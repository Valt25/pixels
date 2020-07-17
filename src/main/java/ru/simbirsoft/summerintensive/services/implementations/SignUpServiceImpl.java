package ru.simbirsoft.summerintensive.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.simbirsoft.summerintensive.dto.SignUpDto;
import ru.simbirsoft.summerintensive.models.Role;
import ru.simbirsoft.summerintensive.models.State;
import ru.simbirsoft.summerintensive.models.User;
import ru.simbirsoft.summerintensive.repository.UsersRepository;
import ru.simbirsoft.summerintensive.services.interfaces.SignUpService;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public boolean signUp(SignUpDto form) {
        if (usersRepository.findByEmail(form.getEmail()).isPresent()) {
            return false;
        }
        User user = User.builder()
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .name(form.getName())
                .country(form.getCountry())
                .city(form.getCity())
                .state(State.CONFIRMED)
                .role(Role.USER)
                .build();

        usersRepository.save(user);
        return true;
    }
}
