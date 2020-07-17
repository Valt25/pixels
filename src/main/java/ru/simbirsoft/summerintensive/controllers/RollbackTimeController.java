package ru.simbirsoft.summerintensive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.simbirsoft.summerintensive.dto.RollbackDto;
import ru.simbirsoft.summerintensive.models.Role;
import ru.simbirsoft.summerintensive.models.RollbackTime;
import ru.simbirsoft.summerintensive.models.User;
import ru.simbirsoft.summerintensive.security.jwt.details.UserDetailsImpl;
import ru.simbirsoft.summerintensive.services.interfaces.RollbackTimeService;
import ru.simbirsoft.summerintensive.services.interfaces.UserService;

@RestController
public class RollbackTimeController {

    @Autowired
    RollbackTimeService rollbackTimeService;

    @Autowired
    UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("v1/rollback")
    public ResponseEntity getRollback() {
        return ResponseEntity.ok(new RollbackDto(rollbackTimeService.getRollbackTime()));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("v1/rollback")
    public ResponseEntity setRollback(@RequestBody RollbackDto form) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = userService.findByEmail(userDetails.getUsername());
        if (user.getRole() != Role.ADMIN) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        rollbackTimeService.setRollbackTime(form.rollback_time);
        return ResponseEntity.ok().build();
    }
}
