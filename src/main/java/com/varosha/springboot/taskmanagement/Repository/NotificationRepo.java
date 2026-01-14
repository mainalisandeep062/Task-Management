package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

    @Query(value = """
            SELECT * FROM notification
            WHERE recipient_id = :recipientId
            ORDER BY created_at DESC
            """, nativeQuery = true)
    List<Notification> findByRecipientId(@Param("recipientId") Long recipientId);

    @Query(value = """
                SELECT count(*)
                FROM notification
                WHERE recipient_id = :recipientId
                AND is_read = false
            """, nativeQuery = true)
    long countUnreadByRecipientId(@Param("recipientId") Long recipientId);

    @Query(value = """
            SELECT * from notification
            where recipient_id = :recipeintId
            AND is_read = false
            """, nativeQuery = true)
    List<Notification> findUnreadNotification(@Param("recipientId") Long recipientId);

}
