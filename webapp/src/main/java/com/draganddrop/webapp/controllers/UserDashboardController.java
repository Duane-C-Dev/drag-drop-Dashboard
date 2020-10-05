package com.draganddrop.webapp.controllers;


import com.draganddrop.webapp.clients.BFFClient;
import com.draganddrop.webapp.dto.UserDashboardDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users/{userId}/userDashboards")
public class UserDashboardController {

    private final BFFClient BFFClient;

    public UserDashboardController(BFFClient BFFClient) {
        this.BFFClient = BFFClient;
    }

    @GetMapping
    public List<UserDashboardDto> findAll(@PathVariable("userId") UUID userId) {
        return BFFClient.findAllUserDashboard(userId);
    }

    @GetMapping("/{userDashboardId}")
    public UserDashboardDto find(@PathVariable("userId") UUID userId, @PathVariable("userDashboardId") UUID userDashBoardId) {
        return BFFClient.findUserDashboard(userId, userDashBoardId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("userId") UUID userId, @RequestBody UserDashboardDto dto) {
        BFFClient.createUserDashboard(userId, dto);
    }

    @DeleteMapping("/{userDashboardId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") UUID userId, @PathVariable("userDashboardId") UUID userDashBoardId) {
        BFFClient.deleteUserDashboard(userId, userDashBoardId);
    }

    @PutMapping("/{userDashboardId}")
    public void update(@PathVariable("userId") UUID userId, @PathVariable("userDashboardId") UUID userDashBoardId, @RequestBody UserDashboardDto dto) {
        BFFClient.updateUserDashboard(userId, userDashBoardId, dto);
    }
}
