package com.example.YouTube.service;

import com.example.YouTube.dto.TagShortDTO;
import com.example.YouTube.dto.VideoTagDTO;
import com.example.YouTube.dto.VideoTagShortInfoDTO;
import com.example.YouTube.entity.VideoTagEntity;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.TagRepository;
import com.example.YouTube.repository.VideoRepository;
import com.example.YouTube.repository.VideoTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoTagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private VideoTagRepository videoTagRepository;
    @Autowired
    private VideoRepository videoRepository;

    public VideoTagDTO create(VideoTagDTO dto) {
        VideoTagEntity entity = new VideoTagEntity();

        if (!tagRepository.existsById(dto.getTagId())) {
            throw new AppBadException("tag not found");
        }

        if (!videoRepository.existsById(dto.getVideoId())) {
            throw new AppBadException("video not found");
        }

        entity.setVideoId(dto.getVideoId());
        entity.setTagId(dto.getTagId());
        entity.setCreatedDate(LocalDateTime.now());

        videoTagRepository.save(entity);

        VideoTagDTO videotagDTO = new VideoTagDTO();
        videotagDTO.setId(entity.getId());
        videotagDTO.setTagId(entity.getTagId());
        videotagDTO.setVideoId(entity.getVideoId());
        videotagDTO.setCreatedDate(entity.getCreatedDate());
        return videotagDTO;
    }

    public Boolean delete(VideoTagDTO dto) {
        int result = videoTagRepository.deleteByVideoId(dto.getVideoId(), dto.getTagId());
        if (result == 0) {
            throw new AppBadException("error deleted");
        }
        return true;
    }

    public List<VideoTagShortInfoDTO> getAllList(String videoId) {
        List<VideoTagEntity> entityList = videoTagRepository.findByVideoId(videoId);

        List<VideoTagShortInfoDTO> dtoList = new ArrayList<>();

        for (VideoTagEntity entity : entityList) {
            VideoTagShortInfoDTO dto = new VideoTagShortInfoDTO();
            dto.setId(entity.getId());
            dto.setVideoId(entity.getVideoId());

            TagShortDTO tagShortDTO = new TagShortDTO();
            tagShortDTO.setId(entity.getTag().getId());
            tagShortDTO.setName(entity.getTag().getName());

            dto.setTagShortDTO(tagShortDTO);
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return dtoList;
    }
}
