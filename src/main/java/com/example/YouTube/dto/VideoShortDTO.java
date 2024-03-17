package com.example.YouTube.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoShortDTO {
    private String id;
    private String title;
    private ChannelShortInfoDTO channel;
    private Double duration;
}
