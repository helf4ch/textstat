package com.github.helf4ch.textstat.nlp.lang;

import com.github.helf4ch.textstat.nlp.LangProvider;

public class RussianProvider extends LangProvider {
  private static final String tokenizerModelName = "opennlp-ru-ud-gsd-tokens-1.2-2.5.0.bin";
  private static final String sentenceModelName = "opennlp-ru-ud-gsd-sentence-1.2-2.5.0.bin";

  public RussianProvider() {
    super(tokenizerModelName, sentenceModelName);
  }
}
