package com.example.YouTube.repository;

import com.example.YouTube.entity.EmailSendHistoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface EmailSenderRepository extends CrudRepository<EmailSendHistoryEntity,Integer> {
    @Query("SELECT count (e)from  EmailSendHistoryEntity e WHERE e.email=?1 and e.createdDate between ?2 and ?3")
    Long countSendEmail(String email, LocalDateTime from, LocalDateTime to);
}
