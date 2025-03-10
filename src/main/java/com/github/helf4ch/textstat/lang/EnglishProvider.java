package com.github.helf4ch.textstat.lang;

public class EnglishProvider extends LangProvider {
  private static final String tokenizerModelName = "opennlp-en-ud-ewt-tokens-1.2-2.5.0.bin";
  private static final String sentenceModelName = "opennlp-en-ud-ewt-sentence-1.2-2.5.0.bin";

  public EnglishProvider() {
    super(tokenizerModelName, sentenceModelName);
  }
}
