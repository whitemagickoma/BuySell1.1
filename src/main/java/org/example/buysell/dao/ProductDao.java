package org.example.buysell.dao;

import org.example.buysell.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Создание нового продукта
    public void createProduct(ProductDto productDto) {
        String sql = "INSERT INTO products (user_id, title, description, price, author, date_of_created, preview_image_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, productDto.getUserId(), productDto.getTitle(), productDto.getDescription(), productDto.getPrice(), productDto.getAuthor(), productDto.getDateOfCreated(), productDto.getPreviewImageId());
    }

    // Получение всех продуктов
    public List<ProductDto> getAllProducts(ProductDto productDto) {
        String sql = "SELECT * FROM products";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductDto.class));
    }

    // Изменение продукта по ID
    public ProductDto editProduct(ProductDto productDto) {
        String sql = "UPDATE products SET title = ?, description = ?, price = ?, author = ?, user_id = ? WHERE id = ?";
        int changed = jdbcTemplate.update(sql, productDto.getTitle(), productDto.getDescription(), productDto.getPrice(), productDto.getAuthor(), productDto.getUserId(), productDto.getId());
        if (changed > 0) {
            return productDto;
        } else {
            return null;
        }
    }

    // Удаление продукта по ID
    public void deleteProduct(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
