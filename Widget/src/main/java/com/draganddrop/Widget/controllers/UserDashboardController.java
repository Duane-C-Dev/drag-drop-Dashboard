package com.draganddrop.Widget.controllers;

import com.draganddrop.Widget.api.UserDashboardDto;
import com.draganddrop.Widget.services.UserDashboardService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users/{userId}/userDashboards")
public class UserDashboardController {

    private final UserDashboardService userDashboardService;

    public UserDashboardController(UserDashboardService userDashboardService) {
        this.userDashboardService = userDashboardService;
    }

    @GetMapping
    public List<UserDashboardDto> findAll(@PathVariable("userId") UUID userId) {
        return userDashboardService.findAll(userId);
    }

    @GetMapping("/{userDashboardId}")
    public UserDashboardDto find(@PathVariable("userDashboardId") UUID userDashBoardId) {
        return userDashboardService.find(userDashBoardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDashboardDto dto) {
        userDashboardService.create(dto);
    }

    @DeleteMapping("/{userDashboardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userDashboardId") UUID userDashBoardId) {
        userDashboardService.delete(userDashBoardId);
    }

    @PutMapping("/{userDashboardId}")
    public void update(@PathVariable("userDashboardId") UUID userDashBoardId, @RequestBody UserDashboardDto dto) {
        userDashboardService.update(userDashBoardId, dto);
    }
}
