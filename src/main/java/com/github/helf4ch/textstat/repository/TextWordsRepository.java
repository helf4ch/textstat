package com.github.helf4ch.textstat.repository;

import com.github.helf4ch.textstat.model.TextWords;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TextWordsRepository extends CrudRepository<TextWords, Long> {

  @Query("SELECT * FROM text_words WHERE text_id = :text_id")
  List<TextWords> findAllByTextId(@Param("text_id") Long id);

  @Query("SELECT DISTINCT * FROM text_words WHERE word = :word GROUP BY id, word")
  List<TextWords> searchWhereWordIsPresented(@Param("word") String word);
}
