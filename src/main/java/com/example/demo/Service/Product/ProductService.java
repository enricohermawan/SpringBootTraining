package com.example.demo.Service.Product;

import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product getById(int id);
    Page<Product> listProducts(String name, String category,
                               BigDecimal minPrice, BigDecimal maxPrice,
                               String sortBy, String sortOrder, Integer page, Integer size);
}
