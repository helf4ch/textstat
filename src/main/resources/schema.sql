CREATE TABLE IF NOT EXISTS text_stat (
  id BIGSERIAL PRIMARY KEY,
  text VARCHAR(4048) NOT NULL,
  word_count INT NOT NULL,
  uniq_word_count INT NOT NULL,
  avg_word_lenght INT NOT NULL,
  sentences_count INT NOT NULL
);

CREATE TABLE IF NOT EXISTS word_stat (
  word VARCHAR(128) PRIMARY KEY,
  use_count INT NOT NULL DEFAULT 1
);

CREATE TABLE IF NOT EXISTS text_words (
  id BIGSERIAL PRIMARY KEY,
  text_id BIGINT REFERENCES text_stat(id),
  word VARCHAR(128) REFERENCES word_stat(word),
  word_pos INT NOT NULL
);

CREATE TABLE IF NOT EXISTS stop_tokens (
  token VARCHAR(128) PRIMARY KEY,
  type VARCHAR(128)
);

