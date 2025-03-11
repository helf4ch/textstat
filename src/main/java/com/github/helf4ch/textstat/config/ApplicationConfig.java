package com.github.helf4ch.textstat.config;

import com.github.helf4ch.textstat.nlp.NlpProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

  @Bean
  public NlpProvider nlpProvider() {
    return new NlpProvider();
  }
}
