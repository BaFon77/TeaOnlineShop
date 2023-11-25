package com.example.shop.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * A DTO for the {@link com.example.shop.entity.User} entity
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private Integer id;
    @Size(max = 50)
    @NotNull
    private String firstname;
    @Size(max = 50)
    @NotNull
    private String lastname;
    private String name;
    @Size(max = 20)
    @NotNull
    private String userrole;
}