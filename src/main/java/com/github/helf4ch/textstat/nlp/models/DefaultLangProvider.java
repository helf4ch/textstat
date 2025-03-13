package com.github.helf4ch.textstat.nlp.models;

import opennlp.tools.tokenize.SimpleTokenizer;

public class DefaultLangProvider implements NlProcessor {
  public String[] tokenize(String text) {
    return SimpleTokenizer.INSTANCE.tokenize(text);
  }

  public String[] sentDetect(String text) {
    return text.split("(?<!\\w\\.\\w.)(?<!\\b[A-Z][a-z]\\.)(?<![A-Z]\\.)(?<=\\.|\\?)\\s|\\\\n");
  }
}
