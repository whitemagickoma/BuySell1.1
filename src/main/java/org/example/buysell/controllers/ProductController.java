package org.example.buysell.controllers;

import lombok.RequiredArgsConstructor;
import org.example.buysell.dto.ProductDTO;
import org.example.buysell.dto.ResponseDTO;
import org.example.buysell.dto.ResponseUtility;
import org.example.buysell.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    /**
     * {@code Post /api/product/create} <br/>
     * Используется для создания нового продукта
     * @param file изображение
     * @param productDto вся информация относящиеся к продукту
     * @param principal
     * @return String возвращает статус операции в виде строки "Product created successfully"
     * @throws IOException
     */
    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestParam("file") MultipartFile file, @RequestBody ProductDTO productDto, Principal principal) throws IOException {
        productService.createProduct(principal, productDto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
    }

    /**
     * {@code Get /api/product/} <br/>
     * Используется для получения всех продуктов
     * @param title получения продукта по названии
     * @param principal
     * @return List Product возвращает лист продуктов
     */
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts (@RequestParam(name = "title", required = false) String title, Principal principal) {
        List<ProductDTO> productDTOList = productService.getAllProducts(new ProductDTO());
        return ResponseEntity.ok(productDTOList);
    }

    /**
     * {@code Put /api/product/edit/id} <br/>
     * Используется для редактирования информации продукта
     * @param id ID продукта
     * @param productDto - вся информации относящиеся к продукту
     * @return ProductDTO возвращает статус продукта
     */
    @PutMapping("/edit/{id}")
    public ResponseEntity<ProductDTO> editProduct (@PathVariable Long id, @RequestBody ProductDTO productDto) {
        productDto.setId(id);
        ProductDTO editedProduct = productService.editProduct(productDto);

        if (editedProduct != null) {
            return ResponseEntity.ok(editedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code Delete /api/product/delete/id} <br/>
     * Используется для удаления продукта по ID
     * @param id ID продукта
     * @return возвращает статус операции
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteProduct (@PathVariable("id") Long id) {
        boolean status = productService.deleteProduct(id);
        String message = status ? "Product deleted successfully" : "Failed to delete product";

        return ResponseUtility.generateResponse(status, message);
    }
}
