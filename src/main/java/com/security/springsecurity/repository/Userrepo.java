package com.security.springsecurity.repository;

import com.security.springsecurity.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Userrepo extends JpaRepository<User, Integer> {
    //we are creating a method which would return the username and passes to the service
    User findbyusername(String username);
}
