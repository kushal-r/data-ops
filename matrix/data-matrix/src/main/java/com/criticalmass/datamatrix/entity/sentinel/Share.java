package com.criticalmass.datamatrix.entity.sentinel;

import com.criticalmass.datamatrix.helper.converter.JsonToMapConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
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
public class Share extends BaseEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type = "uuid-char")
  private UUID id;


  @NotEmpty(message = "Company listing name is blank or empty")
  @Size(max = 255, message = "Maximum Company listing name size cannot exceed 255 characters")
  private String name;


  @Exclude
  @ToString.Exclude
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "exchange_id", nullable = false, referencedColumnName = "id")
  @JsonIgnore
  private Exchange exchange;


  @NotEmpty(message = "Company name is blank or empty")
  @Size(max = 255, message = "Maximum Company name size cannot exceed 255 characters")
  private String companyName;


  @Exclude
  @ToString.Exclude
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
  @JsonIgnore
  private Customer customer;


  @Column(columnDefinition = "json")
  @Convert(converter = JsonToMapConverter.class)
  private Map<String, Object> customAttributes = new HashMap<>();


  private boolean isActive;


  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;


  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

}
