package com.example.YouTube.service;

import com.example.YouTube.dto.ChannelDTO;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.enums.ChannelStatus;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;

    public ChannelDTO createChannel(ChannelDTO dto) {
        Optional<ChannelEntity> optional = channelRepository.findById(String.valueOf(dto.getProfileId()));
        if (optional.isPresent()) {
            throw new AppBadException("you have already created a channel");
        }
        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setPhotoId(dto.getPhotoId());
        entity.setDescription(dto.getDescription());
        entity.setStatus(ChannelStatus.ACTIVE);
        entity.setBannerId(dto.getBannerId());
        entity.setProfileId(dto.getProfileId());
        channelRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

}
