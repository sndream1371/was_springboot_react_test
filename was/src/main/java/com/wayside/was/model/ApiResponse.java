package com.wayside.was.model;

public class ApiResponse<T> {
  private boolean success;
  private T data;
  private String message;

  public ApiResponse(boolean success, T data, String message) {
    this.success = success;
    this.data = data;
    this.message = message;
  }

  // 성공 응답 생성 메소드
  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(true, data, "Operation completed successfully");
  }

  // 실패 응답 생성 메소드
  public static <T> ApiResponse<T> failure(String message) {
    return new ApiResponse<>(false, null, message);
  }

  // Getters
  public boolean isSuccess() {
    return success;
  }

  public T getData() {
    return data;
  }

  public String getMessage() {
    return message;
  }

  // AOP 응답 통신 로그 사용
  @Override
  public String toString() {
    return "ApiResponse [success=" + success + ", data=" + data + ", message=" + message + "]";
  }

}
