package com.advisor.flight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.advisor.flight.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
