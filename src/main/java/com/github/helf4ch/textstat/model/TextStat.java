package com.github.helf4ch.textstat.model;

import org.springframework.data.annotation.Id;

/** Таблица со статистикой текста. */
public class TextStat {
  @Id private Long id;
  private String text;
  private Integer wordCount;
  private Integer uniqWordCount;
  private Integer avgWordLenght;
  private Integer sentencesCount;

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(final String text) {
    this.text = text;
  }

  public Integer getWordCount() {
    return wordCount;
  }

  public void setWordCount(final Integer wordCount) {
    this.wordCount = wordCount;
  }

  public Integer getUniqWordCount() {
    return uniqWordCount;
  }

  public void setUniqWordCount(final Integer uniqWordCount) {
    this.uniqWordCount = uniqWordCount;
  }

  public Integer getAvgWordLenght() {
    return avgWordLenght;
  }

  public void setAvgWordLenght(final Integer avgWordLenght) {
    this.avgWordLenght = avgWordLenght;
  }

  public Integer getSentencesCount() {
    return sentencesCount;
  }

  public void setSentencesCount(final Integer sentencesCount) {
    this.sentencesCount = sentencesCount;
  }

  @Override
  public String toString() {
    return "TextStat [id="
        + id
        + ", text="
        + text
        + ", wordCount="
        + wordCount
        + ", uniqWordCount="
        + uniqWordCount
        + ", avgWordLenght="
        + avgWordLenght
        + ", sentencesCount="
        + sentencesCount
        + "]";
  }
}
