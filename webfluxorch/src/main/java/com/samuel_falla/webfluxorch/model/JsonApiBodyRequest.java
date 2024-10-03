package com.samuel_falla.webfluxorch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;


public class JsonApiBodyRequest   {
  @JsonProperty("data")
  private List<GetEnigmaRequest> data;

  public JsonApiBodyRequest data(List<GetEnigmaRequest> data) {
    this.data = data;
    return this;
  }

  public JsonApiBodyRequest addDataItem(GetEnigmaRequest dataItem) {
    this.data.add(dataItem);
    return this;
  }


  public List<GetEnigmaRequest> getData() {
    return data;
  }

  public void setData(List<GetEnigmaRequest> data) {
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
    JsonApiBodyRequest jsonApiBodyRequest = (JsonApiBodyRequest) o;
    return Objects.equals(this.data, jsonApiBodyRequest.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(data);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JsonApiBodyRequest {\n");
    
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
