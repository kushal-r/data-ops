package com.criticalmass.datamatrix.configuration.database.postgres;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * Date: 13/06/20
 *
 * @author Kushal Roy
 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.matrix-datasource.hibernate")
@Validated
public class DataMatrixDataSourceProperties {

  /* ---------------- Fields ---------------- */

  @NotNull
  private String ddlAuto;

  @NotNull
  private String showSql;

  @NotNull
  private String namingStrategy;

  @NotNull
  private String dialect;

  @NotNull
  private String generateStatistics;


}
