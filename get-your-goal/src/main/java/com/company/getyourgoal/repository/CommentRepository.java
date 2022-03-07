package com.company.getyourgoal.repository;

import com.company.getyourgoal.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> findAllCommentsByGoalId(int goalId);
}
