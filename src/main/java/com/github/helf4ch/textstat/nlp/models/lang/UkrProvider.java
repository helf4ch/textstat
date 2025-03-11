package com.github.helf4ch.textstat.nlp.models.lang;

import com.github.helf4ch.textstat.nlp.models.LangProvider;

public class UkrProvider extends LangProvider {
  private static final String tokenizerModelName = "opennlp-uk-ud-iu-tokens-1.2-2.5.0.bin";
  private static final String sentenceModelName = "opennlp-uk-ud-iu-sentence-1.2-2.5.0.bin";

  public UkrProvider() {
    super(tokenizerModelName, sentenceModelName);
  }
}
