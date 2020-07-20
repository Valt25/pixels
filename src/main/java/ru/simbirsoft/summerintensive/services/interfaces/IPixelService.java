package ru.simbirsoft.summerintensive.services.interfaces;

import ru.simbirsoft.summerintensive.dto.PixelDto;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.models.User;

import java.util.List;

public interface IPixelService {
    List<Pixel> readLastPixels();
    void create(User user, PixelDto pixelDto);
    List<Pixel> findByUser(User user);

    long countActualByUsersCountry(String country);
    long countTotalByUsersCountry(String country);

    long countActualByUsersCity(String country);
    long countTotalByUsersCity(String country);

    long countActualByUser(String email);
    long countTotalByUser(String email);


    List<Pixel> findAll();
}
