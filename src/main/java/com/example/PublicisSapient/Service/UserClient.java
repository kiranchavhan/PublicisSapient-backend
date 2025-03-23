package com.example.PublicisSapient.Service;
import com.example.PublicisSapient.DTO.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "userClient", url = "https://dummyjson.com")
public interface UserClient {
    @GetMapping("/users")
    UserResponse getUsers();
}