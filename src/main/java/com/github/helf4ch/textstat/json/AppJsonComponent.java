package com.github.helf4ch.textstat.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.helf4ch.textstat.dto.TopWordsList;
import com.github.helf4ch.textstat.model.WordStat;
import java.io.IOException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class AppJsonComponent {

  public static class ListSerializer<T> extends JsonSerializer<Iterable<T>> {

    @Override
    public void serialize(Iterable<T> values, JsonGenerator jgen, SerializerProvider serializers)
        throws IOException {
      jgen.writeStartArray();

      for (T value : values) {
        jgen.writeObject(value);
      }

      jgen.writeEndArray();
    }
  }

  public static class TopWordsSerializer extends JsonSerializer<TopWordsList> {

    @Override
    public void serialize(TopWordsList value, JsonGenerator jgen, SerializerProvider serializers)
        throws IOException {
      jgen.writeStartObject();

      for (WordStat topWord : value.topWords()) {
        jgen.writeNumberField(topWord.getWord(), topWord.getUseCount());
      }
    }
  }
}
