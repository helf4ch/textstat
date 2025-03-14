package com.github.helf4ch.textstat.nlp.models;

import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.langdetect.Language;
import opennlp.tools.langdetect.LanguageDetectorME;
import opennlp.tools.langdetect.LanguageDetectorModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс, предоставляющий функции OpenNLP Language Detection.
 * https://opennlp.apache.org/docs/1.9.1/manual/opennlp.html#tools.langdetect
 *
 * @example nlp/NlpProvider.java
 */
public class LangDetector {
  private Logger logger = LoggerFactory.getLogger(LangDetector.class);

  private String modelPath = "models/langdetect/";
  private String modelName = "langdetect-183.bin";
  private LanguageDetectorModel model;

  public LangDetector() {
    try (InputStream modelIn =
        getClass().getClassLoader().getResourceAsStream(modelPath + modelName)) {
      this.model = new LanguageDetectorModel(modelIn);
    } catch (IOException e) {
      logger.error("Can't load model: " + modelPath + modelName + ".");
      System.exit(1);
    }
  }

  public Language detect(String text) {
    LanguageDetectorME categorizer = new LanguageDetectorME(model);
    return categorizer.predictLanguage(text);
  }
}
