package com.example.YouTube.service;


import com.example.YouTube.enums.LanguageEnums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ResourceBundleService {
    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;
    public String getMessage(String code, LanguageEnums appLanguage) {
        return resourceBundleMessageSource.getMessage("email.password.wrong", null, new Locale(appLanguage.name()));
    }

}
