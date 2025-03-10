package com.github.helf4ch.textstat.lang;

public class WordTokenizedObj {
  private String text;
  private String[] tokens;

  public String getText() {
    return text;
  }

  void setText(String text) {
    this.text = text;
  }

  public String[] getTokens() {
    return tokens;
  }

  void setTokens(String[] tokens) {
    this.tokens = tokens;
  }
}
