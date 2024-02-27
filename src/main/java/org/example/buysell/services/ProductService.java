package org.example.buysell.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.buysell.dao.ProductDao;
import org.example.buysell.dao.UserDao;
import org.example.buysell.dto.ImageDto;
import org.example.buysell.dto.ProductDto;
import org.example.buysell.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

    private final ProductDao productDao;
    private final UserDao userDao;

    // Создание нового продукта
    public void createProduct(Principal principal, ProductDto productDto, MultipartFile file) throws IOException {
        ImageDto imageDto = null;
        if (file != null && !file.isEmpty()) {
            imageDto = toImageEntity(file);
            imageDto.setPreviewImage(true);
            productDto.addImageToProduct(imageDto);
        }
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

    public UserDto getUserByPrincipal(Principal principal) {
        if (principal == null) return new UserDto();
        return userDao.getUserByEmail(principal.getName());
    }

    private ImageDto toImageEntity(MultipartFile file) throws IOException {
        ImageDto imageDto = new ImageDto();
        imageDto.setName(file.getName());
        imageDto.setSize(file.getSize());
        imageDto.setBytes(file.getBytes());
        return imageDto;
    }
}
