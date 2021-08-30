package com.advisor.flight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advisor.flight.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	Optional<Country> findByName(String name);
}
