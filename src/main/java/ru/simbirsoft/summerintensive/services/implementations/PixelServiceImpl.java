package ru.simbirsoft.summerintensive.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ru.simbirsoft.summerintensive.dto.PixelDto;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.models.User;
import ru.simbirsoft.summerintensive.repository.UsersRepository;
import ru.simbirsoft.summerintensive.services.interfaces.IPixelService;

import java.time.LocalDateTime;
import java.util.List;
import ru.simbirsoft.summerintensive.repository.PixelRepository;

@Service
public class PixelServiceImpl implements IPixelService {

    private final PixelRepository pixelRepository;

    @Autowired
    public PixelServiceImpl(PixelRepository pixelRepository) {
        this.pixelRepository = pixelRepository;
    }

    @Override
    public List<Pixel> readLastPixels() {
        return this.pixelRepository.findAllWithMaxCreated();
    }

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void create(User user, PixelDto pixelDto) {
        Pixel pixel = Pixel.builder()
                .x(pixelDto.getX())
                .y(pixelDto.getY())
                .color(pixelDto.getColor())
                .user(user)
                .created(LocalDateTime.now())
                .build();
        pixelRepository.save(pixel);
        user.setLastColorTime(LocalDateTime.now());
        usersRepository.save(user);
    }
}
