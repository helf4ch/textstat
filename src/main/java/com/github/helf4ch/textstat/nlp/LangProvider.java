package com.github.helf4ch.textstat.nlp;

import java.io.IOException;
import java.io.InputStream;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LangProvider {
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

  public WordTokenizedObj wordTokenization(String text) {
    WordTokenizedObj obj = new WordTokenizedObj();
    TokenizerME tokenizer = new TokenizerME(tokenizerModel);
    obj.setText(text);
    obj.setTokens(tokenizer.tokenize(text));
    return obj;
  }

  public SentenceTokenizedObj sentenceTokenization(String text) {
    SentenceTokenizedObj obj = new SentenceTokenizedObj();
    SentenceDetectorME sentenceDetector = new SentenceDetectorME(sentenceModel);
    obj.setText(text);
    obj.setTokens(sentenceDetector.sentDetect(text));
    return obj;
  }
}
