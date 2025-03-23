package com.example.PublicisSapient.DTO;

import com.example.PublicisSapient.Model.User;
import lombok.Data;
import java.util.List;

@Data
public class UserResponse {
    private List<User> users;
}