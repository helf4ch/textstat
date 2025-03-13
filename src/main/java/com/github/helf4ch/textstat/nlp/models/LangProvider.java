package com.github.helf4ch.textstat.nlp.models;

import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// models can be downloaded here:
// https://opennlp.apache.org/models.html
public class LangProvider implements NlProcessor {
  private Logger logger = LoggerFactory.getLogger(LangProvider.class);

  private String tokensModelPath = "models/tokens/";
  private TokenizerModel tokenizerModel;

  private String sentenceModelPath = "models/sentence/";
  private SentenceModel sentenceModel;

  public LangProvider(String tokenizerModelName, String sentenceModelName) {
    try (InputStream modelIn =
        getClass().getClassLoader().getResourceAsStream(tokensModelPath + tokenizerModelName)) {
      this.tokenizerModel = new TokenizerModel(modelIn);
    } catch (IOException e) {
      logger.error("Can't load model: " + tokensModelPath + tokenizerModelName + ".");
      System.exit(1);
    }

    try (InputStream modelIn =
        getClass().getClassLoader().getResourceAsStream(sentenceModelPath + sentenceModelName)) {
      this.sentenceModel = new SentenceModel(modelIn);
    } catch (IOException e) {
      logger.error("Can't load model: " + sentenceModelPath + tokenizerModelName + ".");
      System.exit(1);
    }
  }

  public String[] tokenize(String text) {
    TokenizerME tokenizer = new TokenizerME(tokenizerModel);
    return tokenizer.tokenize(text);
  }

  public String[] sentDetect(String text) {
    SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentenceModel);
    return sentenceDetector.sentDetect(text);
  }
}
