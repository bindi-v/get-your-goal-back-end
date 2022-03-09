package com.company.getyourgoal.repository;

import com.company.getyourgoal.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
   // List<Goal> findAllGoalsByUserId(int userId) throws Exception;
   List<Goal> findAllGoalsByUserId(int userId);
}
