package com.advisor.flight.utils;

public class FlightDataContainer {

	private Long routeId;

	private String sourceAirportName;

	private String destinationAirportName;

	private Double routePrice;

	public FlightDataContainer() {
	}

	public FlightDataContainer(Long routeId, String sourceAirportName, String destinationAirportName,
			Double routePrice) {
		this.routeId = routeId;
		this.sourceAirportName = sourceAirportName;
		this.destinationAirportName = destinationAirportName;
		this.routePrice = routePrice;
	}

	public Long getRouteId() {
		return routeId;
	}

	public void setRouteId(Long routeId) {
		this.routeId = routeId;
	}

	public String getSourceAirportName() {
		return sourceAirportName;
	}

	public void setSourceAirportName(String sourceAirportName) {
		this.sourceAirportName = sourceAirportName;
	}

	public String getDestinationAirportName() {
		return destinationAirportName;
	}

	public void setDestinationAirportName(String destinationAirportName) {
		this.destinationAirportName = destinationAirportName;
	}

	public Double getRoutePrice() {
		return routePrice;
	}

	public void setRoutePrice(Double routePrice) {
		this.routePrice = routePrice;
	}

}
