package com.portfolio.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.tms.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
    
}
