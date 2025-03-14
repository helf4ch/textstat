package com.github.helf4ch.textstat.nlp;

import com.github.helf4ch.textstat.nlp.models.NlProcessor;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс, содержащий в себе классы-обработчики текста для различных языков.
 *
 * @see nlp/models/NlProcessor.java
 * @example nlp/NlpProvider.java
 */
public class LanguageConfigurator {
  private Map<String, NlProcessor> stringToProvider;

  public LanguageConfigurator() {
    stringToProvider = new HashMap<String, NlProcessor>();
  }

  /// Добавляет обработчик provider для языка lang.
  public void add(String lang, NlProcessor provider) {
    stringToProvider.put(lang, provider);
  }

  /// Добавляет обработчик provider для множества языков langs.
  public void addFamily(NlProcessor provider, String... langs) {
    for (String lang : langs) {
      stringToProvider.put(lang, provider);
    }
  }

  /// Возвращяет обработчик для языка lang.
  public NlProcessor get(String lang) {
    return stringToProvider.get(lang);
  }
}
