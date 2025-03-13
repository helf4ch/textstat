package com.github.helf4ch.textstat.controller;

import com.github.helf4ch.textstat.dto.TopWordsList;
import com.github.helf4ch.textstat.model.StopTokens;
import com.github.helf4ch.textstat.model.TextStat;
import com.github.helf4ch.textstat.model.TextWords;
import com.github.helf4ch.textstat.model.WordStat;
import com.github.helf4ch.textstat.nlp.NlpProvider;
import com.github.helf4ch.textstat.repository.StopTokensRepository;
import com.github.helf4ch.textstat.repository.TextStatRepository;
import com.github.helf4ch.textstat.repository.TextWordsRepository;
import com.github.helf4ch.textstat.repository.WordStatRepository;
import com.github.helf4ch.textstat.view.AllDocumentsStatisticsView;
import com.github.helf4ch.textstat.view.DocumentStatisticsView;
import com.github.helf4ch.textstat.view.GetDocumentView;
import com.github.helf4ch.textstat.view.PostDocumentView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
  private Logger logger = LoggerFactory.getLogger(DocumentController.class);

  @Autowired private TextStatRepository textStatRepository;
  @Autowired private WordStatRepository wordStatRepository;
  @Autowired private TextWordsRepository textWordsRepository;
  @Autowired private StopTokensRepository stopTokensrepository;

  @Autowired private NlpProvider nlpProvider;

  @PostMapping
  @Transactional
  public ResponseEntity<TextStat> addDocument(@RequestBody PostDocumentView postDocumentView) {
    try {
      TextStat textStat = new TextStat();
      textStat.setText(postDocumentView.text());

      Integer lengthSum = 0;
      Integer count = 0;
      String[] tokenized = nlpProvider.tokenize(postDocumentView.text());
      List<String> words = new ArrayList<String>();
      for (String token : tokenized) {
        token = token.toLowerCase();

        Optional<StopTokens> stopTokenOpt = stopTokensrepository.findById(token);
        if (stopTokenOpt.isPresent()) {
          continue;
        }

        words.add(token);
        wordStatRepository.insertOrUpdateUsageCountIfExists(token);
        lengthSum += token.length();
        count++;
      }

      textStat.setWordCount(tokenized.length);
      textStat.setUniqWordCount(new HashSet<String>(words).size());
      textStat.setAvgWordLenght(lengthSum / count);

      textStat.setSentencesCount(nlpProvider.sentDetect(postDocumentView.text()).length);

      textStatRepository.save(textStat);

      for (int i = 0; i < words.size(); ++i) {
        TextWords textWords =
            new TextWords(
                AggregateReference.to(textStat.getId()),
                AggregateReference.to(words.get(i)),
                i + 1);
        textWordsRepository.save(textWords);
      }

      return ResponseEntity.status(HttpStatus.CREATED).body(textStat);
    } catch (Exception e) {
      logger.error(e.getMessage());
      throw new ResponseStatusException(
          HttpStatus.INTERNAL_SERVER_ERROR, "/api/documents post error");
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<GetDocumentView> findDocumentById(@PathVariable("id") Long id) {
    Optional<TextStat> textStatOpt = textStatRepository.findById(id);

    if (textStatOpt.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }

    TextStat textStat = textStatOpt.get();
    GetDocumentView result = new GetDocumentView(id, textStat.getText());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping("/{id}/normalized")
  public ResponseEntity<GetDocumentView> findDocumentByIdNormalized(@PathVariable("id") Long id) {
    List<TextWords> textWords = textWordsRepository.findAllByTextId(id);

    if (textWords.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }

    String text = new String();
    for (TextWords textWord : textWords) {
      text += textWord.getWord().getId();
      text += " ";
    }

    GetDocumentView result = new GetDocumentView(id, text.trim());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping("/{id}/statistics")
  public ResponseEntity<DocumentStatisticsView> findDocumentStatisticById(
      @PathVariable("id") Long id) {
    Optional<TextStat> textStatOpt = textStatRepository.findById(id);

    if (textStatOpt.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }

    TextStat textStat = textStatOpt.get();
    DocumentStatisticsView result =
        new DocumentStatisticsView(
            textStat.getWordCount(),
            textStat.getUniqWordCount(),
            textStat.getAvgWordLenght(),
            textStat.getSentencesCount());
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping("/statistics")
  public ResponseEntity<AllDocumentsStatisticsView> calculateAllDocumentsStatistics() {
    AllDocumentsStatisticsView result = textStatRepository.calculateAllStatistic();
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @GetMapping("/{id}/top-words")
  public ResponseEntity<TopWordsList> listTopWordsInText(
      @PathVariable("id") Long id,
      @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
    List<WordStat> wordStats = textWordsRepository.selectTopWordsInText(id, limit);

    if (wordStats.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }

    return ResponseEntity.status(HttpStatus.OK).body(new TopWordsList(wordStats));
  }

  @GetMapping("/top-words")
  public ResponseEntity<TopWordsList> listTopWords(
      @RequestParam(name = "limit", defaultValue = "10") Integer limit) {
    List<WordStat> wordStats = wordStatRepository.selectTopWords(limit);

    return ResponseEntity.status(HttpStatus.OK).body(new TopWordsList(wordStats));
  }

  @GetMapping("/{id}/bigrams")
  public ResponseEntity<TopWordsList> listBigrams(@PathVariable("id") Long id) {
    Optional<TextStat> textStatOpt = textStatRepository.findById(id);

    if (textStatOpt.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
    }

    List<WordStat> wordStats = textWordsRepository.selectBigramsInText(id);

    return ResponseEntity.status(HttpStatus.OK).body(new TopWordsList(wordStats));
  }

  @GetMapping("/search")
  public ResponseEntity<List<Long>> searchWord(
      @RequestParam(name = "word", required = true) String word) {
    List<TextWords> textWords = textWordsRepository.searchWhereWordIsPresented(word);

    List<Long> ids = new ArrayList<Long>();
    for (TextWords textWord : textWords) {
      ids.add(textWord.getTextId().getId());
    }

    return ResponseEntity.status(HttpStatus.OK).body(ids);
  }
}
