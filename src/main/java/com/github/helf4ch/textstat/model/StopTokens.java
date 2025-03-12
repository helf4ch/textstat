package com.github.helf4ch.textstat.model;

import org.springframework.data.annotation.Id;

public class StopTokens {
  @Id private String token;
  private String type;

  public StopTokens(String type, String token) {
    this.type = type;
    this.token = token;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
