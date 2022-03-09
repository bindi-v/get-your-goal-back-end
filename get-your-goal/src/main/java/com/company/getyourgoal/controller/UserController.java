package com.company.getyourgoal.controller;

import com.company.getyourgoal.model.Goal;
import com.company.getyourgoal.model.User;
import com.company.getyourgoal.repository.GoalRepository;
import com.company.getyourgoal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

//@CrossOrigin("http://localhost:3000")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    GoalRepository goalRepository;


    @PostMapping("/users")
    @ResponseStatus(value = HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {

        User newUser = userRepository.save(user);

       if(!user.getGoals().isEmpty()) {
           List<Goal> goals = user.getGoals();

           for(Goal g : goals) {
               g.setUserId(newUser.getId());
               goalRepository.save(g);
           }
       }
       return newUser;
    }


    @GetMapping("/users")
    @ResponseStatus(value = HttpStatus.OK)
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        for(User user: userList) {
            List<Goal> goalList = user.getGoals();
        }
        return userList;
       // return userRepository.findAll();
    }


    @GetMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public User getUserById(@PathVariable Integer id) {
        User user = userRepository.getById(id);
        List<Goal> goalList = user.getGoals();
        return user;
//        Optional<User> returnVal = userRepository.findById(id);
//        if (returnVal.isPresent()) {
//            return returnVal.get();
//        } else {
//            return null;
//        }
    }


    @PutMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public User updateUser(@RequestBody   User user,
                           @PathVariable int id) {
        User newUser = userRepository.getById(id);
        if(!newUser.equals(null)) {
            user.setId(newUser.getId());
            userRepository.save(user);
        }
        return user;
    }


    @DeleteMapping("/users/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") int id) {
        userRepository.deleteById(id);
       // return "Post deleted Successfully !!!!!";
    }
}
