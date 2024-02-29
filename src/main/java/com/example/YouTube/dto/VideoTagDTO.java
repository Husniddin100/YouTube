package com.example.YouTube.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoTagDTO {
    private Integer id;
    private String videoId;
    private Integer tagId;
    private LocalDateTime createdDate;
}
