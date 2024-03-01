package com.example.YouTube.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoShortDTO {
    private String id;
    private String name;
    private ChannelShortInfoDTO channel;
    private Double duration;
}
