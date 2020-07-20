package ru.simbirsoft.summerintensive.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbirsoft.summerintensive.models.User;
import ru.simbirsoft.summerintensive.repository.UsersRepository;
import ru.simbirsoft.summerintensive.services.interfaces.UserService;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UsersRepository usersRepository;

    @Override
    public long timeFromLastColor(User user) {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.MIN) - user.getLastColorTime().toEpochSecond(ZoneOffset.MIN);
    }

    @Override
    public User findByEmail(String email) {
        return usersRepository.findByEmail(email).get();
    }

    @Override
    public long countByCountry(String country) {
        return usersRepository.countByCountry(country);
    }

    @Override
    public long countByCity(String city) {
        return usersRepository.countByCity(city);
    }

    @Override
    public List<User>  findAll(){
        return usersRepository.findAll();
    }
}