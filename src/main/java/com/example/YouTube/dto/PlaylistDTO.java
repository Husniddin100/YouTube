package com.example.YouTube.dto;

import com.example.YouTube.enums.PlaylistStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistDTO {
    private Integer id;
    private String channelId;
    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer orderNumber;
}
