package com.example.YouTube.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachDTO {
    private String id;
    private String originalName;
    private Long size;
    private String type;
    private String path;
    private String duration;

}
