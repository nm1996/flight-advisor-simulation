package com.advisor.flight.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advisor.flight.entity.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

	@Query(value = "SELECT * FROM airports WHERE city_id=:id", nativeQuery = true)
	Optional<List<Airport>> findByCity(@Param("id") Long id);
}
