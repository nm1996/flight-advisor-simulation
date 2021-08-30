package com.advisor.flight.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advisor.flight.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

	@Query(value = "SELECT * FROM routes WHERE source_airport_id=:id", nativeQuery = true)
	Optional<List<Route>> findAllBySourceAirport(@Param("id") Long id);

	@Query(value = "SELECT * FROM routes where destination_airport_id=:id", nativeQuery = true)
	Optional<List<Route>> findAllByDestinationAirport(@Param("id") Long id);

	@Query(value = "SELECT * FROM routes where source_airport_id=:sourceId AND  destination_airport_id=:destinationId AND price=:price", nativeQuery = true)
	Optional<Route> findRouteByAirportsAndPrice(@Param("sourceId") Long sourceId,
			@Param("destinationId") Long destinationId, @Param("price") Double price);
}
