package com.advisor.flight.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportDto {

	@JsonProperty
	private Long id;

	@JsonProperty
	private String name;

	@JsonProperty("city")
	private CityDto cityDto;

	@JsonProperty
	private String iata;

	@JsonProperty
	private String icao;

	@JsonProperty
	private Double latitude;

	@JsonProperty
	private Double longitude;

	@JsonProperty
	private Double altitude;

	@JsonProperty
	private Double timezone;
	@JsonProperty
	private String dst;
	@JsonProperty
	private String tz;
	@JsonProperty
	private String type;
	@JsonProperty
	private String source;

	public AirportDto() {
	}

	public AirportDto(Long id, String name, CityDto cityDto, String iata, String icao, Double latitude,
			Double longitude, Double altitude, Double timezone, String dst, String tz, String type, String source) {
		super();
		this.id = id;
		this.name = name;
		this.cityDto = cityDto;
		this.iata = iata;
		this.icao = icao;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.timezone = timezone;
		this.dst = dst;
		this.tz = tz;
		this.type = type;
		this.source = source;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CityDto getCityDto() {
		return cityDto;
	}

	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}

	public String getIata() {
		return iata;
	}

	public void setIata(String iata) {
		this.iata = iata;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getAltitude() {
		return altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	public Double getTimezone() {
		return timezone;
	}

	public void setTimezone(Double timezone) {
		this.timezone = timezone;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public String getTz() {
		return tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}
