package com.github.helf4ch.textstat.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

/**
 * Таблица реализует связь многие ко многим. Хранит в себе список слов word и их позицию word_pos в
 * тексте text_id.
 */
public class TextWords {
  @Id private Long id;
  private AggregateReference<TextStat, Long> textId;
  private AggregateReference<WordStat, String> word;
  private Integer wordPos;

  public TextWords(
      AggregateReference<TextStat, Long> textId,
      AggregateReference<WordStat, String> word,
      Integer wordPos) {
    this.textId = textId;
    this.word = word;
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

  public AggregateReference<WordStat, String> getWord() {
    return word;
  }

  public void setWord(AggregateReference<WordStat, String> word) {
    this.word = word;
  }

  public Integer getWordPos() {
    return wordPos;
  }

  public void setWordPos(Integer wordPos) {
    this.wordPos = wordPos;
  }

  @Override
  public String toString() {
    return "TextWords [textId=" + textId + ", word=" + word + ", wordPos=" + wordPos + "]";
  }
}
