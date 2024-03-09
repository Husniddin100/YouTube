package com.example.YouTube.service;

import com.example.YouTube.dto.TagDTO;
import com.example.YouTube.entity.TagEntity;
import com.example.YouTube.enums.LangEnum;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private ResourceBundleService resourceBundleService;

    public TagDTO createTag(TagDTO dto, LangEnum lang) {
        TagEntity entity = new TagEntity();
        if (!dto.getName().startsWith("#")) {
            entity.setName("#" + dto.getName());
        } else {
            entity.setName(dto.getName());
        }
        entity.setCreatedDate(LocalDateTime.now());
        tagRepository.save(entity);

        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public Boolean updateTag(Integer id, TagDTO dto, LangEnum lang) {
        Optional<TagEntity> optional = tagRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("tag.not.found",lang));
        }
        TagEntity entity = optional.get();
        if (!dto.getName().startsWith("#")) {
            entity.setName("#" + dto.getName());
        } else {
            entity.setName(dto.getName());
        }
        tagRepository.save(entity);
        return true;
    }

    public Boolean deleteTag(Integer id, LangEnum lang) {
        Optional<TagEntity> optional = tagRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException(resourceBundleService.getMessage("tag.not.found",lang));
        }
        tagRepository.deleteById(id);
        return true;
    }

    public List<TagDTO> tagList() {
        Iterable<TagEntity> tagList = tagRepository.findAll();
        List<TagDTO> dtoList = new LinkedList<>();
        for (TagEntity entity : tagList) {
            dtoList.add(ToDTO(entity));
        }
        return dtoList;
    }

    private TagDTO ToDTO(TagEntity entity) {
        TagDTO dto = new TagDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
