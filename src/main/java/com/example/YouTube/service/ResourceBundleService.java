package com.example.YouTube.service;


<<<<<<< HEAD
import com.example.YouTube.enums.LanguageEnums;
=======
import com.example.YouTube.enums.LangEnum;
>>>>>>> origin/master
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ResourceBundleService {
    @Autowired
    private ResourceBundleMessageSource resourceBundleMessageSource;
<<<<<<< HEAD
    public String getMessage(String code, LanguageEnums appLanguage) {
        return resourceBundleMessageSource.getMessage("email.password.wrong", null, new Locale(appLanguage.name()));
    }

=======

    public  String getMessage(String code, LangEnum appLanguage) {
        return resourceBundleMessageSource.getMessage(code, null, new Locale(appLanguage.name()));
    }
>>>>>>> origin/master
}
