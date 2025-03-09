package com.github.helf4ch.textstat.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.helf4ch.textstat.config.TextProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class TextNormalizerTests {

  private TextProperties propertiesMock;
  private TextNormalizer normalizer;

  @BeforeEach
  public void setup() {
    this.propertiesMock = Mockito.mock(TextProperties.class);
    Mockito.when(propertiesMock.getStopWords()).thenReturn("the and or");
    this.normalizer = new TextNormalizer(propertiesMock);
  }

  @Test
  public void givenText_whenTextNormilizer_thenReturnNormilized() {
    String givenText = "The word! Word1 and word2.\nWord3 or...   Word4? Word5, word6; word7!?";
    String actual = normalizer.normalize(givenText);
    String expected = "word word1 word2 word3 word4 word5 word6 word7";
    assertEquals(expected, actual);
  }
}
