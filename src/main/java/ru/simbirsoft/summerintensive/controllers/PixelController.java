package ru.simbirsoft.summerintensive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.simbirsoft.summerintensive.dto.PixelDto;
import ru.simbirsoft.summerintensive.dto.PixelListResponse;
import ru.simbirsoft.summerintensive.dto.PutErrorDto;
import ru.simbirsoft.summerintensive.dto.SignUpDto;
import ru.simbirsoft.summerintensive.models.Pixel;
import ru.simbirsoft.summerintensive.models.User;
import ru.simbirsoft.summerintensive.repository.RollbackTimeRepository;
import ru.simbirsoft.summerintensive.security.jwt.details.UserDetailsImpl;
import ru.simbirsoft.summerintensive.services.interfaces.IPixelService;
import ru.simbirsoft.summerintensive.services.interfaces.RollbackTimeService;
import ru.simbirsoft.summerintensive.services.interfaces.UserService;

import java.util.List;

@RestController
public class PixelController {

    private final IPixelService pixelService;

    @Autowired
    public PixelController(IPixelService pixelService) {
        this.pixelService = pixelService;
    }

    @Autowired
    UserService userService;

    @Autowired
    RollbackTimeService rollbackTimeService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/v1/pixels")
    public ResponseEntity<PixelListResponse> sayHello() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = userService.findByEmail(userDetails.getUsername());
        long left_time = rollbackTimeService.getRollbackTime() - userService.timeFromLastColor(user);
        if (left_time < 0) {
            left_time = 0;
        }
        PixelListResponse resp = PixelListResponse.builder()
                .pixels(PixelDto.from(pixelService.readAll()))
                .rollback_time(left_time)
                .build();
        return ResponseEntity.ok(resp);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/v1/pixels")
    public ResponseEntity put(@RequestBody PixelDto form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = userService.findByEmail(userDetails.getUsername());
        long left_time = rollbackTimeService.getRollbackTime() - userService.timeFromLastColor(user);
        if (left_time < 0) {
            left_time = 0;
        }
        if (left_time  > 0) {
            PutErrorDto err = PutErrorDto.builder().time_left(left_time).build();
            return ResponseEntity.badRequest().body(err);
        }
        pixelService.create(user, form);
        return ResponseEntity.ok().build();
    }
}
