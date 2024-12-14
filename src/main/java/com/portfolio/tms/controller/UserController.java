package com.portfolio.tms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.tms.model.Task;
import com.portfolio.tms.model.User;
import com.portfolio.tms.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    // create a user
    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return service.createUser(user);
    }

    // get a user by id
    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") Long id){
        return service.getUser(id);
    }

    // delete user by id and all tasks will be deleted
    @DeleteMapping("/users/{id}")
    public String removeUser(@PathVariable("id") Long id){
        service.deleteUser(id);
        return "user deleted seccussfully by given ID: " + id;
    }

    // create a user's task
    @PostMapping("users/{id}/tasks")
    public User createTask(@PathVariable("id") Long id, @RequestBody Task task){
        return service.createTask(id, task);
    }

    // get a specific task from list of tasks
    @GetMapping("/users/{id}/tasks/{taskId}")
    public Task getTask(@PathVariable("id") Long id, @PathVariable("taskId") int taskId){
        return service.getTask(id, taskId);
    }

    // update an existing task
    @PutMapping("/users/{id}/tasks/{taskId}")
    public User updateTask(@PathVariable("id") Long id, @PathVariable("taskId") int taskId, @RequestBody Task updatedTask){
        return service.updateTask(id, taskId, updatedTask);
    }

    // delete an existing task
    @DeleteMapping("/users/{id}/tasks/{taskId}")
    public User removeTask(@PathVariable("id") Long id, @PathVariable("taskId") int taskId){
        return service.deleteTask(id, taskId);
    }
    
}
