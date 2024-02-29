package com.example.YouTube.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailHistoryDTO {
    private Integer id;
    private String toEmail;
    private String title;
    private String message;
    private LocalDateTime createdDate;
}
