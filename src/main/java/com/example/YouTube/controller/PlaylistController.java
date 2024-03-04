package com.example.YouTube.controller;

import com.example.YouTube.dto.PlaylistDTO;
import com.example.YouTube.enums.LanguageEnums;
import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.service.PlaylistService;
import com.example.YouTube.util.HttpRequestUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("playlist")
public class PlaylistController {
    @Autowired
    private PlaylistService playlistService;

    @PostMapping("/create")
    public ResponseEntity<PlaylistDTO> create(@RequestBody PlaylistDTO dto,
                                              HttpServletRequest request) {
        HttpRequestUtil.getProfileId(request, ProfileRole.ROLE_USER);
        return ResponseEntity.ok(playlistService.create(dto));

    }

    @PostMapping("/user_owner/change_status")
    public ResponseEntity<Boolean> change_status(@PathVariable("status") String status,
                                                 @PathVariable("num") String num,
                                                 @RequestHeader LanguageEnums languageEnums) {
        return ResponseEntity.ok(playlistService.change_status(status, num, languageEnums));
    }

    @PostMapping("/userAndOwnerAndAdmin/delete")
    public ResponseEntity<String> delete(@PathVariable ("orderNumber") Integer orderNumber,LanguageEnums languageEnums,
                                         HttpServletRequest request ) {
      Integer id=  HttpRequestUtil.getProfileId(request,ProfileRole.ROLE_USER,ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(playlistService.delete(orderNumber,id,languageEnums));


    }

    public ResponseEntity<PageImpl<PlaylistDTO>> filter(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                        @RequestParam(value = "size", defaultValue = "2") Integer size,
                                                        @RequestBody Filter filter) {

        Pageable pageable = PageRequest.of(page - 1, size, Sort.Direction.DESC, "createdDate");
        return ResponseEntity.ok(playlistService.filter(filter, pageable));
    }
}

