package org.example.buysell.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.*;

@Data
public class UserDto {

    private Long id;

    @NotNull
    private String name;

    @Email
    @Size(min = 3, max = 64)
    private String email;

    @NotNull
    @Size(min = 12, max = 12)
    private String phoneNumber;

    private boolean active;

    @NotNull
    @Size(min = 8, max = 10)
    private String password;

    private List<ProductDto> productDtoList = new ArrayList<>();

    public String getUsername() {
        return email;
    }
}
