package com.samuel_falla.webfluxorch.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.validation.annotation.Validated;

import java.util.Objects;


public class GetEnigmaRequest   {
  @JsonProperty("header")
  private Header header = null;

  @JsonProperty("enigma")
  private String enigma = null;

  public GetEnigmaRequest header(Header header) {
    this.header = header;
    return this;
  }


  public Header getHeader() {
    return header;
  }

  public void setHeader(Header header) {
    this.header = header;
  }

  public GetEnigmaRequest enigma(String enigma) {
    this.enigma = enigma;
    return this;
  }


  public String getEnigma() {
    return enigma;
  }

  public void setEnigma(String enigma) {
    this.enigma = enigma;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetEnigmaRequest getEnigmaRequest = (GetEnigmaRequest) o;
    return Objects.equals(this.header, getEnigmaRequest.header) &&
        Objects.equals(this.enigma, getEnigmaRequest.enigma);
  }

  @Override
  public int hashCode() {
    return Objects.hash(header, enigma);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetEnigmaRequest {\n");
    
    sb.append("    header: ").append(toIndentedString(header)).append("\n");
    sb.append("    enigma: ").append(toIndentedString(enigma)).append("\n");
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
