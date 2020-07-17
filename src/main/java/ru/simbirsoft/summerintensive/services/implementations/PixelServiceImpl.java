package ru.simbirsoft.summerintensive.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.services.interfaces.IPixelService;
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

    @Override
    public Pixel storePixel(Pixel pixel){
        return pixelRepository.save(pixel);
    }


}
