
package com.company.getyourgoal.service;

import com.company.getyourgoal.model.Comment;
import com.company.getyourgoal.model.Goal;
import com.company.getyourgoal.model.User;
import com.company.getyourgoal.repository.CommentRepository;
import com.company.getyourgoal.repository.GoalRepository;
import com.company.getyourgoal.repository.UserRepository;
import com.company.getyourgoal.viewmodel.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ServiceLayer {

    private UserRepository userRepository;
    private GoalRepository goalRepository;
    private CommentRepository commentRepository;

    @Autowired
    public ServiceLayer(UserRepository userRepository,
                        GoalRepository goalRepository,
                        CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.goalRepository = goalRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public UserViewModel saveUser(UserViewModel viewModel) throws Exception {

        User u = new User();
        u.setFirstName(viewModel.getFirstName());
        u.setLastName(viewModel.getLastName());
        u.setUserName(viewModel.getUserName());
        u.setEmail(viewModel.getEmail());
        u.setPassword(viewModel.getPassword());
        u.setGoals(viewModel.getGoals());
        u.setComments(viewModel.getComments());

        u= userRepository.save(u);
        viewModel.setId(u.getId());

        List<Goal> goals = viewModel.getGoals();
        goals.stream()
                .forEach(g -> {
                    g.setUserId(viewModel.getId());
                    goalRepository.save(g);
                });
        goals = goalRepository.findAllGoalsByUserId(viewModel.getId());
        viewModel.setGoals(goals);

        return viewModel;
    }


//   @Transactional
    public UserViewModel findOneUser(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() ? builUserViewModel(user.get()): null;
    }

    private UserViewModel builUserViewModel(User user) {
        Optional<Goal> goal =goalRepository.findById(user.getId());//back to check goal id

        Optional<Comment> comment = commentRepository.findById(user.getId()); //back to check comment id

        UserViewModel uvm = new UserViewModel();
        uvm.setId(user.getId());
        uvm.setFirstName(user.getFirstName());
        uvm.setLastName(user.getLastName());
        uvm.setUserName(user.getUserName());
        uvm.setEmail(user.getEmail());
        uvm.setPassword(user.getPassword());
//        uvm.setGoals(goal.get().ge);
//        uvm.setComments(commentList);

        return uvm;
    }
    public List<UserViewModel> findAllUsers() {
        List<User> userList = userRepository.findAll();

        List<UserViewModel> uvmList = new ArrayList<>();

        for (User user : userList) {
            UserViewModel uvm = builUserViewModel(user);
            uvmList.add(uvm);
        }
        return uvmList;
    }
    @Transactional
    public void updateUser(UserViewModel viewModel) {

        User user = new User();

        user.setId(viewModel.getId());
        user.setFirstName(viewModel.getFirstName());
        user.setLastName(viewModel.getLastName());
        user.setEmail(viewModel.getEmail());
        user.setUserName(viewModel.getUserName());
        user.setPassword(viewModel.getPassword());
//        user.setGoals(viewModel.getListGoals());
//        user.setComments(viewModel.getListComments());
        userRepository.save(user);

        List<Goal> goalList = goalRepository.findAllGoalsByUserId(user.getId());
        goalList.stream()
                .forEach(goal -> goalRepository.deleteById(goal.getId()));
        List<Goal> goals =viewModel.getGoals();
        goals.stream()
                .forEach(goal -> {
                    goal.setUserId(viewModel.getId());
                    goal=goalRepository.save(goal);
                });

    }

    @Transactional
    public void removeUser(int id) {

        // Remove all associated goal
        List<Goal> goalList = goalRepository.findAllGoalsByUserId(id);

        goalList.stream()
                .forEach(goal -> goalRepository.deleteById(goal.getId()));

        // Remove user
        userRepository.deleteById(id);

    }

}

