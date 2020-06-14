package com.criticalmass.datamatrix.configuration.constant;

import java.time.format.DateTimeFormatter;
import lombok.Data;

/**
 * Date: 13/06/20
 *
 * @author Kushal Roy
 */
@Data
public class DateFormatter {

  /* ---------------- Fields ---------------- */

  private static final DateTimeFormatter DATE_TIME_MILLI_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ssssss");

  private static final DateTimeFormatter ZONED_DATE_TIME_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ss[.SSSSSS][XXX]");

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter
      .ofPattern("yyyy-MM-dd HH:mm:ss");
}
