package com.github.helf4ch.textstat.view;

public record DocumentStatisticView(
    Integer word_count,
    Integer uniq_word_count,
    Integer avg_word_lenght,
    Integer sentences_count) {}
