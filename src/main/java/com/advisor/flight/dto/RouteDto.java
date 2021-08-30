package com.advisor.flight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RouteDto {

	@JsonProperty
	private Long id;

	@JsonProperty
	private String airline;

	@JsonProperty
	private Long airlineId;

	@JsonProperty("sourceAirport")
	private AirportDto sourceAirportDto;

	@JsonProperty("destinationAirport")
	private AirportDto destinationAirportDto;

	@JsonProperty
	private String codeshare;

	@JsonProperty
	private int stops;

	@JsonProperty
	private String equipment;

	@JsonProperty
	private Double price;

	public RouteDto() {
	}

	public RouteDto(Long id, String airline, Long airlineId, AirportDto sourceAirportDto,
			AirportDto destinationAirportDto, String codeshare, int stops, String equipment, Double price) {
		super();
		this.id = id;
		this.airline = airline;
		this.airlineId = airlineId;
		this.sourceAirportDto = sourceAirportDto;
		this.destinationAirportDto = destinationAirportDto;
		this.codeshare = codeshare;
		this.stops = stops;
		this.equipment = equipment;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public Long getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(Long airlineId) {
		this.airlineId = airlineId;
	}

	public AirportDto getSourceAirportDto() {
		return sourceAirportDto;
	}

	public void setSourceAirportDto(AirportDto sourceAirportDto) {
		this.sourceAirportDto = sourceAirportDto;
	}

	public AirportDto getDestinationAirportDto() {
		return destinationAirportDto;
	}

	public void setDestinationAirportDto(AirportDto destinationAirportDto) {
		this.destinationAirportDto = destinationAirportDto;
	}

	public String getCodeshare() {
		return codeshare;
	}

	public void setCodeshare(String codeshare) {
		this.codeshare = codeshare;
	}

	public int getStops() {
		return stops;
	}

	public void setStops(int stops) {
		this.stops = stops;
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
