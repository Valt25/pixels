package ru.simbirsoft.summerintensive.services.interfaces;

import ru.simbirsoft.summerintensive.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    long timeFromLastColor(User user);
    User findByEmail(String email);
    long countByCountry(String country);
    long countByCity(String city);
    List<User> findAll();
}
