package com.github.helf4ch.textstat;

import com.github.helf4ch.textstat.config.TextProperties;
import com.github.helf4ch.textstat.util.TextNormalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class TextstatApplication {

  public static void main(String[] args) {
    SpringApplication.run(TextstatApplication.class, args);
  }

  @Service
  static class Startup implements CommandLineRunner {
    @Autowired private TextProperties properties;
    @Autowired private TextNormalizer norm;

    @Override
    public void run(String... strings) throws Exception {
      System.out.println(properties.getStopWords());
      System.out.println(norm.normalize("Word1 and word2!"));
    }
  }
}
