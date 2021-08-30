package com.advisor.flight.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advisor.flight.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	Optional<List<City>> findByName(String name);
}
