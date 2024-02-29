package com.example.YouTube.dto;

import com.example.YouTube.enums.TypeStatus;
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
    private ChannelDTO channel;
    private ProfileDTO owner;
    private String playListJson;
    private AttachDTO attach;
    private AttachDTO previewAttach;
    // Type
    private TypeStatus typeStatus;
    private Integer viewCount;
    private Integer shareCount;
    private String description;


}
