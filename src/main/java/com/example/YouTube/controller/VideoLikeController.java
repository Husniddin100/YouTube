package com.example.YouTube.controller;

import com.example.YouTube.dto.VideoLikeDTO;
import com.example.YouTube.dto.VideoLikeInfoDTO;
import com.example.YouTube.enums.VideoLikeType;
import com.example.YouTube.service.VideoLikeService;
import com.example.YouTube.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/video_like")
public class VideoLikeController {
    @Autowired
    private VideoLikeService videoLikeService;

    @PostMapping("/create_like")
    public ResponseEntity<VideoLikeDTO> create(@RequestBody VideoLikeDTO dto) {
        return ResponseEntity.ok(videoLikeService.create(dto));
    }

   /* @PreAuthorize("hasRole('USER')")
    @GetMapping("/userLiked_list")
    public ResponseEntity<List<VideoLikeInfoDTO>> getLikedVideos() {
        Integer userId = SpringSecurityUtil.getCurrentUser().getId();
        List<VideoLikeInfoDTO> result = videoLikeService.getLikedVideos(userId);
        return ResponseEntity.ok(result);
    }*/

}
