package com.criticalmass.datamatrix.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/** @author Kushal Roy */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
public class AuditorConfig {}
