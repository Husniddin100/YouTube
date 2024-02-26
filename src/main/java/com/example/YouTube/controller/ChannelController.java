package com.example.YouTube.controller;

import com.example.YouTube.dto.ChannelDTO;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.enums.ChannelStatus;
import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.service.ChannelService;
import com.example.YouTube.util.SpringSecurityUtil;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/create")
    public ResponseEntity<ChannelDTO> createChannel(@RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.createChannel(dto));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/update/{channelId}")
    public ResponseEntity<ChannelDTO> updateChannel(@PathVariable String channelId, @RequestBody ChannelDTO dto) {
        Integer profileId = SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(channelService.update(channelId, dto, profileId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updatePhoto/{channelId}/{photoId}")
    public ResponseEntity<Boolean> updatePhoto(@PathVariable String channelId, @PathVariable String photoId) {
        Integer profileId = SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(channelService.updatePhoto(channelId, photoId, profileId));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/updateBanner/{channelId}/{bannerId}")
    public ResponseEntity<Boolean> updateBanner(@PathVariable String channelId, @PathVariable String bannerId) {
        Integer profileId = SpringSecurityUtil.getCurrentUser().getId();
        return ResponseEntity.ok(channelService.updateBanner(channelId, bannerId, profileId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllPagination")
    public ResponseEntity<PageImpl> getAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return ResponseEntity.ok(channelService.getAll(page, size));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<Optional<ChannelEntity>> getById(@PathVariable String id) {
        return ResponseEntity.ok(channelService.getById(id));
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping("/changeStatus/{channelId}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable String channelId) {
        Integer profileId = SpringSecurityUtil.getCurrentUser().getId();
        ProfileRole role = SpringSecurityUtil.getCurrentUser().getRole();
        return ResponseEntity.ok(channelService.changeStatus(channelId, profileId, role));
    }
}
