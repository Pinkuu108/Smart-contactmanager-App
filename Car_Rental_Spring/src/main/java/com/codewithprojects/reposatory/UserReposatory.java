package com.codewithprojects.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codewithprojects.entity.User;

@Repository
public interface UserReposatory extends JpaRepository<User, Long> {

}
