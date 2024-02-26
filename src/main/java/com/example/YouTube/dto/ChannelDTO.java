package com.example.YouTube.dto;

import com.example.YouTube.enums.ChannelStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelDTO {
    private String id;
    private String name;
    private String photoId;
    private String description;
    private ChannelStatus status;
    private String bannerId;
    private Integer profileId;
    private Integer subscriptionCount;
}
