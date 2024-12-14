package com.portfolio.tms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portfolio.tms.model.Task;
import com.portfolio.tms.model.User;
import com.portfolio.tms.repository.TaskRepository;
import com.portfolio.tms.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    public UserRepository userRepository;

    @Autowired TaskRepository taskRepository;

    
    public User createUser(User user){
        return userRepository.save(user);
    }

    public User getUser(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with ID: " + id));
        return user;
    }

    public User createTask(Long id,Task task){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with ID: " + id));
        user.addTask(task);
        userRepository.save(user);
        return user;
    }

    public Task getTask(Long userId, int taskId){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with ID: " + userId));
        int givenId = taskId - 1;
        if (givenId > 0 || givenId <= user.getTasks().size()){
            return user.getTask(givenId);
        }else {
            throw new RuntimeException("given id is out of index of tasks list!");
        }
    }

    public User updateTask(Long userId, int taskId, Task updatedTask) {
        // Fetch the user by ID
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    
        Task taskToUpdate = null;
    
        int lines = 1;
    
        for (Task task : user.getTasks()) {
            if (lines == taskId) {
                taskToUpdate = task;
                break;
            }
            lines++; 
        }
    
        if (taskToUpdate == null) {
            // Task was not found with the given ID
            throw new RuntimeException("Task not found with given ID: " + taskId);
        }
    
        // Update the task's fields
        taskToUpdate.setTitle(updatedTask.getTitle());
        taskToUpdate.setDescription(updatedTask.getDescription());
        taskToUpdate.setStatus(updatedTask.getStatus());
        taskToUpdate.setDueDate(updatedTask.getDueDate());
    
       
        taskRepository.save(taskToUpdate);
    
        return user;
    }

    public User deleteTask(Long userId, int taskId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        Task taskToDelete = null;

        int lines = 1;

        for (Task task: user.getTasks()){
            if (taskId == lines){
                taskToDelete = task;
                break;
            }
            lines ++;
        }

        if (taskToDelete == null){
            throw new RuntimeException("Task not found with given ID: " + taskId);
        }

        taskToDelete.setUser(null);
        user.removeTask(taskToDelete);
        taskRepository.deleteById(taskToDelete.getId());

        return user;

    }
    
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
