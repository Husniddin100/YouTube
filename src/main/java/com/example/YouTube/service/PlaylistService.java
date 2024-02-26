package com.example.YouTube.service;

import com.example.YouTube.dto.PlaylistDTO;
import com.example.YouTube.entity.PlaylistEntity;
import com.example.YouTube.enums.LanguageEnums;
import com.example.YouTube.enums.PlaylistStatus;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.PlaylistRepository;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    private ResourceBundleService resourceBundleService;
    @Autowired
    private PlaylistRepository playlistRepository;
    public PlaylistDTO create(PlaylistDTO dto) {

        PlaylistEntity entity=new PlaylistEntity();
        entity.setChannel_id(dto.getChannelId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setOrder_num(dto.getOrderNumber());
        entity.setStatus(toEntity(entity,dto).getStatus());

        playlistRepository.save(entity);
        return toDTO(entity);
    }
    private PlaylistEntity toEntity(PlaylistEntity entity,PlaylistDTO dto){

        if(String.valueOf(PlaylistStatus.PUBLIC).toLowerCase().equals(dto.getStatus())) {
             entity.setStatus(PlaylistStatus.PUBLIC);
             return entity;
        }
        entity.setStatus(PlaylistStatus.PRIVATE);
        return entity;
    }
    private PlaylistDTO toDTO(PlaylistEntity entity){
        PlaylistDTO dto=new PlaylistDTO();
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public Boolean change_status(String status, String num, LanguageEnums languageEnums) {

        Optional<PlaylistEntity> optional=playlistRepository.getByOrder_num(num);
        if (optional.isPresent()){
           /* PlaylistEntity entity=optional.get();
            if(entity.getStatus().equals(PlaylistStatus.PRIVATE))*/
            playlistRepository.change_status(status,num);

        }
        resourceBundleService.getMessage("Item.not.found",languageEnums);
        throw new AppBadException("Item not found");
    }

    public String delete(Integer orderNumber, Integer id,LanguageEnums languageEnums) {
   Boolean b=getByIdProfileEntity(orderNumber ,id,languageEnums);
   if(b){
       resourceBundleService.getMessage("ok",languageEnums);
   }
   resourceBundleService.getMessage("server.error",languageEnums);
   throw new AppBadException("item not found");
    }

    private   Boolean getByIdProfileEntity(Integer orderNumber,Integer id,LanguageEnums languageEnums){
        List<PlaylistEntity> entityList=playlistRepository.getByProfileId(id);

        for (PlaylistEntity entity:entityList){
          if(  entity.getOrder_num().equals(orderNumber)) {
             Boolean b= playlistRepository.deleteByOrder_num(entity.getId());
return b;
          }
        }
        resourceBundleService.getMessage("item.not.found",languageEnums);
        throw  new AppBadException("SERVER ERROR");
    }

    public PageImpl filter(Filter filter, Pageable pageable) {
        return null;
    }
}
