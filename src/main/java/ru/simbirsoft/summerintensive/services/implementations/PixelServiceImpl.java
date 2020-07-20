package ru.simbirsoft.summerintensive.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.simbirsoft.summerintensive.dto.PixelDto;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.models.User;
import ru.simbirsoft.summerintensive.repository.PixelRepository;
import ru.simbirsoft.summerintensive.repository.UsersRepository;
import ru.simbirsoft.summerintensive.services.interfaces.IPixelService;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<Pixel> findByUser(User user) {
        return pixelRepository.findByUser(user);
    }

    // подсчитывает текущее кол-во пикселей, закрашенных пользователями из указанной страны
    @Override
    public long countActualByUsersCountry(String country) {
        long counter=0;
        List<Pixel> actualPixels = readLastPixels(); // список актуальных пикселей
        for(Pixel p: actualPixels){
            if(p.getUser().getCountry().equals(country))
                counter++;
        }
        return counter;
    }

    // подсчитывает закрашенные пиксели за всё время пользователями из указанной страны
    @Override
    public long countTotalByUsersCountry(String country) {
        return pixelRepository.countPixelsByUserCountry(country);
    }


    @Override
    public long countActualByUsersCity(String city) {
        long counter=0;
        List<Pixel> actualPixels = readLastPixels(); // список актуальных пикселей
        for(Pixel p: actualPixels){
            if(p.getUser().getCity().equals(city))
                counter++;
        }
        return counter;
    }

    @Override
    public long countTotalByUsersCity(String city) {
        return pixelRepository.countPixelsByUserCity(city);
    }

    @Override
    public long countActualByUser(String email) {
        long counter=0;
        List<Pixel> actualPixels = readLastPixels(); // список актуальных пикселей
        for(Pixel p: actualPixels){
            if(p.getUser().getEmail().equals(email))
                counter++;
        }
        return counter;
    }

    @Deprecated
    @Override
    public long countTotalByUser(String email) {
        return 0;
    }


    @Override
    public List<Pixel> findAll(){
        return pixelRepository.findAll();
    }


}
