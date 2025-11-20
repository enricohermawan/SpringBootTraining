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

        int pageIndex = (page != null && page > 0) ? page - 1 : 0;
        int pageSize = (size != null && size > 0) ? size : 10; // default size

        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        return productRepository.findAll(
                ProductSpecification.filter(name, category, minPrice, maxPrice),
                pageable
        );
    }

    @Override
    public UUID addProduct(Product product) {
        product = productRepository.save(product);
        System.out.println(product.getId());

        return product.getId();
    }

    @Override
    public Product editProduct(Product product) {
        Product oldProduct = productRepository.findById(product.getId())
                .orElse(null);

        if (oldProduct == null){
            return null;
        }

        if (product.getName() != null) {
            oldProduct.setName(product.getName());
        }
        if (product.getCategory() != null) {
            oldProduct.setCategory(product.getCategory());
        }
        if (product.getPrice() != null) {
            oldProduct.setPrice(product.getPrice());
        }
        if (product.getCode() != null) {
            oldProduct.setCode(product.getCode());
        }
        if (product.getAmount() != null) {
            oldProduct.setAmount(product.getAmount());
        }

        return productRepository.save(oldProduct);
    }


}
