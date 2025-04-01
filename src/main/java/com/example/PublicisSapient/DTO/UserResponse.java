package com.example.PS.DTO;

import com.example.PS.Model.User;
import lombok.Data;
import java.util.List;

@Data
public class UserResponse {
    private List<User> users;
}
