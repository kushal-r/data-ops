package com.criticalmass.datamatrix.dto.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Date: 13/06/20
 *
 * @author Kushal Roy
 */
@Data
@AllArgsConstructor
public class Error {

  @JsonProperty("timestamp")
  private OffsetDateTime timestamp;

  @JsonProperty("status_code")
  private int status;

  @JsonProperty("message")
  private String message;

}
