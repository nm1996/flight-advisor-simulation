package com.advisor.flight.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.advisor.flight.utils.FlightDataContainer;
import com.fasterxml.jackson.annotation.JsonProperty;

public class FlightDto {

	public enum LOCATION {
		FROM, TO
	}

	@JsonProperty("locations")
	private Map<String, String> locations;

	@JsonProperty("totalFlightPrice")
	private Double totalPrice;

	@JsonProperty("paths")
	private List<FlightDataContainer> paths;

	@JsonProperty("totalDistance")
	private String totalDistance;

	public FlightDto() {
		locations = new HashMap<>();
		paths = new ArrayList<>();
	}

	public FlightDto(List<FlightDataContainer> paths) {
		this.paths = paths;
	}

	public void addPath(FlightDataContainer path) {
		this.paths.add(path);
	}

	public List<FlightDataContainer> getPaths() {
		return paths;
	}

	public void setPaths(List<FlightDataContainer> paths) {
		this.paths = paths;
	}

	public Map<String, String> getLocations() {
		return locations;
	}

	public void setLocations(Map<String, String> locations) {
		this.locations = locations;
	}

	public String getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(String totalDistance) {
		this.totalDistance = totalDistance;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public void addLocation(LOCATION location, String cityName) {
		this.locations.put(location.toString().toLowerCase(), cityName);
	}

}
