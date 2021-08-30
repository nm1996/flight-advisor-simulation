package com.advisor.flight.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String description;

	@ManyToOne
	private Country country;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "city")
	private List<Comment> comments;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public boolean equals(Object obj) {
		Long id = this.getId();
		String name = this.getName();

		Long providedId = ((City) obj).getId();
		String providedName = ((City) obj).getName();

		return id.equals(providedId) && name.equals(providedName);
	}

	public boolean equalsNameAndCountry(Object obj) {
		String name = this.getName();
		Long countryId = this.getCountry().getId();

		String providedName = ((City) obj).getName();
		Long providedCountryId = ((City) obj).getCountry().getId();

		return name.equals(providedName) && countryId.equals(providedCountryId);
	}
	
	public boolean equalsIdAndCountry(Object obj) {
		Long id = this.getId();
		Long countryId = this.getCountry().getId();
		
		Long providedId = ((City) obj).getId();
		Long providedCountryId = ((City) obj).getCountry().getId();
		
		return id.equals(providedId) && countryId.equals(providedCountryId);
	}

}
