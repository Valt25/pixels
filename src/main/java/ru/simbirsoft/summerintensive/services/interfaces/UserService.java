package ru.simbirsoft.summerintensive.services.interfaces;

import ru.simbirsoft.summerintensive.models.User;

public interface UserService {
    long timeFromLastColor(User user);
    User findByEmail(String email);
}
