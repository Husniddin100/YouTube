package com.example.YouTube.controller;

import com.example.YouTube.dto.TagDTO;
import com.example.YouTube.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    private TagService tagService;

    @PostMapping("/create")
    public ResponseEntity<TagDTO> createTag(@RequestBody TagDTO dto) {
        return ResponseEntity.ok(tagService.createTag(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> updateTag(@PathVariable Integer id, @RequestBody TagDTO dto) {
        return ResponseEntity.ok(tagService.updateTag(id, dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/deleteTag/{id}")
    public ResponseEntity<Boolean> deleteTag(@PathVariable Integer id) {
        return ResponseEntity.ok(tagService.deleteTag(id));
    }

    @GetMapping("/tagList")
    public ResponseEntity<List<TagDTO>> tagList() {
        List<TagDTO> list = tagService.tagList();
        return ResponseEntity.ok(list);
    }
}
