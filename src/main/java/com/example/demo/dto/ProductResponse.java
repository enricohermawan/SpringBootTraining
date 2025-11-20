package com.example.demo.dto;

import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ProductResponse {
    @JsonProperty("response_code")
    private String responseCode;
    @JsonProperty("response_message")
    private String responseMessage;
    @JsonProperty("data")
    private Product product;

    public ProductResponse(String responseCode, String message, Product product) {
        this.responseCode = responseCode;
        this.responseMessage = message;
        this.product = product;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
