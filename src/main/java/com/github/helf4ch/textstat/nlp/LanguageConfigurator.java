package com.github.helf4ch.textstat.nlp;

import com.github.helf4ch.textstat.nlp.models.NlProcessor;
import java.util.HashMap;
import java.util.Map;

// the language names can be found here:
// https://downloads.apache.org/opennlp/models/langdetect/1.8.3/README.txt
public class LanguageConfigurator {
  private Map<String, NlProcessor> stringToProvider;

  public LanguageConfigurator() {
    stringToProvider = new HashMap<String, NlProcessor>();
  }

  public void add(String lang, NlProcessor provider) {
    stringToProvider.put(lang, provider);
  }

  public void addFamily(NlProcessor provider, String... langs) {
    for (String lang : langs) {
      stringToProvider.put(lang, provider);
    }
  }

  public NlProcessor get(String lang) {
    return stringToProvider.get(lang);
  }
}
