package com.salespage.salepageschedule.app.dtos.bankDtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Oath2CassoDto {

  @NotBlank(message = "Client ID là bắt buộc")
  @JsonProperty("client_id")
  private String clientId;

  private String scope;

  @NotBlank(message = "URL chuyển hướng là bắt buộc")
  @JsonProperty("redirect_uri")
  private String redirectUri;

  @NotBlank(message = "Loại phản hồi là bắt buộc")
  @JsonProperty("response_type")
  private String responseType;

  private String state;
}
