package com.example.YouTube.dto;

import com.example.YouTube.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProfileDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileRole role;
}
