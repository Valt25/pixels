package ru.simbirsoft.summerintensive.services.interfaces;


import ru.simbirsoft.summerintensive.dto.SignInDto;
import ru.simbirsoft.summerintensive.dto.TokenDto;

public interface SignInService {
    TokenDto signIn(SignInDto signInData);
}
