package com.github.helf4ch.textstat.view;

public record AllDocumentsStatisticsView(
    Long documents_count,
    Long word_count,
    Long uniq_word_count,
    Long avg_word_lenght,
    Long sentences_count) {}
