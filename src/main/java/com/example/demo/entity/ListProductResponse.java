package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.List;

public class ListProductResponse {
    @JsonProperty("response_code")
    private String responseCode;

    @JsonProperty("response_message")
    private String responseMessage;

    @JsonProperty("data")
    private List<Product> data;

    @JsonProperty("metadata")
    private Metadata metadata;

    public ListProductResponse(String responseCode, String responseMessage, Page<Product> page) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = page.getContent();
        this.metadata = new Metadata(page);
    }

    // Inner class for metadata
    public static class Metadata {
        @JsonProperty("page_number")
        private int pageNumber;

        @JsonProperty("page_size")
        private int pageSize;

        @JsonProperty("total_elements")
        private long totalElements;

        @JsonProperty("total_pages")
        private int totalPages;

        public Metadata(Page<?> page) {
            if (page.getNumber() < 1) {
                this.pageNumber = 1;
            } else {
                this.pageNumber = page.getNumber();
            }
            this.pageSize = page.getSize();
            this.totalElements = page.getTotalElements();
            this.totalPages = page.getTotalPages();
        }

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public long getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(long totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
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

    public List<Product> getData() {
        return data;
    }

    public void setData(List<Product> data) {
        this.data = data;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}