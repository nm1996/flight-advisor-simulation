package com.advisor.flight.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.advisor.flight.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUserName(String username);

	Optional<User> findByUserNameAndPassword(String username, String password);

	@Query(value = "SELECT salt FROM users WHERE user_name=:username", nativeQuery = true)
	Optional<String> getSaltByUserName(@Param("username") String username);
}
