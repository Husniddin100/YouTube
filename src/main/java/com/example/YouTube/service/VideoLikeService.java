package com.example.YouTube.service;

import com.example.YouTube.dto.*;
import com.example.YouTube.entity.AttachEntity;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.entity.VideoEntity;
import com.example.YouTube.entity.VideoLikeEntity;
import com.example.YouTube.enums.VideoLikeType;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.VideoLikeRepository;
import com.example.YouTube.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class VideoLikeService {

    @Autowired
    private VideoLikeRepository videoLikeRepository;
    @Autowired
    private VideoRepository videoRepository;

    public VideoLikeDTO create(VideoLikeDTO dto) {
        Optional<Boolean> findVideo = videoRepository.findByVideoId(dto.getVideoId());
        if (findVideo.isEmpty()) {
            throw new AppBadException("video not found");
        }
        Optional<Boolean> optional = videoLikeRepository.findByVideoLikedProfile(dto.getVideoId(), dto.getProfileId(), dto.getType());
        if (optional.isPresent()) {
            remove(dto.getVideoId(), dto.getProfileId());
            return dto;
        }
        Optional<Boolean> optional1 = videoLikeRepository.findByVideoIdAndProfileId(dto.getVideoId(), dto.getProfileId());
        if (optional1.isPresent()) {
            remove(dto.getVideoId(), dto.getProfileId());
        }

        VideoLikeEntity entity = new VideoLikeEntity();
        entity.setProfileId(dto.getProfileId());
        entity.setVideoId(dto.getVideoId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setLikeType(dto.getType());
        videoLikeRepository.save(entity);

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setId(entity.getId());
        return dto;

    }

    public Boolean remove(String videoId, Integer profileId) {
        Optional<Boolean> optional = videoLikeRepository.findByVideoIdAndProfileId(videoId, profileId);
        if (optional.isEmpty()) {
            throw new AppBadException("the video has not been liked");
        }
        videoLikeRepository.deleteLike(videoId, profileId);
        return true;
    }
    // tugatilmagan
/*
    public List<VideoLikeInfoDTO> getLikedVideos(Integer profileId) {
        List<VideoLikeEntity> liked = videoLikeRepository.findAllByProfileIdAndLikeTypeOrderByCreatedDate(profileId, VideoLikeType.LIKE);

        List<VideoLikeInfoDTO> result = new ArrayList<>();
        for (VideoLikeEntity videoLikeEntity : liked) {
            result.add(getVideoLikeInfo(videoLikeEntity));
        }

        return result;
    }
*/


}
