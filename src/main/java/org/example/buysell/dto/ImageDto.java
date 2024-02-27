package org.example.buysell.dto;

import lombok.Data;

import java.util.Objects;

@Data
public class ImageDto {

    private Long id;
    private String name;
    private Long size;
    private boolean isPreviewImage;
    private byte[] bytes;
    private ProductDto productDto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageDto imageDto = (ImageDto) o;
        return Objects.equals(id, imageDto.id) && Objects.equals(productDto, imageDto.productDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productDto);
    }
}
