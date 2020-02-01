package com.criticalmass.datamatrix.exception;

import java.io.FileNotFoundException;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/** @author Kushal Roy */
@ControllerAdvice
public class DataMatrixExceptionAdvice {

  private static final Logger log = LogManager.getLogger();

  @ExceptionHandler({NullPointerException.class})
  public ResponseEntity<String> handleNullPointerException(NullPointerException e) {
    return error(HttpStatus.SERVICE_UNAVAILABLE, e);
  }

  @ExceptionHandler({TaskRejectedException.class})
  public ResponseEntity<String> handleTaskRejectedException(TaskRejectedException e) {
    return error(HttpStatus.SERVICE_UNAVAILABLE, e);
  }

  @ExceptionHandler({HttpMessageNotReadableException.class})
  public ResponseEntity<String> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Required request body is missing");
  }

  @ExceptionHandler({IllegalArgumentException.class})
  public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
    return error(HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler({MissingServletRequestParameterException.class})
  public ResponseEntity<String> handleMissingServletRequestParameterException(
      MissingServletRequestParameterException e) {
    return error(HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler({UnsupportedOperationException.class})
  public ResponseEntity<String> handleUnsupportedOperationException(
      UnsupportedOperationException e) {
    return error(HttpStatus.BAD_REQUEST, e);
  }

  @ExceptionHandler({RuntimeException.class})
  public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
    return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
  }

  @ExceptionHandler({FileNotFoundException.class})
  public ResponseEntity<String> handleFileNotFoundException(FileNotFoundException e) {
    return error(HttpStatus.NOT_FOUND, e);
  }

  @ExceptionHandler({NoSuchElementException.class})
  public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
    return error(HttpStatus.NOT_FOUND, e);
  }

  @ExceptionHandler({ConcurrentModificationException.class})
  public ResponseEntity<String> handleConcurrentModificationException(
      ConcurrentModificationException e) {
    return error(HttpStatus.CONFLICT, e);
  }

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<String> handleDataIntegrityViolationException(
      DataIntegrityViolationException e) {
    return error(HttpStatus.CONFLICT, e);
  }

  private ResponseEntity<String> error(HttpStatus status, Exception e) {
    log.error("Exception Details: ", e);
    return ResponseEntity.status(status).body(e.getMessage());
  }
}
