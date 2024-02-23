package com.example.YouTube.controller;

import com.example.YouTube.dto.AttachDTO;
import com.example.YouTube.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        AttachDTO fileName = attachService.save(file);
        return ResponseEntity.ok().body(fileName);
    }
    @GetMapping(value = "/open/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {
        return attachService.open(fileName);
    }

    @GetMapping("/public/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        Resource file = attachService.download(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
