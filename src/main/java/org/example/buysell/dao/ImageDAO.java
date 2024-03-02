package org.example.buysell.dao;

import lombok.RequiredArgsConstructor;
import org.example.buysell.dto.ImageDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImageDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Сохранение изображения в базе данных
     * @param imageDto вся информация про изображения
     */
    public void saveImage(ImageDTO imageDto) {
        String sql = "INSERT INTO images (" +
                "product_id," +
                "name, size," +
                "is_preview_image," +
                "bytes)";
        jdbcTemplate.update(sql, imageDto.getProductDto().getId(), imageDto.getName(), imageDto.getSize(), imageDto.isPreviewImage(), imageDto.getBytes());
    }

    /**
     * Получения изображения с базы данных по ID
     * @param id ID изображения
     * @return возвращает лист imageDto объектов по ID
     */
    public List<ImageDTO> getImageById(Long id) {
        String sql = "SELECT *" +
                "FROM  images" +
                "WHERE id = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ImageDTO.class));
    }

    /**
     * Удаление изображения с базы данных по ID
     * @param id ID изображения
     */
    public void deleteImage(Long id) {
        String sql = "DELETE" +
                "FROM images" +
                "WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }
}
