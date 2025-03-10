package com.github.helf4ch.textstat.config;

import com.github.helf4ch.textstat.nlp.LangDetector;
import com.github.helf4ch.textstat.nlp.lang.EnglishProvider;
import com.github.helf4ch.textstat.nlp.lang.RussianProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public LangDetector langDetector() {
    return new LangDetector();
  }

  @Bean
  public EnglishProvider englishProvider() {
    return new EnglishProvider();
  }

  @Bean
  public RussianProvider russianProvider() {
    return new RussianProvider();
  }
}
