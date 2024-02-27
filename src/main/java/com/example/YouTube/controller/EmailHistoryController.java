package com.example.YouTube.controller;

import com.example.YouTube.dto.EmailHistoryDTO;
import com.example.YouTube.dto.PageEmailHistoryDTO;
import com.example.YouTube.service.EmailHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("email_history")
public class EmailHistoryController {
    @Autowired
    private EmailHistoryService emailHistoryService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getPagination")
    public ResponseEntity<Page<EmailHistoryDTO>> getListByPagination(@RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<EmailHistoryDTO> result = emailHistoryService.getListPagination(page, size);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/getByEmailPagination")
    public ResponseEntity<Page<EmailHistoryDTO>> getListByEmailPagination(@RequestParam(value = "page", defaultValue = "1") Integer page
            , @RequestParam(value = "size", defaultValue = "10") Integer size
            , @RequestBody PageEmailHistoryDTO dto) {
        Page<EmailHistoryDTO> result = emailHistoryService.getListByEmailPagination(page, size, dto);
        return ResponseEntity.ok(result);
    }
    ///// Filter method
    ////
}
