package com.example.YouTube.controller;

import com.example.YouTube.dto.CategoryDTO;
import com.example.YouTube.dto.VideoDTO;
import com.example.YouTube.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;
    @PostMapping("/create")
    public ResponseEntity<VideoDTO> create(@RequestBody VideoDTO dto){
        VideoDTO dtoList= videoService.create(dto);
        return ResponseEntity.ok(dtoList);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateById(@PathVariable("id") String id,
                                             @RequestBody VideoDTO dto){

        return ResponseEntity.ok(videoService.update(id,dto));
    }
    @PutMapping("/incrementViews/{id}")
    public ResponseEntity<?> incrementViewCount(@PathVariable("id") String id) {
        videoService.increaseCount(id);
        return ResponseEntity.ok().build();
    }

    }