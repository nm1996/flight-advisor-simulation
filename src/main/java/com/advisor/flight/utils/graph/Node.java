package com.advisor.flight.utils.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.advisor.flight.entity.Airport;

public class Node {

	private UUID id;

	private Airport airport;

	private Double price = Double.MAX_VALUE;

	private List<Node> lowestPrice = new LinkedList<>();

//	private List<Route> routes = new ArrayList<Route>();

//	private HashMap<Node, Double> routes = new HashMap<>();

	private HashMap<Long, Map<Node, Double>> routes = new HashMap<>();

//	public void addRoute(Node node, Double price) {
//		routes.put(node, price);
//	}

//	public HashMap<Node, Double> getRoutes() {
//		return routes;
//	}
//
//	public void setRoutes(HashMap<Node, Double> routes) {
//		this.routes = routes;
//	}

	public void addRoute(Long routeId, Map<Node, Double> map) {
		routes.put(routeId, map);
	}

	public HashMap<Long, Map<Node, Double>> getRoutes() {
		return routes;
	}

	public void setRoutes(HashMap<Long, Map<Node, Double>> routes) {
		this.routes = routes;
	}

	public Node(Airport airport) {
		this.id = UUID.randomUUID();
		this.airport = airport;
	}

	public Airport getAirport() {
		return airport;
	}

	public void setAirport(Airport airport) {
		this.airport = airport;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public List<Node> getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(List<Node> lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	@Override
	public String toString() {
		return id.toString() + "_" + airport.getName();
	}

}
