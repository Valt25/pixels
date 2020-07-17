package ru.simbirsoft.summerintensive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.repository.PixelRepository;
import ru.simbirsoft.summerintensive.services.interfaces.IPixelService;

import java.util.List;

@RestController
public class PixelController {

    private final IPixelService pixelService;
    private final PixelRepository pixelRepo;
    @Autowired
    public PixelController(IPixelService pixelService, PixelRepository pixelRepo) {
        this.pixelService = pixelService;
        this.pixelRepo = pixelRepo;
    }

    @GetMapping("/pixels")
    public List<Pixel> pixelsList() {
        return pixelService.readLastPixels();
    }

    @PostMapping("/v1/cell/put")
    public Pixel fillPixel(@RequestBody Pixel pixel){
        return pixelService.storePixel(pixel);
    }

}
