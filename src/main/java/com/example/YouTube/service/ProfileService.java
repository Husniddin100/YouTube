package com.example.YouTube.service;

import com.example.YouTube.dto.CreatProfileDto;
import com.example.YouTube.dto.JwtDTO;
import com.example.YouTube.entity.EmailSendHistoryEntity;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.LanguageEnums;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.EmailSenderRepository;
import com.example.YouTube.repository.ProfileRepository;
import com.example.YouTube.util.JWTUtil;
import com.example.YouTube.util.MDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Slf4j
@Service
public class ProfileService {
    @Autowired
    private EmailSenderRepository emailSenderRepository;
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private  ResourceBundleService resourceBundleService;

    public CreatProfileDto change_password(CreatProfileDto dto,  LanguageEnums languageEnums) {

        Optional<ProfileEntity>optional= profileRepository.findByEmail(dto.getEmail());
        if (optional.isEmpty()){
            resourceBundleService.getMessage("email.password.wrong",languageEnums);
            log.warn("Email or Password is wrong {}", dto.getEmail());
            throw new AppBadException("Email or Password is wrong");
        }
        LocalDateTime from = LocalDateTime.now().minusMinutes(1);
        LocalDateTime to = LocalDateTime.now();

        if (emailSenderRepository.countSendEmail(dto.getEmail(), from, to) >= 3) {
            throw new AppBadException("To many attempt. Please try after 1 minute.");
        }
        ProfileEntity entity= optional.get();
        if (dto.getPassword()!=null && MDUtil.encode(entity.getPassword()).equals(dto.getPassword())){
          Boolean b=profileRepository.updatePassword(dto.getEmail(),dto.getNewPassword());
          if (!b){
              resourceBundleService.getMessage("server.error",languageEnums);
           throw  new AppBadException("SERVER ERROR");
          }

        }

      /*  String jwt = JWTUtil.encode(entity.getEmail(),entity.getRole());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8081/verification/emailChange/" + jwt;
        mailSenderService.sendEmail(dto.getEmail(), "Complete registration", text);

*/
        return null;
    }

    public void verificationChange(String jwt, LanguageEnums languageEnums) {

    }
}
