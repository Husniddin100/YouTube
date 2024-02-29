package com.example.YouTube.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoTagShortInfoDTO {
    private Integer id;
    private String videoId;
    private TagShortDTO tagShortDTO;
    private LocalDateTime createdDate;
}
