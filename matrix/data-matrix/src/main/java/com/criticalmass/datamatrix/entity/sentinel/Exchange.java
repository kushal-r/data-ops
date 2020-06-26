package com.criticalmass.datamatrix.entity.sentinel;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.validation.annotation.Validated;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
@Data
//@Entity
//@DynamicUpdate
@Validated
//@EntityListeners(AuditingEntityListener.class)
public class Exchange extends BaseEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type = "uuid-char")
  private UUID id;

  @NotEmpty(message = "Exchange name is blank or empty")
  @Column(nullable = false, unique = true)
  @Size(max = 255, message = "Maximum name size cannot exceed 255 characters")
  private String name;

  @NotEmpty(message = "Exchange code is blank or empty")
  @Column(nullable = false, unique = true)
  @Size(max = 255, message = "Maximum code size cannot exceed 255 characters")
  private String code;

}
