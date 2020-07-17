package ru.simbirsoft.summerintensive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.summerintensive.dto.SignUpDto;
import ru.simbirsoft.summerintensive.services.interfaces.SignUpService;

import javax.validation.Valid;

@RestController
public class SignUpController {
    @Autowired
    private SignUpService service;

    @PostMapping("/v1/users")
    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpDto form, BindingResult bindingResult) {
        System.out.println(form);
        if (!form.getPassword().equals(form.getPasswordConfirmation()) || bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body("invalid data");
        }

        if (service.signUp(form)) {
            return ResponseEntity.ok("Ok");
        }
        return ResponseEntity.badRequest().body("User already exist");
    }
}
