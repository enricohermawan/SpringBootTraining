package com.example.demo.controller.Product;

import com.example.demo.Service.Product.ProductService;
import com.example.demo.dto.ProductRequest;
import com.example.demo.dto.CreateProductResponse;
import com.example.demo.dto.ListProductResponse;
import com.example.demo.dto.ProductResponse;
import com.example.demo.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<ListProductResponse> listProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortOrder,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Page<Product> products = productService.listProducts(
                name, category, minPrice, maxPrice, sortBy, sortOrder, page, size
        );

        if (products.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ListProductResponse("99", "Not found", products));
        }

        return ResponseEntity.ok(new ListProductResponse("00", "Success", products));
    }

    @PostMapping("/product")
    public ResponseEntity<CreateProductResponse> createProduct(
            @RequestBody ProductRequest request
    ){
        Product product = new Product();
        product.setName(request.getName());
        product.setCode(request.getCode());
        product.setCategory(request.getCategory());
        product.setAmount(request.getAmount());
        product.setPrice(request.getPrice());

        UUID id = productService.addProduct(product);

        if (id == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new CreateProductResponse("99", "Something went wrong", null));
        }

        return ResponseEntity.ok(new CreateProductResponse("00", "Success create product", id));
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<ProductResponse> editProduct(
            @RequestBody ProductRequest request,
            @PathVariable String id
    ){
        Product product = new Product();
        product.setId(UUID.fromString(id));
        product.setName(request.getName());
        product.setCode(request.getCode());
        product.setCategory(request.getCategory());
        product.setAmount(request.getAmount());
        product.setPrice(request.getPrice());

        product = productService.editProduct(product);

        if (product == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ProductResponse("99", "Product not found", null));
        }

        return ResponseEntity.ok(new ProductResponse("00", "Success edit product", product));
    }

}