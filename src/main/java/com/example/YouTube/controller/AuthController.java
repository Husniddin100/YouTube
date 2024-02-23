package com.example.YouTube.controller;

import com.example.YouTube.config.CustomUserDetails;
import com.example.YouTube.config.CustomUserDetailsService;
import com.example.YouTube.dto.AuthDTO;
import com.example.YouTube.dto.CreateProfileDTO;
import com.example.YouTube.dto.ProfileDTO;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.LangEnum;
import com.example.YouTube.service.AuthService;
import com.example.YouTube.util.SpringSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO authDTO
            , @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum language) {
        log.info("login {}", authDTO.getEmail());
        return ResponseEntity.ok(authService.login(authDTO, language));
    }

    @PostMapping("/registration")
    public ResponseEntity<Boolean> registrationEmail(@RequestBody CreateProfileDTO dto,
                                                     @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum language) {
        return ResponseEntity.ok(authService.registration(dto, language));
    }

    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt,
                                                    @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum language) {
        return ResponseEntity.ok(authService.emailVerification(jwt, language));
    }
}