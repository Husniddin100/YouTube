package com.example.YouTube.repository;

import com.example.YouTube.entity.VideoLikeEntity;
import com.example.YouTube.enums.VideoLikeType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VideoLikeRepository extends CrudRepository<VideoLikeEntity, Integer> {
    @Query("from VideoLikeEntity where videoId=?1 and profileId=?2 and likeType=?3 ")
    Optional<Boolean> findByVideoLikedProfile(String videoId, Integer profileId, VideoLikeType type);

    @Query("from VideoLikeEntity where videoId=?1 and profileId=?2")
    Optional<Boolean> findByVideoIdAndProfileId(String videoId, Integer profileId);

    @Modifying
    @Transactional
    @Query("delete from VideoLikeEntity where videoId=?1 and profileId=?2")
    void deleteLike(String videoId, Integer profileId);

    List<VideoLikeEntity> findAllByProfileIdAndLikeTypeOrderByCreatedDate(Integer profileId, VideoLikeType videoLikeType);
}
