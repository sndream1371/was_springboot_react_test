package com.wayside.was.model.log;

import java.time.LocalDateTime;

public class RequestResponseLog {
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRequestUri() {
    return requestUri;
  }

  public void setRequestUri(String requestUri) {
    this.requestUri = requestUri;
  }

  public String getRequestMethod() {
    return requestMethod;
  }

  public void setRequestMethod(String requestMethod) {
    this.requestMethod = requestMethod;
  }

  public String getRequestBody() {
    return requestBody;
  }

  public void setRequestBody(String requestBody) {
    this.requestBody = requestBody;
  }

  public String getResponseBody() {
    return responseBody;
  }

  public void setResponseBody(String responseBody) {
    this.responseBody = responseBody;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  private String requestUri;
  private String requestMethod;
  private String requestBody;
  private String responseBody;
  private LocalDateTime timestamp;

  @Override
  public String toString() {
    return "RequestResponseLog [id=" + id + ", requestUri=" + requestUri + ", requestMethod=" + requestMethod
        + ", requestBody=" + requestBody + ", responseBody=" + responseBody + ", timestamp=" + timestamp + "]";
  }

}
