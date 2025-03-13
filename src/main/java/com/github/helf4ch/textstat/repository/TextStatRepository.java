package com.github.helf4ch.textstat.repository;

import com.github.helf4ch.textstat.model.TextStat;
import com.github.helf4ch.textstat.view.AllDocumentsStatisticsView;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface TextStatRepository extends CrudRepository<TextStat, Long> {

  @Query(
      "SELECT COUNT(*) AS documents_count, SUM(word_count) AS word_count, SUM(uniq_word_count) AS"
          + " uniq_word_count, SUM(avg_word_lenght) AS avg_word_lenght, SUM(sentences_count) AS"
          + " sentences_count FROM text_stat")
  AllDocumentsStatisticsView calculateAllStatistic();
}
