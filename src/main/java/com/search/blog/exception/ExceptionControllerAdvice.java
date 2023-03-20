package com.search.blog.exception;

import com.search.blog.exception.custom.BlogSearchException;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ExceptionControllerAdvice {

  /**
   * 인자 값의 변수형 또는 enum type 이 일치하지 않아 binding 못할 경우 발생하는 예외 처리
   */
  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  protected ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex, HttpServletRequest request) {
    ExceptionResponse response = ExceptionResponse.of(ex, request);
    return ResponseEntity
        .status(response.getStatus())
        .body(response);
  }
  /**
   * 지원하지 않은 HTTP method 호출 할 경우 발생
   */
  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException ex, HttpServletRequest request) {
    HttpHeaders headers = new HttpHeaders();

    Set<HttpMethod> supportedMethods = ex.getSupportedHttpMethods();
    if (!CollectionUtils.isEmpty(supportedMethods)) {
      headers.setAllow(supportedMethods);
    }
    ExceptionResponse response = ExceptionResponse.of(ExceptionCode.METHOD_NOT_ALLOWED, request);

    return ResponseEntity
        .status(response.getStatus())
        .headers(headers)
        .body(response);
  }

  /**
   * 그 외 발생하는 모든 예외는 BAD_REQUEST 로 처리
   */
  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ExceptionResponse> handleException(Exception ex, HttpServletRequest request) {
    ExceptionResponse response = ExceptionResponse.of(ExceptionCode.INTERNAL, request, ex.getMessage());

    return ResponseEntity
        .status(response.getStatus())
        .body(response);
  }

  /**
   * Wapl 비지니스 로직에서 발생시킨 모든 예외 처리
   */
  @ExceptionHandler(BlogSearchException.class)
  protected ResponseEntity<ExceptionResponse> handleBusinessException(BlogSearchException ex, HttpServletRequest request) {
    ExceptionResponse response = ExceptionResponse.of(ex.getExceptionCode(), request);

    return ResponseEntity
        .status(response.getStatus())
        .body(response);
  }


}
