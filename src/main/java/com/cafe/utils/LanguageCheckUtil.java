package com.cafe.utils;

import org.springframework.stereotype.Component;
import com.cafe.exceptions.MessageException;

import java.util.Arrays;
import java.util.List;

@Component
public class LanguageCheckUtil {

    public void checkLanguageName(List<String> languageNames) throws MessageException {
        List<String> languages = Arrays.asList("en", "ru", "hy");
        if (!languages.containsAll(languageNames))
            throw new MessageException("Language name is not valid");
    }

    public void checkLanguageName(String languageName) throws MessageException {
        List<String> languages = Arrays.asList("en", "ru", "hy");
        if (!languages.contains(languageName))
            throw new MessageException("Language name is not valid");
    }

}