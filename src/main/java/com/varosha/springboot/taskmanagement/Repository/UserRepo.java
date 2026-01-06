package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.Models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Page<User> findByActiveTrue(Pageable pageable);
}
