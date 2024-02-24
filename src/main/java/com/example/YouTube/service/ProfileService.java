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
        EmailSendHistoryEntity emailSendHistoryEntity = new EmailSendHistoryEntity();

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
        if (dto.getPassword()!=null ) {
            if (MDUtil.encode(entity.getPassword()).
                    equals(dto.getPassword())) {
                emailSendHistoryEntity.setEmail(entity.getEmail());
                emailSendHistoryEntity.setPassword(dto.getNewPassword());
                profileRepository.updatePassword(dto.getEmail(), dto.getNewPassword());
                emailSenderRepository.updateVisible(entity.getEmail());
                 return dto;
            }
            resourceBundleService.getMessage("password.error", languageEnums);
            throw new AppBadException("PASSWORD ERROR");

        }
        emailSendHistoryEntity.setEmail(entity.getEmail());
        emailSendHistoryEntity.setPassword(dto.getNewPassword());

        String jwt = JWTUtil.encode(entity.getId(),entity.getRole());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8081/verification/emailChange/" + jwt;
        mailSenderService.sendEmail(dto.getEmail(), "Complete registration", text);
        return null;
    }

    public void verificationChange(String jwt, LanguageEnums languageEnums) {
       JwtDTO jwtDTO=JWTUtil.decode(jwt);
ProfileEntity entity=getId(jwtDTO.getId());

EmailSendHistoryEntity emailSendHistoryEntity=searchEmail(entity.getEmail(), languageEnums);

profileRepository.updatePassword(entity.getEmail(), emailSendHistoryEntity.getPassword());
emailSenderRepository.updateVisible(entity.getEmail());
    }

    public Boolean updateNameAndSurname(String name, String surname, Integer id) {
        ProfileEntity entity=getId(id);
        profileRepository.updateNameAndSurname(name,surname,id);
        return true;
    }
    public ProfileEntity getId(Integer id){
        Optional<ProfileEntity>optional=profileRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        throw  new AppBadException("ID NOT FOUND");
    }

    public Boolean updateEmail(String email, String newEmail,String password,LanguageEnums languageEnums) {
Optional<ProfileEntity>optional=profileRepository.findByEmailAndPassword(email,password);
        if (optional.isEmpty()){
            resourceBundleService.getMessage("email.password.wrong",languageEnums);
            throw new AppBadException("Email or Password is wrong");
}
        ProfileEntity entity= optional.get();

        EmailSendHistoryEntity emailSendHistoryEntity=new EmailSendHistoryEntity();
        emailSendHistoryEntity.setEmail(email);
        emailSendHistoryEntity.setNew_email(newEmail);
        emailSendHistoryEntity.setPassword(password);

        String jwt = JWTUtil.encode(entity.getId(),entity.getRole());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8081/verification/emailChange/" + jwt;
        mailSenderService.sendEmail(entity.getEmail(), "Complete registration", text);
        return null;
    }

    public void emailChangeUpdateNewEmail(String jwt, LanguageEnums languageEnums) {
        JwtDTO jwtDTO=JWTUtil.decode(jwt);

        ProfileEntity entity=getId(jwtDTO.getId());
    EmailSendHistoryEntity emailSendHistoryEntity=searchEmail(entity.getEmail(),languageEnums);

    profileRepository.updateEmail(entity.getEmail(),emailSendHistoryEntity.getNew_email());
    emailSenderRepository.updateVisible(entity.getEmail());
    }
    public EmailSendHistoryEntity searchEmail(String email,LanguageEnums languageEnums){
        Optional<EmailSendHistoryEntity>optional=emailSenderRepository.findByEmail(email);
        if (optional.isPresent()){
            return optional.get();
        }
        resourceBundleService.getMessage("email.password.wrong",languageEnums);
        log.warn("Email or Password is wrong {}",email);
        throw new AppBadException("Email or Password is wrong");
    }
}
