package com.criticalmass.datamatrix.helper.converter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Date: 17/06/20
 *
 * @author Kushal Roy
 */
@Component
public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

  @Override
  public LocalDateTime convert(String source) {
    if (source == null || source.isEmpty()) {
      return null;
    }

    return LocalDateTime.parse(source, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }
}
