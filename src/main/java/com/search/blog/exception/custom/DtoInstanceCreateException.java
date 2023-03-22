package com.search.blog.exception.custom;

public class DtoInstanceCreateException extends IllegalStateException {

  public DtoInstanceCreateException() {
    throw new IllegalStateException("DTO instance creation is not allowed");
  }
}
