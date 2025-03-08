package com.github.helf4ch.textstat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

public class TextWords {
  @Id private Long id;
  private AggregateReference<TextStat, Long> textId;
  private AggregateReference<WordStat, Long> wordId;
  private Integer wordPos;

  public TextWords(
      AggregateReference<TextStat, Long> textId,
      AggregateReference<WordStat, Long> wordId,
      Integer wordPos) {
    this.textId = textId;
    this.wordId = wordId;
    this.wordPos = wordPos;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public AggregateReference<TextStat, Long> getTextId() {
    return textId;
  }

  public void setTextId(AggregateReference<TextStat, Long> textId) {
    this.textId = textId;
  }

  public AggregateReference<WordStat, Long> getWordId() {
    return wordId;
  }

  public void setWordId(AggregateReference<WordStat, Long> wordId) {
    this.wordId = wordId;
  }

  public Integer getWordPos() {
    return wordPos;
  }

  public void setWordPos(Integer wordPos) {
    this.wordPos = wordPos;
  }

  @Override
  public String toString() {
    return "TextWords [textId=" + textId + ", wordId=" + wordId + ", wordPos=" + wordPos + "]";
  }
}
