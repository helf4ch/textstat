package com.github.helf4ch.textstat.nlp;

import com.github.helf4ch.textstat.nlp.models.ILangProvider;
import java.util.Map;

// the language names can be found here:
// https://downloads.apache.org/opennlp/models/langdetect/1.8.3/README.txt
public class LanguageConfigurator {
  private Map<String, ILangProvider> stringToProvider;

  public void add(String lang, ILangProvider provider) {
    stringToProvider.put(lang, provider);
  }

  public void addFamily(ILangProvider provider, String... langs) {
    for (String lang : langs) {
      stringToProvider.put(lang, provider);
    }
  }

  public ILangProvider get(String lang) {
    return stringToProvider.get(lang);
  }
}
