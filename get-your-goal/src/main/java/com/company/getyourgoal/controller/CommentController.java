package com.company.getyourgoal.controller;

import com.company.getyourgoal.model.Comment;
import com.company.getyourgoal.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin("http://localhost:3000")
@RestController
public class CommentController {

    @Autowired
    CommentRepository commentRepository;


    @GetMapping("/comments")
    @ResponseStatus(HttpStatus.OK)
    public List<Comment> getAllComments() {
        List<Comment> commentList = commentRepository.findAll();
        return commentList;
    }


    @GetMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Comment> getComment(@PathVariable Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment;
    }


    @PostMapping("/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment createComment(@RequestBody Comment comment) {
            commentRepository.save(comment);
        return comment;
    }


    @PutMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Comment updateComment(@RequestBody Comment comment, @PathVariable int id) {
        Comment newComment = commentRepository.getById(id);
        newComment.setComment(comment.getComment());
        return commentRepository.save(newComment);
    }


    @DeleteMapping("/comments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComments(@PathVariable int id) {

        commentRepository.deleteById(id);
    }
}
