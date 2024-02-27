package com.example.YouTube.repository;

import com.example.YouTube.entity.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity, Integer> , PagingAndSortingRepository<EmailHistoryEntity,Integer> {
    Page<EmailHistoryEntity> findByToEmail(String Email,Pageable paging);
}
