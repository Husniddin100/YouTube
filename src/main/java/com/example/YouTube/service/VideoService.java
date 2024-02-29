package com.example.YouTube.service;

import com.example.YouTube.dto.ChannelDTO;
import com.example.YouTube.dto.ProfileDTO;
import com.example.YouTube.dto.VideoDTO;
import com.example.YouTube.dto.VideoListPaginationDTO;
import com.example.YouTube.entity.VideoEntity;
import com.example.YouTube.enums.LangEnum;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.mapper.VideoShortInfoMapper;
import com.example.YouTube.mapper.VideoShortInfoPaginationMapper;
import com.example.YouTube.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private AttachService attachService;

    public VideoDTO create(VideoDTO dto) {
        VideoEntity entity = new VideoEntity();
        entity.setTitle(dto.getTitle());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setPublishedDate(LocalDateTime.now());
        entity.setStatus(dto.getStatus());
        entity.setTypeStatus(dto.getTypeStatus());
        entity.setViewCount(dto.getViewCount());
        entity.setShareCount(dto.getShareCount());
        entity.setDescription(dto.getDescription());
        videoRepository.save(entity);

        dto.setId(entity.getId());
        return dto;

    }

    public Boolean update(String id, VideoDTO dto) {
        Optional<VideoEntity> optional = videoRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("video not found");
        }
        VideoEntity entity = get(id);
        entity.setTitle(dto.getTitle());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setPublishedDate(dto.getPublishedDate());
        entity.setStatus(dto.getStatus());
        entity.setTypeStatus(dto.getTypeStatus());
        entity.setViewCount(dto.getViewCount());
        entity.setShareCount(dto.getShareCount());
        entity.setDescription(dto.getDescription());
        videoRepository.save(entity);
        return true;
    }

    public void increaseCount(String id) {
        Optional<VideoEntity> optional = videoRepository.findById(id);
        if (optional.isPresent()) {
            VideoEntity entity = optional.get();
            entity.setViewCount(entity.getViewCount() + 1);
            videoRepository.save(entity);
        } else {
            throw new AppBadException("id is not found");
        }
    }

  /*  public PageImpl pagination(Integer page, Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable pageable = (Pageable) PageRequest.of(page - 1, size, sort);

        Page<VideoEntity> entityPage = videoRepository.findAll(pageable);
//        Page<VideoShortInfo> findByCategoryId(Long categoryId, Pageable pageable);
        long totalElements = entityPage.getTotalElements();

        List<VideoDTO> dtoList = new LinkedList<>();
        for (VideoEntity entity : entityPage) {
            dtoList.add(toDTO(entity));
        }

        return new PageImpl<>(dtoList, pageable, totalElements);
    }
*/
    public VideoDTO toDTO(VideoEntity entity) {
        VideoDTO dto = new VideoDTO();

        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setCategoryId(entity.getCategoryId().getId());
        dto.setAttachId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setTypeStatus(entity.getTypeStatus());
        dto.setViewCount(entity.getViewCount());
        dto.setShareCount(entity.getShareCount());
        dto.setDescription(entity.getDescription());

        return dto;
    }

    public VideoEntity get(String id) {
        return videoRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Region not found");
        });
    }

   // incomplete method
   /* public Page<VideoShortInfoMapper> searchVideosByTitle(String title, Pageable pageable) {
        return videoRepository.findByTitleContaining(title, pageable);
    }

    public Page<VideoShortInfoMapper> getVideosByTagIdWithPagination(Long tagId, Pageable pageable) {
        return videoRepository.findByTagId(tagId, pageable);
    }*/

  /*  public PageImpl<VideoListPaginationDTO> getVideoListForAdmin(Integer page, Integer size, LangEnum language) {
        Pageable pageable = (Pageable) PageRequest.of(page - 1, size);
        Page<VideoShortInfoPaginationMapper> entityPage = videoRepository.getVideoListForAdmin(pageable);

        List<VideoShortInfoPaginationMapper> entityList = entityPage.getContent();
        long totalElements = entityPage.getTotalElements();

        List<VideoListPaginationDTO> dtoList = new LinkedList<>();
        for (VideoShortInfoPaginationMapper entity : entityList) {
            VideoDTO videoDTO = new VideoDTO();
            videoDTO.setId(entity.getId());
            videoDTO.setTitle(entity.geTitle());
            if (entity.getPreviewAttachId() != null) {
                videoDTO.setPreviewAttach(attachService.getURL(entity.getPreviewAttachId()));
            }
            videoDTO.setPublishedDate(entity.getPublishedDate());

            ChannelDTO channelDTO = new ChannelDTO();
            channelDTO.setId(entity.getChannelId());
            channelDTO.setName(entity.getChannelName());
            channelDTO.setPhotoId(entity.getPhotoId());
            videoDTO.setChannel(channelDTO);

            ProfileDTO profileDTO = new ProfileDTO();
            profileDTO.setId(entity.getProfileId());
            profileDTO.setName(entity.getProfileName());
            profileDTO.setSurname(entity.getProfileSurname());
            videoDTO.setOwner(profileDTO);

            videoDTO.setPlayListJson(entity.getPlayListJson());
        }
        return new PageImpl<>(dtoList, (org.springframework.data.domain.Pageable) pageable, totalElements);
    }*/
}