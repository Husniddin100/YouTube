package com.example.YouTube.repository;

import com.example.YouTube.entity.EmailSendHistoryEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailSenderRepository extends CrudRepository<EmailSendHistoryEntity,Integer> {
    @Query("SELECT count (e)from  EmailSendHistoryEntity e WHERE e.email=?1 and e.createdDate between ?2 and ?3")
    Long countSendEmail(String email, LocalDateTime from, LocalDateTime to);
@Query("from EmailSendHistoryEntity where email=?1")
    Optional<EmailSendHistoryEntity> findByEmail(String email);
@Transactional
@Modifying
@Query("update EmailSendHistoryEntity  SET visible=false where email=?1")
    void updateVisible(String email);
}
