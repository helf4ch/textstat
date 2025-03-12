package com.github.helf4ch.textstat.model;

import org.springframework.data.annotation.Id;

public class WordStat {
  @Id private String word;
  private Integer useCount;

  public WordStat(String word, Integer useCount) {
    this.word = word;
    this.useCount = useCount;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }

  public Integer getUseCount() {
    return useCount;
  }

  public void setUseCount(Integer useCount) {
    this.useCount = useCount;
  }

  @Override
  public String toString() {
    return "WordStat [word=" + word + ", useCount=" + useCount + "]";
  }
}
