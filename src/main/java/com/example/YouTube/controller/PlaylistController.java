package com.example.YouTube.controller;

import com.example.YouTube.dto.PlaylistDTO;
import com.example.YouTube.enums.LanguageEnums;
import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.service.PlaylistService;
import com.example.YouTube.util.HttpRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;
    @PostMapping("/create")
    public ResponseEntity<PlaylistDTO> create(@RequestBody PlaylistDTO dto,
                                              HttpServletRequest request){
        HttpRequestUtil.getProfileId(request, ProfileRole.ROLE_USER);
        return ResponseEntity.ok(playlistService.create(dto));

    }
    @PostMapping("/user_owner/change_status")
    public ResponseEntity<Boolean>change_status(@PathVariable("status") String status,
                                                @PathVariable("num")String num,
                                                @RequestHeader LanguageEnums languageEnums){
      return   ResponseEntity.ok(playlistService.change_status(status,num,languageEnums));
    }
    @PostMapping("/userAndownwrAndAdmin/delete")
    public ResponseEntity<String>delete(){
        return ResponseEntity.ok("salom");
    }
}
