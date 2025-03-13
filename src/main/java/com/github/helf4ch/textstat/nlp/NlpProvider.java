package com.github.helf4ch.textstat.nlp;

import com.github.helf4ch.textstat.nlp.models.DefaultLangProvider;
import com.github.helf4ch.textstat.nlp.models.LangDetector;
import com.github.helf4ch.textstat.nlp.models.NlProcessor;
import com.github.helf4ch.textstat.nlp.models.lang.EngProvider;
import com.github.helf4ch.textstat.nlp.models.lang.RusProvider;
import com.github.helf4ch.textstat.nlp.models.lang.UkrProvider;

public class NlpProvider {
  private LangDetector langDetector;
  private LanguageConfigurator configurator;

  public NlpProvider() {
    langDetector = new LangDetector();

    configurator = new LanguageConfigurator();

    configurator.add("eng", new EngProvider());
    configurator.add("rus", new RusProvider());
    configurator.addFamily(new UkrProvider(), "ukr", "bel");
  }

  private NlProcessor getProvider(String lang) {
    NlProcessor provider = configurator.get(lang);

    if (provider == null) {
      provider = new DefaultLangProvider();
    }

    return provider;
  }

  public String languageDetect(String text) {
    return langDetector.detect(text).getLang();
  }

  public String[] tokenize(String text) {
    String lang = languageDetect(text);
    NlProcessor provider = getProvider(lang);
    return provider.tokenize(text);
  }

  public String[] sentDetect(String text) {
    String lang = languageDetect(text);
    NlProcessor provider = getProvider(lang);
    return provider.sentDetect(text);
  }
}
