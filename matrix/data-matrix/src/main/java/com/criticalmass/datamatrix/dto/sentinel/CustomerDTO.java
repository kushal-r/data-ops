package com.criticalmass.datamatrix.dto.sentinel;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
@Data
public class CustomerDTO implements Serializable {

  private String username;


  private String password;


  @Email
  private String email;


  private boolean isActive;


  @CreatedDate
  private LocalDateTime createdDate;


  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

}
