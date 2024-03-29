package com.example.YouTube.controller;

import com.example.YouTube.dto.VideoLikeDTO;
import com.example.YouTube.dto.VideoLikeInfoDTO;
import com.example.YouTube.enums.LangEnum;
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
    public ResponseEntity<VideoLikeDTO> create(@RequestBody VideoLikeDTO dto,
                                               @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        return ResponseEntity.ok(videoLikeService.create(dto, lang));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/getLikeVideoList")
    public ResponseEntity<List<VideoLikeInfoDTO>> getLikeVideoList() {
        Integer profileId = SpringSecurityUtil.getCurrentUser().getId();
        List<VideoLikeInfoDTO> list = videoLikeService.getLikedVideos(profileId);
        return ResponseEntity.ok(list);
    }
}
