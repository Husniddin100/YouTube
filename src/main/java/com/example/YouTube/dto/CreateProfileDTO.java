package com.example.YouTube.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProfileDTO {
    private String name;
    private String surname;
    private String email;
    private String password;
}
