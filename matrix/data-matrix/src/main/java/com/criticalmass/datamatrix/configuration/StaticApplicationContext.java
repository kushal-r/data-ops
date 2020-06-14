package com.criticalmass.datamatrix.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * Date: 13/06/20
 *
 * @author Kushal Roy
 */
@Configuration
public class StaticApplicationContext implements ApplicationContextAware {

  private static ApplicationContext applicationContext;


  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {

    applicationContext = context;
  }


  public static ApplicationContext getContext() {

    return applicationContext;
  }


}
