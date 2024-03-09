package com.example.YouTube.controller;

import com.example.YouTube.dto.VideoTagDTO;
import com.example.YouTube.dto.VideoTagShortInfoDTO;
import com.example.YouTube.enums.LangEnum;
import com.example.YouTube.service.VideoTagService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("video_tag")
public class VideoTagController {
    @Autowired
    private VideoTagService videoTagService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/addTag")
    public ResponseEntity<VideoTagDTO> addTag(@RequestBody VideoTagDTO dto,
                                              @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        return ResponseEntity.ok(videoTagService.create(dto, lang));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/delete")
    public ResponseEntity<Boolean> delete(@RequestBody VideoTagDTO dto,
                                          @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        return ResponseEntity.ok(videoTagService.delete(dto, lang));
    }

    @GetMapping("/getList/{videoId}")
    public ResponseEntity<List<VideoTagShortInfoDTO>> getVideTagList(@PathVariable String videoId,
                                                                     @RequestHeader(value = "Accept-Language", defaultValue = "uz") LangEnum lang) {
        List<VideoTagShortInfoDTO> list = videoTagService.getAllList(videoId, lang);
        return ResponseEntity.ok(list);
    }

}
