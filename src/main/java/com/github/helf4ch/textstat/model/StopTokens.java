package com.github.helf4ch.textstat.model;

import org.springframework.data.annotation.Id;

public class StopTokens {
  @Id private Integer id;
  private String type;
  private String token;

  public StopTokens(String type, String token) {
    this.type = type;
    this.token = token;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }
}
