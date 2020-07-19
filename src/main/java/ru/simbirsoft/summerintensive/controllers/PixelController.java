package ru.simbirsoft.summerintensive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.simbirsoft.summerintensive.dto.*;
import ru.simbirsoft.summerintensive.models.Role;
import ru.simbirsoft.summerintensive.models.User;
import ru.simbirsoft.summerintensive.repository.PixelRepository;
import ru.simbirsoft.summerintensive.security.jwt.details.UserDetailsImpl;
import ru.simbirsoft.summerintensive.services.interfaces.IPixelService;
import ru.simbirsoft.summerintensive.services.interfaces.RollbackTimeService;
import ru.simbirsoft.summerintensive.services.interfaces.UserService;


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
                .pixels(PixelDto.from(pixelService.readLastPixels()))
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

    // посмотреть статистику по стране
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/v1/admin/statistic/country/{countryName}")
    public ResponseEntity getCountriesStat(@PathVariable String countryName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = userService.findByEmail(userDetails.getUsername());
        if (user.getRole() != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        long _usersCount = userService.countByCountry(countryName);
        long _cellsCount = pixelService.findByUser(user).size();
        _cellsCount = pixelService.countActualByUsersCountry(countryName);
        long _allCellsCount = pixelService.countTotalByUsersCountry(countryName);
        StatisticResponse resp = StatisticResponse.builder().
                usersCount(_usersCount).
                cellsCount(_cellsCount).
                allCellsCount(_allCellsCount).
                build();
        return ResponseEntity.ok(resp);
    }

    // посмотреть статистику по городу
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/v1/admin/statistic/city/{cityName}")
    public ResponseEntity getCityStat(@PathVariable String cityName){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = userService.findByEmail(userDetails.getUsername());
        if (user.getRole() != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        long _usersCount = userService.countByCity(cityName);
        long _cellsCount = pixelService.findByUser(user).size();
        _cellsCount = pixelService.countActualByUsersCity(cityName);
        long _allCellsCount = pixelService.countTotalByUsersCity(cityName);
        StatisticResponse resp = StatisticResponse.builder().
                allCellsCount(_allCellsCount).
                cellsCount(_cellsCount).
                usersCount(_usersCount).
                build();
        return ResponseEntity.ok(resp);
    }

    // посмотреть статистику по пользователю
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/v1/admin/statistic/user/")
    public ResponseEntity getUserStat(@RequestParam String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = userService.findByEmail(userDetails.getUsername());
        if (user.getRole() != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        User user1 = userService.findByEmail(email);

        long _cellsCount = pixelService.countActualByUser(email);
        long _allCellsCount = pixelService.findByUser(user1).size();
        UserStatisticResponse resp = UserStatisticResponse.builder().
                history(PixelHistoryDto.from(pixelService.findByUser(user1))).
                allCellsCount(_allCellsCount).
                cellsCount(_cellsCount).
                build();
        return ResponseEntity.ok(resp);
    }

}
