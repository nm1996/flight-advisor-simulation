package com.advisor.flight.utils.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Calculator {

	private static void CalculateMinimumPrice(Node evaluationNode, Double edgeWeight, Node sourceNode) {
		Double sourcePrice = sourceNode.getPrice();
		if (sourcePrice + edgeWeight < evaluationNode.getPrice()) {
			evaluationNode.setPrice(sourcePrice + edgeWeight);
			LinkedList<Node> lowestPrice = new LinkedList<>(sourceNode.getLowestPrice());
			lowestPrice.add(sourceNode);
			evaluationNode.setLowestPrice(lowestPrice);
		}
	}

	private static Node getLowestPriceNode(Set<Node> unsettledNodes) {
		Node lowestPriceNode = null;

		double lowestPrice = Double.MAX_VALUE;
		for (Node node : unsettledNodes) {
			Double nodePrice = node.getPrice();
			if (nodePrice < lowestPrice) {
				lowestPrice = nodePrice;
				lowestPriceNode = node;
			}
		}

		return lowestPriceNode;
	}

	public static Graph calculateLowestPricePathFromSource(Graph graph, Node source) {
		source.setPrice(0.0);

		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();

		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestPriceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Long, Map<Node, Double>> routes : currentNode.getRoutes().entrySet()) {
				for (Entry<Node, Double> route : routes.getValue().entrySet()) {
					Node routeNode = route.getKey();
					Double edgeWeight = route.getValue();
					if (!settledNodes.contains(routeNode)) {
						CalculateMinimumPrice(routeNode, edgeWeight, currentNode);
						unsettledNodes.add(routeNode);
					}
				}
			}
			settledNodes.add(currentNode);
		}

		return graph;
	}

}
