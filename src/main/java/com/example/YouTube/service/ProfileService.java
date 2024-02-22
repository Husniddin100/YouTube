package com.example.YouTube.service;

import com.example.YouTube.dto.CreatProfileDto;
import com.example.YouTube.dto.JwtDTO;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.LanguageEnums;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.ProfileRepository;
import com.example.YouTube.util.JWTUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    public CreatProfileDto change_password(CreatProfileDto dto,  LanguageEnums languageEnums) {

        Optional<ProfileEntity>optional= profileRepository.findByEmail(dto.getEmail());
        if (optional.isEmpty()){
            new AppBadException("Topilmadi");
        }
        ProfileEntity entity= optional.get();

        return null;
    }
}
