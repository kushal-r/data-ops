package com.criticalmass.datamatrix.dto.response;

import com.criticalmass.datamatrix.dto.exception.Error;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Date: 13/06/20
 *
 * @author Kushal Roy
 */
@Data
@JsonIgnoreProperties
public class Response {

  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  @JsonProperty("errors")
  private List<Error> errors = new ArrayList<>();
}
