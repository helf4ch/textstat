package com.github.helf4ch.textstat.nlp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;

class NlpProviderTests {
  private NlpProvider nlpProvider;

  public NlpProviderTests() {
    this.nlpProvider = new NlpProvider();
  }

  @Test
  public void langDetectShouldReturnRussian() {
    String text = "Текст на русском.";
    String got = nlpProvider.languageDetect(text);
    String expected = "rus";
    assertEquals(expected, got);
  }

  @Test
  public void tokenizeShouldReternExpected() {
    String text = "Тестирование токенизации.";
    String[] got = nlpProvider.tokenize(text);
    String[] expected = {"Тестирование", "токенизации", "."};
    assertTrue(Arrays.equals(expected, got));
  }

  @Test
  public void sentDetectShouldReternExpected() {
    String text = "Предложение 1. Предложение 2.";
    String[] got = nlpProvider.sentDetect(text);
    String[] expected = {"Предложение 1.", "Предложение 2."};
    assertTrue(Arrays.equals(expected, got));
  }
}
