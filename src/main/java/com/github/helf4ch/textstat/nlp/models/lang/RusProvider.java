package com.github.helf4ch.textstat.nlp.models.lang;

import com.github.helf4ch.textstat.nlp.models.LangProvider;

public class RusProvider extends LangProvider {
  private static final String tokenizerModelName = "opennlp-ru-ud-gsd-tokens-1.2-2.5.0.bin";
  private static final String sentenceModelName = "opennlp-ru-ud-gsd-sentence-1.2-2.5.0.bin";

  public RusProvider() {
    super(tokenizerModelName, sentenceModelName);
  }
}
