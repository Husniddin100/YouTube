package com.example.YouTube.repository;

import com.example.YouTube.entity.VideoTagEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VideoTagRepository extends CrudRepository<VideoTagEntity,Integer> {
    @Modifying
    @Transactional
    @Query("delete from VideoTagEntity v where v.videoId=?1 and v.tagId=?2")
    int deleteByVideoId(String videoId, Integer tagId);
   @Query("from VideoTagEntity where videoId=?1")
    List<VideoTagEntity> findByVideoId(String videoId);
}
