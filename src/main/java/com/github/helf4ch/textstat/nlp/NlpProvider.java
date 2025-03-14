package com.github.helf4ch.textstat.nlp;

import com.github.helf4ch.textstat.nlp.models.DefaultLangProvider;
import com.github.helf4ch.textstat.nlp.models.LangDetector;
import com.github.helf4ch.textstat.nlp.models.NlProcessor;
import com.github.helf4ch.textstat.nlp.models.lang.EngProvider;
import com.github.helf4ch.textstat.nlp.models.lang.RusProvider;
import com.github.helf4ch.textstat.nlp.models.lang.UkrProvider;

/**
 * Класс предоставляет различные функции обработки текста с использованием OpenNLP и без. Позволяет
 * настраивать обработчики для конкретных языков, а так же настраивать семейства языков.
 *
 * @see nlp/models/NlProcessor.java
 * @see LanguageConfigurator.java
 * @example controller/DocumentController.java
 */
public class NlpProvider {
  private LangDetector langDetector;
  private LanguageConfigurator configurator;

  public NlpProvider() {
    langDetector = new LangDetector();

    configurator = new LanguageConfigurator();

    /// Названия языков взяты отсюда:
    /// https://downloads.apache.org/opennlp/models/langdetect/1.8.3/README.txt
    configurator.add("eng", new EngProvider());
    configurator.add("rus", new RusProvider());
    configurator.addFamily(new UkrProvider(), "ukr", "bel");
  }

  /**
   * Возвращяет обработчик для конкретного языка lang. В случае, если обработчик для него не указан,
   * возвращает стандартный обработчик, который не использует OpenNLP.
   *
   * @see nlp/models/DefaultLangProvider.java
   */
  private NlProcessor getProvider(String lang) {
    NlProcessor provider = configurator.get(lang);

    if (provider == null) {
      provider = new DefaultLangProvider();
    }

    return provider;
  }

  /// Определения языка текста.
  /// Названия языков взяты отсюда:
  /// https://downloads.apache.org/opennlp/models/langdetect/1.8.3/README.txt
  public String languageDetect(String text) {
    return langDetector.detect(text).getLang();
  }

  /// Токенизация текста.
  public String[] tokenize(String text) {
    String lang = languageDetect(text);
    NlProcessor provider = getProvider(lang);
    return provider.tokenize(text);
  }

  /// Детекция предложений.
  public String[] sentDetect(String text) {
    String lang = languageDetect(text);
    NlProcessor provider = getProvider(lang);
    return provider.sentDetect(text);
  }
}
