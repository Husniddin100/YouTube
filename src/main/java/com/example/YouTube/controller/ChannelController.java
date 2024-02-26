package com.example.YouTube.controller;

import com.example.YouTube.dto.ChannelDTO;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;
    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<ChannelDTO>createChannel(@RequestBody ChannelDTO dto){
        return ResponseEntity.ok(channelService.createChannel(dto));
    }
    @PutMapping("/update")
    public ResponseEntity<ChannelDTO>updateChannel(@RequestBody ChannelDTO dto){
        return null;
    }
}
