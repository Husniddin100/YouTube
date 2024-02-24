package com.example.YouTube.controller;

import com.example.YouTube.dto.CreatProfileDto;
import com.example.YouTube.dto.JwtDTO;
import com.example.YouTube.dto.ProfileDTO;
import com.example.YouTube.enums.LanguageEnums;
import com.example.YouTube.service.ProfileService;
import com.example.YouTube.util.HttpRequestUtil;
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
    @GetMapping("/verification/emailChange/{jwt}")
    public void verificationEmailChange(@PathVariable("jwt") String jwt,@RequestHeader LanguageEnums languageEnums){
        profileService.verificationChange(jwt,languageEnums);
    }
    @PostMapping("/updateNameAndSurname")
    public ResponseEntity<Boolean>update(@RequestParam(value = "name") String name,@RequestParam("surname")String surname,
                                       HttpServletRequest request  ) {
       Integer id= HttpRequestUtil.getProfileId(request);
        return ResponseEntity.ok(profileService.updateNameAndSurname(name,surname,id));
    }
    @PostMapping("/updateEmail")
    public ResponseEntity<Boolean>updateEmail(@RequestParam(value = "email") String email,
                                              @RequestParam ("newEmail") String newEmail,
                                              @RequestParam("password") String password,
                                              @RequestHeader LanguageEnums languageEnums){
       return ResponseEntity.ok(profileService.updateEmail(email,newEmail,password,languageEnums));

    }
    @GetMapping("/verification/emailChangeUpdateNewEmail/{jwt}")
    public void emailChangeUpdateNewEmail(@PathVariable("jwt") String jwt,@RequestHeader LanguageEnums languageEnums){
        profileService.emailChangeUpdateNewEmail(jwt,languageEnums);
    }
}