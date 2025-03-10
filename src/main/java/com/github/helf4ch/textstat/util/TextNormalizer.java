package com.github.helf4ch.textstat.util;

import com.github.helf4ch.textstat.config.TextProperties;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TextNormalizer {
  private final TextProperties properties;

  @Autowired
  public TextNormalizer(TextProperties properties) {
    this.properties = properties;
  }

  public class TextObj {
    private String text;
    private String textNormalized;

    public String getText() {
      return text;
    }

    private void setText(String text) {
      this.text = text;
    }

    public String getTextNormalized() {
      return textNormalized;
    }

    private void setTextNormalized(String textNormalized) {
      this.textNormalized = textNormalized;
    }
  }

  public TextObj normalize(String text) {
    String textNormalized = normalization(text);

    List<String> stopWords = Arrays.asList(normalization(properties.getStopWords()).split(" "));
    String[] normalizedWords = textNormalized.split(" ");

    for (int i = 0; i < normalizedWords.length; ++i) {
      if (stopWords.contains(normalizedWords[i])) {
        normalizedWords[i] = "";
      }
    }

    textNormalized = String.join(" ", normalizedWords);

    TextObj result = new TextObj();
    result.setText(text);
    result.setTextNormalized(textNormalized);

    return result;
  }

  private String normalization(String text) {
    return text.trim().toLowerCase().replaceAll("[^a-z0-9 ]", " ").trim().replaceAll(" +", " ");
  }
}
