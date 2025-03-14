package com.github.helf4ch.textstat.nlp.models;

/*
 * Интерфейс, предоставляющий различные методы обработки текста
 *
 * @see nlp/models/LangProvider.java
 * @see nlp/models/DefaultLangProvider.java
 * @example nlp/NlpProvider.java
 */
public interface NlProcessor {

  /**
   * Токенeизация текста.
   *
   * @return массив строк, где элементы - токены
   */
  public String[] tokenize(String text);

  /**
   * Детекция конца предложений.
   *
   * @return массив строк, где элементы - предложения
   */
  public String[] sentDetect(String text);
}
