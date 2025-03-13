package com.github.helf4ch.textstat.dto;

import com.github.helf4ch.textstat.model.WordStat;
import java.util.List;

public record TopWordsList(List<WordStat> topWords) {}
