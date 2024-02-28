package com.example.YouTube.dto;

import java.util.List;

public class VideoListPaginationDTO<T> {
    private Long totalSize;
    private List<T> list;

    public VideoListPaginationDTO(Long totalSize, List<T> list) {
        this.totalSize = totalSize;
        this.list = list;
    }
}
