package ru.simbirsoft.summerintensive.services.interfaces;

import ru.simbirsoft.summerintensive.dto.SignUpDto;

public interface SignUpService {
    boolean signUp(SignUpDto form);
}