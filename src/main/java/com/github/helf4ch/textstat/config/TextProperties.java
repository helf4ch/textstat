package com.github.helf4ch.textstat.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("textstat.text")
public class TextProperties {
  private String stopWords = "";

  public String getStopWords() {
    return stopWords;
  }

  public void setStopWords(String stopWords) {
    this.stopWords = stopWords;
  }
}
