package com.advisor.flight.utils.graph;

import java.util.HashSet;
import java.util.Set;

public class Graph {

	private Set<Node> nodes = new HashSet<>();

	public void addNode(Node node) {
		nodes.add(node);
	}

	public Set<Node> getNodes() {
		return nodes;
	}

	public void setNodes(Set<Node> nodes) {
		this.nodes = nodes;
	}

}
