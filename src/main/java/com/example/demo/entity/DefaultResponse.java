package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class DefaultResponse {
    @JsonProperty("response_code")
    private String responseCode;
    @JsonProperty("response_message")
    private String responseMessage;

    public DefaultResponse(String responseCode, String message) {
        this.responseCode = responseCode;
        this.responseMessage = message;

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

}
