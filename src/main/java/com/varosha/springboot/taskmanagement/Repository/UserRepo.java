package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query(
            nativeQuery = true,
            value ="""
            select u.* from `user` u where u.e_mail=:email
"""
    )
    Optional<User> findByEmail(String email);

    Optional<User> findByFullNameIgnoreCase(String fullName);
}
