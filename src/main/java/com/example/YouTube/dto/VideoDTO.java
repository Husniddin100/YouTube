package com.example.YouTube.dto;

import com.example.YouTube.enums.VideoStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoDTO {
    private String id;
    private String previewAttachId;
    private String title;
    private Integer categoryId;
    private String attachId;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private VideoStatus status;
    // Type



}
