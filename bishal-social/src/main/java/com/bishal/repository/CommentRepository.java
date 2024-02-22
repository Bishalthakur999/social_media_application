package com.bishal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bishal.models.Comment;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
	

}
