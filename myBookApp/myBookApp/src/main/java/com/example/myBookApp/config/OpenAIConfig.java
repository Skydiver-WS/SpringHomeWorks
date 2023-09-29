package com.example.myBookApp.config;

import com.example.myBookApp.controllers.MainPageController;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
      Что бы не использовать @RestController для формирования документации
   OpenApi 3 можно использовать данную конфигурацию
 */
@Configuration
public class OpenAIConfig {
    static {
        SpringDocUtils.getConfig().addRestControllers(MainPageController.class);
    }
}
