package com.example.YouTube.service;

import com.example.YouTube.dto.ChannelDTO;
import com.example.YouTube.dto.EmailHistoryDTO;
import com.example.YouTube.dto.PageEmailHistoryDTO;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.entity.EmailHistoryEntity;
import com.example.YouTube.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmailHistoryService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    public Page<EmailHistoryDTO> test(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "created_date");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<EmailHistoryEntity> pages = emailHistoryRepository.findAll(pageable);
        List<EmailHistoryEntity> entityList = pages.getContent();

        List<EmailHistoryDTO> dtoList = new ArrayList<>();
        entityList.forEach(entity -> dtoList.add(toDTO(entity)));

        return new PageImpl<>(dtoList, pageable, pages.getTotalElements());
    }

    public PageImpl getListPagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<EmailHistoryEntity> channelPage = emailHistoryRepository.findAll(paging);

        List<EmailHistoryEntity> entityList = channelPage.getContent();
        Long totalElements = channelPage.getTotalElements();

        List<EmailHistoryDTO> dtoList = new LinkedList<>();
        for (EmailHistoryEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public PageImpl getListByEmailPagination(Integer page, Integer size, PageEmailHistoryDTO dto) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<EmailHistoryEntity> pages = emailHistoryRepository.findByToEmail(dto.getEmail(), paging);

        long totalElements = pages.getTotalElements();
        List<EmailHistoryEntity> content = pages.getContent();
        List<EmailHistoryDTO> dtoList = new LinkedList<>();
        for (EmailHistoryEntity entity : content) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList,paging, totalElements);
    }
    public EmailHistoryDTO toDTO(EmailHistoryEntity entity) {
        EmailHistoryDTO dto = new EmailHistoryDTO();
        dto.setId(entity.getId());
        dto.setToEmail(entity.getToEmail());
        dto.setTitle(entity.getTitle());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setMessage(entity.getMessage());
        return dto;
    }
}
