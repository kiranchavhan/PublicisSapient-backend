package com.example.PublicisSapient.Repository;

import com.example.PublicisSapient.Model.Address;
import com.example.PublicisSapient.Model.Coordinates;
import com.example.PublicisSapient.Model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        String url = "https://dummyjson.com/users";
        String response = restTemplate.getForObject(url, String.class);
        JsonNode root = objectMapper.readTree(response);

        List<User> users = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (JsonNode node : root.get("users")) {
            User user = new User();
            user.setId(node.get("id").asLong());
            user.setFirstName(node.get("firstName").asText());
            user.setLastName(node.get("lastName").asText());
            user.setAge(node.get("age").asInt());
            user.setGender(node.get("gender").asText());
            user.setEmail(node.get("email").asText());
            user.setPhone(node.get("phone").asText());
            user.setUsername(node.get("username").asText());
            user.setPassword(node.get("password").asText());
            user.setBirthDate(String.valueOf(LocalDate.parse(node.get("birthDate").asText(), formatter)));
            user.setImage(node.get("image").asText());

            // Parse Address
            JsonNode addressNode = node.get("address");
            if (addressNode != null) {
                Address address = new Address();
                address.setAddress(addressNode.get("address").asText());
                address.setCity(addressNode.get("city").asText());
                address.setState(addressNode.get("state").asText());
                address.setStateCode(addressNode.get("stateCode").asText());
                address.setPostalCode(addressNode.get("postalCode").asText());
                address.setCountry(addressNode.get("country").asText());

                // Parse Coordinates
                JsonNode coordinatesNode = addressNode.get("coordinates");
                if (coordinatesNode != null) {
                    Coordinates coordinates = new Coordinates();
                    coordinates.setLat(coordinatesNode.get("lat").asDouble());
                    coordinates.setLng(coordinatesNode.get("lng").asDouble());
                    address.setCoordinates(coordinates);
                }

                user.setAddress(address);
            }

            users.add(user);
        }

        userRepository.saveAll(users);
        System.out.println("Users successfully loaded into H2 database!");
    }
}
