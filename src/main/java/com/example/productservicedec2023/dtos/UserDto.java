package com.example.productservicedec2023.dtos;



import lombok.Getter;
import lombok.Setter;

import javax.management.relation.Role;
import java.util.List;


@Setter
@Getter
public class UserDto {
    private String name;
    private String email;
    private String hashedPassword;
    private List<Role> roles;
    private boolean isEmailVerified;
}
