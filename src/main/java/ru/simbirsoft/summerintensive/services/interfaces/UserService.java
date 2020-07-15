package ru.simbirsoft.summerintensive.services.interfaces;

import ru.simbirsoft.summerintensive.models.User;

public interface UserService{
    void save(User user);

    User findByUsername(String username);
}
