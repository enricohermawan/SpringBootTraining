package com.example.demo.Service.Product;

import com.example.demo.Service.Product.Helper.ProductSpecification;
import com.example.demo.entity.Product;
import com.example.demo.repository.Product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductImpl implements ProductService{
    @Autowired
   private final ProductRepository productRepository;

    public ProductImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(UUID.fromString(String.valueOf(id)));
    }

    @Override
    public Page<Product> listProducts(String name, String category,
                                      BigDecimal minPrice, BigDecimal maxPrice,
                                      String sortBy, String sortOrder, Integer page, Integer size) {

        Sort sort = Sort.by(
                sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC,
                sortBy
        );

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(
                ProductSpecification.filter(name, category, minPrice, maxPrice),
                pageable
        );
    }
}
