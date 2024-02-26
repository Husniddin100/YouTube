package com.example.YouTube.repository;

import com.example.YouTube.entity.ChannelEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChannelRepository extends CrudRepository<ChannelEntity,String> {
    @Query("from ChannelEntity c where c.profileId=?1")
    Optional<ChannelEntity> findId(Integer profileId);
    @Query("from ChannelEntity c where c.profileId=?1 and c.id=?2")
    Optional<ChannelEntity> findOwner(Integer profileId, String id);
}
