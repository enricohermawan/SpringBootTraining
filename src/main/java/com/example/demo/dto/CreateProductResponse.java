package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreateProductResponse {
    @JsonProperty("response_code")
    private String responseCode;
    @JsonProperty("response_message")
    private String responseMessage;
    @JsonProperty("data")
    private Object data;

    public CreateProductResponse(String responseCode, String message, UUID id) {
        this.responseCode = responseCode;
        this.responseMessage = message;
        Map data = new HashMap();
        data.put("id", id);
        this.data = data;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
