package com.criticalmass.datamatrix.helper.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Date: 17/06/20
 *
 * @author Kushal Roy
 */
@Component
public class LocalDateConverter implements Converter<String, LocalDate> {

  @Override
  public LocalDate convert(String source) {
    if (source == null || source.isEmpty()) {
      return null;
    }

    return LocalDate.parse(source, DateTimeFormatter.ISO_LOCAL_DATE);
  }
}
