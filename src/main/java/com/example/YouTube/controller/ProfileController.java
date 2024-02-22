package com.example.YouTube.controller;

import com.example.YouTube.dto.CreatProfileDto;
import com.example.YouTube.dto.JwtDTO;
import com.example.YouTube.dto.ProfileDTO;
import com.example.YouTube.enums.LanguageEnums;
import com.example.YouTube.service.ProfileService;
import com.example.YouTube.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping("/getIdChange_password")
    public ResponseEntity<CreatProfileDto> change_password(@RequestBody CreatProfileDto dto,
                                                           @RequestHeader LanguageEnums languageEnums){

        return ResponseEntity .ok(profileService.change_password(dto,languageEnums));
    }
}
