package com.criticalmass.datamatrix.entity.sentinel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.validation.annotation.Validated;

/**
 * Date: 14/06/20
 *
 * @author Kushal Roy
 */
@Data
@Entity
@DynamicUpdate
@Validated
@EntityListeners(AuditingEntityListener.class)
public class Customer extends BaseEntity {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  @Type(type = "uuid-char")
  private UUID id;


  @NotEmpty(message = "Username is blank or empty")
  @Column(nullable = false, unique = true)
  @Size(max = 255, message = "Maximum username size cannot exceed 255 characters")
  private String username;


  @NotEmpty(message = "Password is blank or empty")
  @Column(nullable = false)
  @Size(max = 255, message = "Maximum Password size cannot exceed 255 characters")
  private String password;


  @NaturalId
  @NotEmpty(message = "Email is not valid")
  @Column(nullable = false, unique = true)
  @Email
  private String emailId;


  @Column(nullable = false)
  private LocalDate dateOfBirth;


  @Column(nullable = false)
  @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$",
      message = "Contact number is not valid. Contact number should be of format +00 1234567890")
  private String contactNumber;


/*  @Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Set<Share> shares = new HashSet<>();*/


  private boolean isActive;


  @Column(nullable = false, updatable = false)
  @CreatedDate
  private LocalDateTime createdDate;


  @Column(nullable = false)
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;

}
