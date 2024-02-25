package com.example.YouTube.repository;

import com.example.YouTube.entity.ChannelEntity;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<ChannelEntity,String> {
}
