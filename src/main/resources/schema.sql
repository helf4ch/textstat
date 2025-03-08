CREATE TABLE IF NOT EXISTS text_stat (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  text VARCHAR(2048) NOT NULL,
  word_count INT NOT NULL,
  uniq_word_count INT NOT NULL,
  avg_word_lenght INT NOT NULL,
  sentences_count INT NOT NULL
);

CREATE TABLE IF NOT EXISTS word_stat (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  word VARCHAR(64) NOT NULL,
  use_count INT NOT NULL
);

CREATE TABLE IF NOT EXISTS text_words (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  text_id BIGINT NOT NULL,
  word_id BIGINT NOT NULL,
  word_pos INT NOT NULL,
  FOREIGN KEY (text_id) references text_stat(id),
  FOREIGN KEY (word_id) references word_stat(id)
);

