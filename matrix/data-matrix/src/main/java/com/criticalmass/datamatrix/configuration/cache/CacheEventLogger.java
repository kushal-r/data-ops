package com.criticalmass.datamatrix.configuration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.springframework.stereotype.Service;

/**
 * Date: 18/02/20
 *
 * @author Kushal Roy
 */
@Service
public class CacheEventLogger implements CacheEventListener<Object, Object> {

  private static final Logger log = LogManager.getLogger();

  @Override
  public void onEvent(CacheEvent<? extends Object, ? extends Object> cacheEvent) {
    log.info("Cache event = {}, Key = {},  Old value = {}, New value = {}", cacheEvent.getType(),
        cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
  }
}

