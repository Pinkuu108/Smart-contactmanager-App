package com.smart.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smart.entity.Contact;
import com.smart.entity.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

    // get all contacts of a user
    List<Contact> findByUser(User user);
    
 // search contacts by name for a user
    List<Contact> findByNameContainingAndUser(String name, User user);

}
