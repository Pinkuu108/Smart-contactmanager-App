package com.smart.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smart.entity.User;

@Repository
public interface UserReposatory extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email")
    public User getUserByuserName(@Param("email") String email);
}
