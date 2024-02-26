package com.example.YouTube.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
