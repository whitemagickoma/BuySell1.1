package org.example.buysell.dao;

import org.example.buysell.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Сохранения продукта в базе данных
     * @param productDto вся информация относящиеся к продукту
     */
    public void createProduct(ProductDTO productDto) {
        String sql = "INSERT INTO products (" +
                "user_id," +
                "title," +
                "description," +
                "price, author," +
                "date_of_created," +
                "preview_image_id)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, productDto.getUserId(), productDto.getTitle(), productDto.getDescription(), productDto.getPrice(), productDto.getAuthor(), productDto.getDateOfCreated(), productDto.getPreviewImageId());
    }

    /**
     * Получение всех продуктов с базы данных
     * @param productDto вся информация о продукте
     * @return возвращает лист productDto объектов
     */
    public List<ProductDTO> getAllProducts(ProductDTO productDto) {
        String sql = "SELECT *" +
                "FROM products";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ProductDTO.class));
    }

    /**
     * Обновление информации о продукте в базе данных
     * @param productDto вся информация относящиеся к продукту
     * @return возвращает информацию о продукте с обновлением
     */
    public ProductDTO editProduct(ProductDTO productDto) {
        String sql = "UPDATE products SET" +
                "title = ?," +
                "description = ?," +
                "price = ?," +
                "author = ?," +
                "user_id = ?" +
                "WHERE id = ?";
        int changed = jdbcTemplate.update(sql, productDto.getTitle(), productDto.getDescription(), productDto.getPrice(), productDto.getAuthor(), productDto.getUserId(), productDto.getId());
        if (changed > 0) {
            return productDto;
        } else {
            return null;
        }
    }

    /**
     * Удаление продукта по ID с базы данных
     * @param id ID продукта
     */
    public int deleteProduct(Long id) {
        String sql = "DELETE\n" +
                "FROM products\n" +
                "WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
