package com.company.getyourgoal.controller;

import com.company.getyourgoal.model.Goal;
import com.company.getyourgoal.repository.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoalController {

    @Autowired
    GoalRepository goalRepository;

    @GetMapping("/goals")
    public List<Goal> getAllGoals() {
        List<Goal> goalList = goalRepository.findAll();

        return goalList;
    }

    @GetMapping("/goals/{id}")
    public Goal getGoal(@PathVariable Integer id) {
        Goal goal = goalRepository.getById(id);
        return goal;
    }

    @PostMapping("/goals")
    @ResponseStatus(HttpStatus.CREATED)
    public Goal createGoal(@RequestBody Goal goal) {
        goalRepository.save(goal);
        return goal;
    }

    @PutMapping("/goals/{id}")
    public Goal updateGoal(@RequestBody Goal goal, @PathVariable int id) {
        Goal newGoal = goalRepository.getById(id);
        newGoal.setGoalTitle(goal.getGoalTitle());
        newGoal.setGoal(goal.getGoal());
        return goalRepository.save(newGoal);

    }

    @DeleteMapping("/goals/{id}")
    public void deleteGoal(@PathVariable int id) {

        goalRepository.deleteById(id);
    }

}
