package com.samuel_falla.webfluxorch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class JsonApiBodyResponseSuccess   {
  @JsonProperty("data")

  private List<GetEnigmaStepResponse> data;

  public JsonApiBodyResponseSuccess data(List<GetEnigmaStepResponse> data) {
    this.data = data;
    return this;
  }

  public JsonApiBodyResponseSuccess addDataItem(GetEnigmaStepResponse dataItem) {
    this.data.add(dataItem);
    return this;
  }

  public List<GetEnigmaStepResponse> getData() {
    return data;
  }

  public void setData(List<GetEnigmaStepResponse> data) {
    this.data = data;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonApiBodyResponseSuccess jsonApiBodyResponseSuccess = (JsonApiBodyResponseSuccess) o;
    return Objects.equals(this.data, jsonApiBodyResponseSuccess.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JsonApiBodyResponseSuccess {\n");
    
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
