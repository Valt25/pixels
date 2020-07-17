package ru.simbirsoft.summerintensive.services.interfaces;

import ru.simbirsoft.summerintensive.dto.PixelDto;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.models.User;

import java.util.List;

public interface IPixelService {
    List<Pixel> readAll();
    void create(User user, PixelDto pixelDto);
}
