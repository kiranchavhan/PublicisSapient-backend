package com.example.PublicisSapient.Service;

import com.example.PublicisSapient.DTO.UserResponse;
import com.example.PublicisSapient.Model.User;
import com.example.PublicisSapient.Repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserClient userClient;

    public UserService(UserRepository userRepository, UserClient userClient) {
        this.userRepository = userRepository;
        this.userClient = userClient;
    }
    //     Load users from external API into H2 database
    public void loadUsersIntoDB() {
        UserResponse response = userClient.getUsers();
        List<User> users = response.getUsers();
        userRepository.saveAll(users);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByRole(String role) {
        return userRepository.findByRole(role);
    }

    public List<User> getUsersSortedByAge(boolean ascending) {
        return ascending ? userRepository.findAllByOrderByAgeAsc() : userRepository.findAllByOrderByAgeDesc();
    }

    public Optional<User> findUserByIdOrSsn(Long id, String ssn) {
        return userRepository.findByIdOrSsn(id, ssn);
    }
}
