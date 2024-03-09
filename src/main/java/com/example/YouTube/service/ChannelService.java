package com.example.YouTube.service;

import com.example.YouTube.dto.ChannelDTO;
import com.example.YouTube.entity.ChannelEntity;
import com.example.YouTube.enums.ChannelStatus;
import com.example.YouTube.enums.LangEnum;
import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ResourceBundleService resourceBundleService;

    public ChannelDTO createChannel(ChannelDTO dto, LangEnum lang) {
        Optional<ChannelEntity> optional = channelRepository.findId(dto.getProfileId());
        if (optional.isPresent()) {
            throw new AppBadException(resourceBundleService.getMessage("you.have.already.created.a.channel", lang));
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

    public ChannelDTO update(String channelId, ChannelDTO dto, Integer profileId, LangEnum lang) {
        Optional<ChannelEntity> optional = channelRepository.findById(channelId);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("channel.not.found",lang));
        }
        // check channel owner
        Optional<ChannelEntity> checkOwner = channelRepository.findOwner(profileId, channelId);
        if (checkOwner.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("You.are.not.the.owner.of.the.channel",lang));
        }
        ChannelEntity entity = optional.get();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        channelRepository.save(entity);
        return dto;
    }

    public boolean updatePhoto(String channelId, String photoId, Integer profileId, LangEnum lang) {
        Optional<ChannelEntity> optional = channelRepository.findById(channelId);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("channel.not.found",lang));
        }
        // check channel owner
        Optional<ChannelEntity> checkOwner = channelRepository.findOwner(profileId, channelId);
        if (checkOwner.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("You.are.not.the.owner.of.the.channel",lang));
        }
        ChannelEntity entity = optional.get();
        entity.setPhotoId(photoId);
        channelRepository.save(entity);
        return true;
    }

    public Boolean updateBanner(String channelId, String bannerId, Integer profileId, LangEnum lang) {
        Optional<ChannelEntity> optional = channelRepository.findById(channelId);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("channel.not.found",lang));
        }
        // check channel owner
        Optional<ChannelEntity> checkOwner = channelRepository.findOwner(profileId, channelId);
        if (checkOwner.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("You.are.not.the.owner.of.the.channel",lang));
        }
        ChannelEntity entity = optional.get();
        entity.setBannerId(bannerId);
        channelRepository.save(entity);
        return true;
    }

    public PageImpl getAll(Integer page, Integer size, LangEnum lang) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");

        Pageable paging = PageRequest.of(page - 1, size, sort);
        Page<ChannelEntity> channelPage = channelRepository.findAll(paging);

        List<ChannelEntity> entityList = channelPage.getContent();
        Long totalElements = channelPage.getTotalElements();

        List<ChannelDTO> dtoList = new LinkedList<>();
        for (ChannelEntity entity : entityList) {
            dtoList.add(toDTO(entity));
        }
        return new PageImpl<>(dtoList, paging, totalElements);
    }

    public Optional<ChannelEntity> getById(String id, LangEnum lang) {
        Optional<ChannelEntity> optional = channelRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("channel.not.found",lang));
        }
        return optional;
    }

    public Boolean changeStatus(String channelId, Integer profileId, ProfileRole role, LangEnum lang) {
        Optional<ChannelEntity> optional = channelRepository.findById(channelId);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("channel.not.found",lang));
            }
        // check role admin
        if (!role.equals(ProfileRole.ROLE_ADMIN)) {
            Optional<ChannelEntity> checkOwner = channelRepository.findOwner(profileId, channelId);
            // check channel owner
            if (checkOwner.isEmpty()) {
                throw new AppBadException(resourceBundleService.getMessage("You.are.not.the.owner.of.the.channel",lang));
            }
        }
        ChannelEntity entity = optional.get();
        if (entity.getStatus().equals(ChannelStatus.ACTIVE)) {
            entity.setStatus(ChannelStatus.BLOCK);
        } else {
            entity.setStatus(ChannelStatus.ACTIVE);
        }
        channelRepository.save(entity);
        return true;
    }

    public ChannelDTO toDTO(ChannelEntity entity) {
        ChannelDTO dto = new ChannelDTO();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPhotoId(entity.getPhotoId());
        dto.setStatus(entity.getStatus());
        dto.setBannerId(entity.getBannerId());
        dto.setProfileId(entity.getProfileId());
        dto.setSubscriptionCount(entity.getSubscriptionCount());
        return dto;
    }
}