package com.varosha.springboot.taskmanagement.Repository;

import com.varosha.springboot.taskmanagement.Enums.NotificationType;
import com.varosha.springboot.taskmanagement.Models.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {

    @Query(value = """
            SELECT * FROM notification
            WHERE recipient_email = :recipientEmail
            ORDER BY created_at DESC
            """, nativeQuery = true)
    List<Notification> findByRecipientEmail(@Param("recipientEmail") String recipientEmail);

    @Query(value = """
                SELECT count(*)
                FROM notification
                WHERE recipient_email = :recipientEmail
                AND is_read = false
            """, nativeQuery = true)
    long countUnreadByRecipientEmail(@Param("recipientEmail") String recipientEmail);

    @Modifying
    @Transactional
    @Query(value = """
                UPDATE notification n
                set n.is_read = true
                where n.recipient_email = :recipientEmail
                AND n.is_read = false
            """, nativeQuery = true)
    int markAllAsReadByRecipientEmail(@Param("recipientEmail")String recipientEmail);

    @Modifying
    @Transactional
    @Query(value = """
                UPDATE notification
                set is_read = true
                where id = :notificationId
                AND is_read = false
            """, nativeQuery = true)
    int markAsReadByNotificationId(@Param("notificationId") Long notificationId);

    boolean existsByRecipientIdAndTypeAndReferenceId(Long recipientId, NotificationType type, Long referenceId);

    @Query(value = """
                SELECT n.* FROM notification n
                where n.recipient_email = :recipientEmail
                AND n.is_read = false
                ORDER BY n.created_at DESC
            """, nativeQuery = true)
    List<Notification> getUnreadNotifications(@Param("recipientEmail") String email);

}
