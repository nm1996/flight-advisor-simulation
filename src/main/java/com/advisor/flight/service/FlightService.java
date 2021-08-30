package com.advisor.flight.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.advisor.flight.dto.FlightDto;
import com.advisor.flight.dto.FlightDto.LOCATION;
import com.advisor.flight.entity.Airport;
import com.advisor.flight.entity.Route;
import com.advisor.flight.exception.AirportNotExistException;
import com.advisor.flight.exception.CityNotExistException;
import com.advisor.flight.repository.AirportRepository;
import com.advisor.flight.repository.CityRepository;
import com.advisor.flight.repository.RouteRepository;
import com.advisor.flight.utils.DistanceCalculator;
import com.advisor.flight.utils.DoubleManipulator;
import com.advisor.flight.utils.FlightDataContainer;
import com.advisor.flight.utils.graph.Calculator;
import com.advisor.flight.utils.graph.Graph;
import com.advisor.flight.utils.graph.Node;

@Service
public class FlightService {

	private RouteRepository routeRepository;
	private AirportRepository airportRepository;
	private CityRepository cityRepository;

	@Autowired
	public FlightService(RouteRepository routeRepository, AirportRepository airportRepository,
			CityRepository cityRepository) {
		this.routeRepository = routeRepository;
		this.airportRepository = airportRepository;
		this.cityRepository = cityRepository;
	}

	private List<Airport> getAirportsByCity(Long id) throws AirportNotExistException {
		List<Airport> airports = airportRepository.findByCity(id).orElseThrow(() -> new AirportNotExistException());

		if (airports.isEmpty()) {
			throw new AirportNotExistException();
		}

		return airports;
	}

	private List<Graph> calculateGraphFromSourceCity(Long citySourceId) throws AirportNotExistException {
		List<Graph> graphs = new ArrayList<>();
		List<Airport> sourceAirports = getAirportsByCity(citySourceId);

		Graph graph = getGraph();

		for (Airport airport : sourceAirports) {
			Node node = getNodeFromSet(graph.getNodes(), airport);
			if (node == null) {
				break;
			}
			graphs.add(Calculator.calculateLowestPricePathFromSource(graph, node));
		}

		return graphs;
	}

	private List<Node> getCalculatedRoutes(Long citySourceId, Long cityDestinationId) throws AirportNotExistException {
		List<Node> list = new ArrayList<>();
		List<Graph> calculatedGraphs = calculateGraphFromSourceCity(citySourceId);
		List<Airport> destinationAirports = getAirportsByCity(cityDestinationId);

		for (Airport airport : destinationAirports) {
			for (Graph graph : calculatedGraphs) {
				Node node = getNodeFromSet(graph.getNodes(), airport);
				if (node == null) {
					break;
				}

				list.add(node);
			}
		}

		return list;
	}

	public FlightDto getFlight(Long citySourceId, Long cityDestinationId)
			throws AirportNotExistException, CityNotExistException {
		FlightDto flightDto = new FlightDto();
		Node chipestDestinationNode = getChipestNodeForProvidedCities(citySourceId, cityDestinationId);

		List<Node> nodes = getLowestPriceNodes(chipestDestinationNode);

		List<Route> routes = getRoutesForFlight(chipestDestinationNode, nodes);

		fillUpFlightDto(citySourceId, cityDestinationId, flightDto, chipestDestinationNode, routes);

		return flightDto;
	}

	private void fillUpFlightDto(Long citySourceId, Long cityDestinationId, FlightDto flightDto,
			Node chipestDestinationNode, List<Route> routes) throws CityNotExistException {
		Collections.reverse(routes);
		Double distance = 0.0;

		for (Route route : routes) {
			Airport sourceAirport = route.getSourceAirport();
			Airport destinationAirport = route.getDestinationAirport();

			distance += DistanceCalculator.distance(sourceAirport.getLatitude(), sourceAirport.getLongitude(),
					destinationAirport.getLatitude(), destinationAirport.getLongitude());

			FlightDataContainer data = new FlightDataContainer(route.getId(), sourceAirport.getName(),
					destinationAirport.getName(), route.getPrice());
			flightDto.addPath(data);
		}

		flightDto.setTotalPrice(chipestDestinationNode.getPrice());
		flightDto.setTotalDistance(DoubleManipulator.round(distance, 2).toString() + " km");
		flightDto.addLocation(LOCATION.FROM,
				cityRepository.findById(citySourceId).orElseThrow(() -> new CityNotExistException()).getName());
		flightDto.addLocation(LOCATION.TO,
				cityRepository.findById(cityDestinationId).orElseThrow(() -> new CityNotExistException()).getName());
	}

