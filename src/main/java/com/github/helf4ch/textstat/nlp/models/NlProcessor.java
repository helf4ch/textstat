package com.github.helf4ch.textstat.nlp.models;

public interface NlProcessor {

  public String[] tokenize(String text);

  public String[] sentDetect(String text);
}
