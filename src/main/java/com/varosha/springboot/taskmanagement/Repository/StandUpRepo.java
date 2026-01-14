package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.Models.StandUp;
import com.varosha.springboot.taskmanagement.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface StandUpRepo extends JpaRepository<StandUp, Long> {
    boolean existsByUserAndSubmissionDate(User user, LocalDate date);
}