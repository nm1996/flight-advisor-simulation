package com.advisor.flight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String airline;
	private Long airlineId;

	@ManyToOne
	private Airport sourceAirport;

	@ManyToOne
	private Airport destinationAirport;

	@Column(nullable = true)
	private String codeshare;
	private int stops;
	private String equipment;
	private Double price;

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

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
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

	@Override
	public boolean equals(Object obj) {
		Airport sourceAirport = this.getSourceAirport();
		Airport destinationAirport = this.getDestinationAirport();
		Double price = this.getPrice();

		Airport providedSourceAirport = ((Route) obj).getSourceAirport();
		Airport providedDestinationAirport = ((Route) obj).getDestinationAirport();
		Double providedPrice = ((Route) obj).getPrice();

		return sourceAirport.equals(providedSourceAirport) && destinationAirport.equals(providedDestinationAirport)
				&& price.equals(providedPrice);
	}

}
