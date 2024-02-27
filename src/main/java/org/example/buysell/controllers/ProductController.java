package org.example.buysell.controllers;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.example.buysell.dto.ProductDto;
import org.example.buysell.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

//@Api(tags = "ProductController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    // Создание продукта
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductDto productDto) {
        productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    // Получение существующих продуктов
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts (@RequestParam(name = "title", required = false) String title, Principal principal) {
        List<ProductDto> productDtoList = productService.getAllProducts(new ProductDto());
        return ResponseEntity.ok(productDtoList);
    }

    // Изменение продукта по ID
    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductDto> editProduct (@PathVariable Long id,@RequestBody ProductDto productDto) {
        productDto.setId(id);
        ProductDto editedProduct = productService.editProduct(productDto);

        if (editedProduct != null) {
            return ResponseEntity.ok(editedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Удаление продукта по ID
    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteProduct (@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return HttpStatus.OK;
    }
}
