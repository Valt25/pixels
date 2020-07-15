package ru.simbirsoft.summerintensive.controllers;

import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.summerintensive.models.User;
import ru.simbirsoft.summerintensive.services.interfaces.SecurityService;
import ru.simbirsoft.summerintensive.services.interfaces.UserService;

@RestController
public class UserController {
    private UserService userService;
    private SecurityService securityService;

    @PostMapping("/registration")
    public User registration(@RequestBody User user) {
        userService.save(user);
        securityService.autoLogin(user.getEmail(), user.getPassword());
        return userService.findByUsername(user.getEmail());
    }
}
