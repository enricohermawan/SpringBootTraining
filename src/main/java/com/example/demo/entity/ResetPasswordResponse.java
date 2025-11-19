package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class ResetPasswordResponse {
    @JsonProperty("response_code")
    private String responseCode;
    @JsonProperty("response_message")
    private String responseMessage;
    @JsonProperty("data")
    private Object data;

    public ResetPasswordResponse(String responseCode, String message, User user) {
        this.responseCode = responseCode;
        this.responseMessage = message;
        if (user != null) {
            Map<String, Object> dataObj = new HashMap<>();
            dataObj.put("password", user.getPasswordHash());
            this.data = dataObj;
        }

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
