package com.github.helf4ch.textstat.nlp.models.lang;

import com.github.helf4ch.textstat.nlp.models.LangProvider;

public class EngProvider extends LangProvider {
  private static final String tokenizerModelName = "opennlp-en-ud-ewt-tokens-1.2-2.5.0.bin";
  private static final String sentenceModelName = "opennlp-en-ud-ewt-sentence-1.2-2.5.0.bin";

  public EngProvider() {
    super(tokenizerModelName, sentenceModelName);
  }
}
