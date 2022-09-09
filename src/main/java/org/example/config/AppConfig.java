package org.example.config;

import org.example.DictionaryLatin;
import org.example.DictionaryNumber;
import org.example.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.io.FileNotFoundException;

@Configuration
@ComponentScan
public class AppConfig {

    private static final String PATH_TO_DICTIONARY_LATIN = "target\\classes\\dictionaryLatin.txt";
    private static final String PATH_TO_DICTIONARY_NUMBER = "target\\classes\\dictionaryNumber.txt";

    @Bean
    DictionaryService dictionaryService(@Autowired DictionaryLatin dictionaryLatin,@Autowired DictionaryNumber dictionaryNumber){
        return new DictionaryService(dictionaryLatin, dictionaryNumber);
    }

    @Bean
    DictionaryLatin dictionaryLatin(){
        try {
            return new DictionaryLatin(PATH_TO_DICTIONARY_LATIN);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    DictionaryNumber dictionaryNumber(){
        try {
            return new DictionaryNumber(PATH_TO_DICTIONARY_NUMBER);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
