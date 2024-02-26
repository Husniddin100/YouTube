package com.example.YouTube.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AttachDTO {
    private String id;
    private String originalName;
    private Long size;
    private String type;
    private String path;
    private Double duration;
    private LocalDateTime createdDate;
    private String url;
    // attach
}
