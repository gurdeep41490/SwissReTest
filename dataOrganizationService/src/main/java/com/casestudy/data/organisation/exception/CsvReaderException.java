package com.casestudy.data.organisation.exception;

public class CsvReaderException extends Exception{
  public CsvReaderException(String message) {
    super(message);
  }

  public CsvReaderException(String message, Throwable cause) {
    super(message, cause);
  }
}
