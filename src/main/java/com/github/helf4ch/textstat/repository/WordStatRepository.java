package com.github.helf4ch.textstat.repository;

import com.github.helf4ch.textstat.model.WordStat;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WordStatRepository extends CrudRepository<WordStat, String> {

  /// Добавляет новое слово, если его нет. Если есть, то увеличивает счетчик use_count на 1.
  @Modifying
  @Query(
      "MERGE INTO word_stat AS target USING (SELECT CAST(:word AS VARCHAR(128)) AS word) AS source"
          + " ON target.word = source.word WHEN MATCHED THEN UPDATE SET target.use_count ="
          + " target.use_count + 1 WHEN NOT MATCHED THEN INSERT (word) VALUES (source.word)")
  void insertOrUpdateUsageCountIfExists(@Param("word") String word);

  @Query("SELECT * FROM word_stat ORDER BY use_count DESC, word LIMIT :show_limit")
  List<WordStat> selectTopWords(@Param("show_limit") Integer limit);
}
