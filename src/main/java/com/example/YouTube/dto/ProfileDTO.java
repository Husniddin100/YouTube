package com.example.YouTube.dto;

import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileDTO {
    protected Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Integer photoId;
    private ProfileRole role;
    private ProfileStatus status;
    private LocalDateTime createdDate;
    private Boolean visible;
}
