package com.github.helf4ch.textstat.controller;

import com.github.helf4ch.textstat.model.StopTokens;
import com.github.helf4ch.textstat.model.TextStat;
import com.github.helf4ch.textstat.model.TextWords;
import com.github.helf4ch.textstat.nlp.NlpProvider;
import com.github.helf4ch.textstat.repository.StopTokensRepository;
import com.github.helf4ch.textstat.repository.TextStatRepository;
import com.github.helf4ch.textstat.repository.TextWordsRepository;
import com.github.helf4ch.textstat.repository.WordStatRepository;
import com.github.helf4ch.textstat.view.PostDocumentView;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
  @Autowired private TextStatRepository textStatRepository;
  @Autowired private WordStatRepository wordStatRepository;
  @Autowired private TextWordsRepository textWordsRepository;
  @Autowired private StopTokensRepository stopTokensrepository;

  @Autowired private NlpProvider nlpProvider;

  @PostMapping
  @Transactional
  public RedirectView addDocument(@RequestBody PostDocumentView postDocumentView) {
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
    textStat.setUniqWordCount(Set.of(tokenized).size());
    textStat.setAvgWordLenght(lengthSum / count);

    textStat.setSentencesCount(nlpProvider.sentDetect(postDocumentView.text()).length);

    textStatRepository.save(textStat);

    for (int i = 0; i < words.size(); ++i) {
      TextWords textWords =
          new TextWords(
              AggregateReference.to(textStat.getId()), AggregateReference.to(words.get(i)), i + 1);
      textWordsRepository.save(textWords);
    }

    return new RedirectView("/api/documents/" + textStat.getId());
  }
}
