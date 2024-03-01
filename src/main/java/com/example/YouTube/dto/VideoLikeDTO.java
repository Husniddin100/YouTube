package com.example.YouTube.dto;

import com.example.YouTube.enums.VideoLikeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VideoLikeDTO {
    private Integer id;
    private Integer profileId;
    private String videoId;
    private LocalDateTime createdDate;
    private VideoLikeType type;
}
