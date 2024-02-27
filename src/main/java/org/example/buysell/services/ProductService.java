package org.example.buysell.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.buysell.dao.ProductDao;
import org.example.buysell.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;

    // Создание нового продукта
    public void createProduct(ProductDto productDto) {
        log.info("Saving new product title: {}", productDto.getTitle());
        productDao.createProduct(productDto);
    }

    // Получение всех продуктов
    public List<ProductDto> getAllProducts(ProductDto productDto) {
        return productDao.getAllProducts(productDto);
    }

    // Изменение продукта
    public ProductDto editProduct(ProductDto productDto) {
        return productDao.editProduct(productDto);
    }

    // Удаление продукта по ID
    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}
