package org.example.buysell.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.buysell.dao.ProductDAO;
import org.example.buysell.dao.UserDAO;
import org.example.buysell.dto.ImageDTO;
import org.example.buysell.dto.ProductDTO;
import org.example.buysell.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductDAO productDao;
    private final UserDAO userDao;

    /**
     * Создание нового продукта
     * @param principal
     * @param productDto вся информация относящиеся к продукту
     * @param file изображение
     * @throws IOException
     */
    public void createProduct(Principal principal, ProductDTO productDto, MultipartFile file) throws IOException {
        ImageDTO imageDto = null;
        if (file != null && !file.isEmpty()) {
            imageDto = toImageEntity(file);
            imageDto.setPreviewImage(true);
            productDto.addImageToProduct(imageDto);
        }
        log.info("Saving new product title: {}", productDto.getTitle());
        productDao.createProduct(productDto);
    }

    /**
     * Получение списка всех пользователей
     * @param productDto вся информация отнасящиеся к продукту
     * @return возвращает лист productDto
     */
    public List<ProductDTO> getAllProducts(ProductDTO productDto) {
        return productDao.getAllProducts(productDto);
    }

    /**
     * Редактирование продукта
     * @param productDto вся информация относящиеся к продукту
     * @return
     */
    public ProductDTO editProduct(ProductDTO productDto) {
        return productDao.editProduct(productDto);
    }

    /**
     * Удаление продукта по ID
     * @param id ID продукта
     */
    public boolean deleteProduct(Long id) {
        try {
            return productDao.deleteProduct(id) == 1 ? true : false;
        } catch (Exception e){
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public UserDTO getUserByPrincipal(Principal principal) {
        if (principal == null) return new UserDTO();
        return userDao.getUserByEmail(principal.getName());
    }

    private ImageDTO toImageEntity(MultipartFile file) throws IOException {
        ImageDTO imageDto = new ImageDTO();
        imageDto.setName(file.getName());
        imageDto.setSize(file.getSize());
        imageDto.setBytes(file.getBytes());
        return imageDto;
    }
}
