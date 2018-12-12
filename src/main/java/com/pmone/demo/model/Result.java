
package com.pmone.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "recognitionResult"
})
public class Result {

  @JsonProperty("status")
  private String status;
  @JsonProperty("recognitionResult")
  private RecognitionResult recognitionResult;

  @JsonIgnore
  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  @JsonProperty("status")
  public void setStatus(String status) {
    this.status = status;
  }

  @JsonProperty("recognitionResult")
  public RecognitionResult getRecognitionResult() {
    return recognitionResult;
  }

  @JsonProperty("recognitionResult")
  public void setRecognitionResult(RecognitionResult recognitionResult) {
    this.recognitionResult = recognitionResult;
  }

}
