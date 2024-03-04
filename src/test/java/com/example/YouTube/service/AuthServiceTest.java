package com.example.YouTube.service;

import com.example.YouTube.repository.ProfileRepository;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
@Setter
public class AuthServiceTest {

    private AuthService underTest;
    @Mock
    private MailSenderService mailSenderService;
    @Mock
    private ProfileRepository profileRepository;
    @Mock
    private ResourceBundleService resourceBundleService;

    @BeforeEach
    public void init(){
        underTest=new AuthService();
        underTest.se
    }
}
