package com.company.getyourgoal.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

    @Entity
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @Table(name = "goal")
    public class Goal implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Integer id;
        @NotEmpty
        private String goalTitle;
        private String goal;
        private String userName;
        private Integer userId;
        @Column(name = "goalPosted_at")
        private Date goalPostedAt = new Date();

        @Column(name = "updated_at")
        private Date goalUpdatedAt = new Date();

        @OneToMany(mappedBy = "goalId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        private List<Comment> comments;

        public Goal() {
        }

        public Goal(Integer id, String goalTitle, String goal, String userName, Integer userId) {
            this.id = id;
            this.goalTitle = goalTitle;
            this.goal = goal;
            this.userName = userName;
            this.userId = userId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getGoalTitle() {
            return goalTitle;
        }

        public void setGoalTitle(String goalTitle) {
            this.goalTitle = goalTitle;
        }

        public String getGoal() {
            return goal;
        }

        public void setGoal(String goal) {
            this.goal = goal;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Date getGoalPostedAt() {
            return goalPostedAt;
        }

        public void setGoalPostedAt(Date goalPostedAt) {
            this.goalPostedAt = goalPostedAt;
        }

        public Date getGoalUpdatedAt() {
            return goalUpdatedAt;
        }

        public void setGoalUpdatedAt(Date goalUpdatedAt) {
            this.goalUpdatedAt = goalUpdatedAt;
        }

        public List<Comment> getComments() {
            return comments;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Goal goal1 = (Goal) o;
            return Objects.equals(id, goal1.id) && Objects.equals(goalTitle, goal1.goalTitle) && Objects.equals(goal, goal1.goal) && Objects.equals(userName, goal1.userName) && Objects.equals(userId, goal1.userId) && Objects.equals(goalPostedAt, goal1.goalPostedAt) && Objects.equals(goalUpdatedAt, goal1.goalUpdatedAt) && Objects.equals(comments, goal1.comments);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, goalTitle, goal, userName, userId, goalPostedAt, goalUpdatedAt, comments);
        }

        @Override
        public String toString() {
            return "Goal{" +
                    "id=" + id +
                    ", goalTitle='" + goalTitle + '\'' +
                    ", goal='" + goal + '\'' +
                    ", userName='" + userName + '\'' +
                    ", userId=" + userId +
                    ", goalPostedAt=" + goalPostedAt +
                    ", goalUpdatedAt=" + goalUpdatedAt +
                    ", comments=" + comments +
                    '}';
        }
    }


