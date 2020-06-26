package com.criticalmass.datamatrix.dto.sentinel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Data;

/**
 * Date: 15/06/20
 *
 * @author Kushal Roy
 */
@Data
@ApiModel(value = "Customer")
public class CustomerDTO extends AbstractDTO {

  @ApiModelProperty(value = "User name")
  private String username;

  @ApiModelProperty(value = "Password")
  private String password;

  @ApiModelProperty(value = "Email Address")
  private String emailId;

  @ApiModelProperty(value = "Date of Birth", example = "2019-12-29")
  private LocalDate dateOfBirth;

  @ApiModelProperty(value = "Contact Number")
  private String contactNumber;

/*  @Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Share> shares = new HashSet<>();*/


  private boolean isActive;

  private LocalDateTime createdDate;

  private LocalDateTime lastModifiedDate;

}
