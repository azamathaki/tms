package com.portfolio.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portfolio.tms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
