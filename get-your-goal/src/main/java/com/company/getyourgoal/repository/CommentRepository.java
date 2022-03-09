package com.company.getyourgoal.repository;

import com.company.getyourgoal.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
   // List<Comment> findAllCommentsByGoalId(int goalId);
   // List<Comment> findAllCommentsByUserId(int goalId);
    List<Comment> findAllCommentsByUserId(int userId);
}
