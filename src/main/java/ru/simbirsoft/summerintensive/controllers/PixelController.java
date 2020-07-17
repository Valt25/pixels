package ru.simbirsoft.summerintensive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.services.interfaces.IPixelService;

import java.util.List;

@RestController
public class PixelController {

    private final IPixelService pixelService;

    @Autowired
    public PixelController(IPixelService pixelService) {
        this.pixelService = pixelService;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/pixels")
    public List<Pixel> sayHello() {
        return pixelService.readAll();
    }
}
