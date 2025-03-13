package com.github.helf4ch.textstat.repository;

import com.github.helf4ch.textstat.model.TextWords;
import com.github.helf4ch.textstat.model.WordStat;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TextWordsRepository extends CrudRepository<TextWords, Long> {

  @Query("SELECT * FROM text_words WHERE text_id = :text_id")
  List<TextWords> findAllByTextId(@Param("text_id") Long id);

  @Query(
      "SELECT * FROM text_words WHERE word = :word AND word_pos = (SELECT MIN(word_pos) FROM"
          + " text_words WHERE word = :word)")
  List<TextWords> searchWhereWordIsPresented(@Param("word") String word);

  @Query(
      "WITH text_words_stat (word, use_count) AS (SELECT text_words.word, COUNT(text_words.word)"
          + " FROM text_words INNER JOIN word_stat ON word_stat.word = text_words.word WHERE"
          + " text_id = :text_id GROUP BY text_words.word) SELECT word, use_count FROM"
          + " text_words_stat ORDER BY use_count DESC, word LIMIT :show_limit")
  List<WordStat> selectTopWordsInText(
      @Param("text_id") Long id, @Param("show_limit") Integer limit);

  @Query(
      "WITH text_words_stat (word, use_count) AS (SELECT text_words.word, COUNT(text_words.word)"
          + " FROM text_words INNER JOIN word_stat ON word_stat.word = text_words.word WHERE"
          + " text_id = :text_id GROUP BY text_words.word) SELECT word, use_count FROM"
          + " text_words_stat WHERE use_count > 1 ORDER BY use_count DESC, word")
  List<WordStat> selectBigramsInText(@Param("text_id") Long id);
}