	private List<Route> getRoutesForFlight(Node chipestNode, List<Node> nodes) {
		List<Route> routes = new ArrayList<>();

		for (int i = 0; i < nodes.size(); i++) {
			int index = i;
			Node node;
			Airport source;
			Airport destination;
			Double price;
			if (index == 0) {
				node = (Node) nodes.get(index);
				source = node.getAirport();
				destination = chipestNode.getAirport();
				price = chipestNode.getPrice() - node.getPrice();
			} else {
				node = (Node) nodes.get(index);
				source = node.getAirport();
				Node nodeBefore = (Node) nodes.get(--index);
				destination = nodeBefore.getAirport();
				price = nodeBefore.getPrice() - node.getPrice();
			}
			Route route = routeRepository
					.findRouteByAirportsAndPrice(source.getId(), destination.getId(), DoubleManipulator.round(price, 2))
					.orElse(null);

			routes.add(route);

		}
		return routes;
	}

	private List<Node> getLowestPriceNodes(Node chipestNode) {
		List<Node> nodes = chipestNode.getLowestPrice();
		Collections.reverse(nodes);
		return nodes;
	}

	private Node getChipestNodeForProvidedCities(Long citySourceId, Long cityDestinationId)
			throws AirportNotExistException {
		List<Node> calculatedRoutes = getCalculatedRoutes(citySourceId, cityDestinationId);
		calculatedRoutes.sort(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.getPrice().compareTo(o2.getPrice());
			}
		});
		return calculatedRoutes.get(0);
	}

	private Graph getGraph() {
		Graph graph = new Graph();

		List<Route> routes = routeRepository.findAll();

		for (Route route : routes) {

			Set<Node> nodes = graph.getNodes();
			Long routeId = route.getId();

			Airport source = route.getSourceAirport();
			Airport destination = route.getDestinationAirport();

			fillGraph(route, nodes, routeId, source, destination);

		}

		return graph;
	}

	private void fillGraph(Route route, Set<Node> nodes, Long routeId, Airport source, Airport destination) {
		if (nodeExist(nodes, source)) {
			processExistedSourceNode(route, nodes, routeId, source, destination);
		} else {
			processNotExistedSourceNod(route, nodes, routeId, source, destination);
		}
	}

	private void processNotExistedSourceNod(Route route, Set<Node> nodes, Long routeId, Airport source,
			Airport destination) {
		Node sourceNode = new Node(source);
		addDestinationNodeToSourceNodeRoutes(route, nodes, routeId, destination, sourceNode);
		nodes.add(sourceNode);
	}

	private void addDestinationNodeToSourceNodeRoutes(Route route, Set<Node> nodes, Long routeId, Airport destination,
			Node sourceNode) {
		Node destinationNode;
		destinationNode = processDestinationNode(nodes, destination);
		sourceNode.addRoute(routeId, getMap(destinationNode, route.getPrice()));
	}

	private Node processDestinationNode(Set<Node> nodes, Airport destination) {
		Node destinationNode;
		if (nodeExist(nodes, destination)) {
			destinationNode = getNodeFromSet(nodes, destination);
		} else {
			destinationNode = new Node(destination);
			nodes.add(destinationNode);
		}
		return destinationNode;
	}

	private void processExistedSourceNode(Route route, Set<Node> nodes, Long routeId, Airport source,
			Airport destination) {
		Node sourceNode = getNodeFromSet(nodes, source);
		addDestinationNodeToSourceNodeRoutes(route, nodes, routeId, destination, sourceNode);
	}

	private HashMap<Node, Double> getMap(Node node, Double value) {
		HashMap<Node, Double> map = new HashMap<Node, Double>();
		map.put(node, value);
		return map;
	};

	private Node getNodeFromSet(Set<Node> airports, Airport airport) {
		Object[] array = airports.toArray();
		Node node = null;

		for (Object obj : array) {
			if (((Node) obj).getAirport().getId().equals(airport.getId())) {
				node = (Node) obj;
			}
		}
		return node;
	}

	private boolean nodeExist(Set<Node> airports, Airport airport) {
		boolean isExist = false;
		Object[] array = airports.toArray();

		for (Object obj : array) {
			if (((Node) obj).getAirport().getId().equals(airport.getId())) {
				isExist = true;
			}
		}
		return isExist;
	}

}
