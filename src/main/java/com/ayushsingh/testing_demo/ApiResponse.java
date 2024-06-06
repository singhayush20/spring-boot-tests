package com.ayushsingh.testing_demo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {

  private String message;
  private T data;
  private Integer code;

  public ApiResponse(T data) {
    this.data = data;
    this.message = "SUCCESS";
    this.code = 2000;
  }

  public ApiResponse(String message, T data, Integer code) {
    this.message = message;
    this.data = data;
    this.code = code;
  }
}
