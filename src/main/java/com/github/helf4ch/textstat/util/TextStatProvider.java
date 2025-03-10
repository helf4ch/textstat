package com.github.helf4ch.textstat.util;

import com.github.helf4ch.textstat.util.TextNormalizer.TextObj;
import java.util.Set;

public class TextStatProvider {
  private TextStatProvider() {}

  public static Integer getWordCount(TextObj textObj) {
    String[] words = textObj.getTextNormalized().split(" ");
    return words.length;
  }

  public static Integer getUniqWordCount(TextObj textObj) {
    Set<String> uniqWords = Set.of(textObj.getTextNormalized().split(" "));
    return uniqWords.size();
  }

  public static Integer getAvgWordLenght(TextObj textObj) {
    String[] words = textObj.getTextNormalized().split(" ");

    Integer lenghtSum = 0;
    for (String s : words) {
      lenghtSum += s.length();
    }

    return lenghtSum / words.length;
  }
}
