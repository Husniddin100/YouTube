package com.example.YouTube.service;

import com.example.YouTube.dto.AuthDTO;
import com.example.YouTube.dto.CreateProfileDTO;
import com.example.YouTube.dto.JwtDTO;
import com.example.YouTube.dto.ProfileDTO;
import com.example.YouTube.entity.ProfileEntity;
import com.example.YouTube.enums.ProfileRole;
import com.example.YouTube.enums.ProfileStatus;
import com.example.YouTube.exp.AppBadException;
import com.example.YouTube.repository.ProfileRepository;
import com.example.YouTube.util.JWTUtil;
import com.example.YouTube.util.MDUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private ProfileRepository profileRepository;
    public ProfileDTO login(AuthDTO profile) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                MDUtil.encode(profile.getPassword()));

        if (optional.isEmpty()) {
            throw new AppBadException("email or password.wrong");
        }

        ProfileEntity entity = optional.get();

        ProfileDTO dto = new ProfileDTO();

        if (entity.getVisible().equals(false)) {
            throw new AppBadException("Account.not.found");
        }

        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());
        dto.setJwt(JWTUtil.encode(entity.getEmail(), entity.getRole()));
        return dto;
    }

    public Boolean registration(CreateProfileDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                profileRepository.delete(optional.get());
            } else {
                throw new AppBadException("Email exists");
            }
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setRole(ProfileRole.ROLE_USER);
        profileRepository.save(entity);
        String jwt = JWTUtil.encodeForEmail(entity.getId());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8081/auth/verification/email/" + jwt;
        mailSenderService.sendEmail(dto.getEmail(), "Complete registration", text);
        return true;
    }
    public String emailVerification(String jwt) {
        try {
            JwtDTO jwtDTO = JWTUtil.decode(jwt);
            Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
            if (!optional.isPresent()) {
                throw new AppBadException("Profile not found");
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("Profile in wrong status");
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException("Please tyre again.");
        }
        return null;
    }
}
