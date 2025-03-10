package com.github.helf4ch.textstat.repository;

import com.github.helf4ch.textstat.model.StopTokens;
import java.util.List;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface StopTokensRepository extends CrudRepository<StopTokens, Integer> {

  @Query("SELECT token FROM stop_tokens")
  List<String> getAllStopTokens();
}
