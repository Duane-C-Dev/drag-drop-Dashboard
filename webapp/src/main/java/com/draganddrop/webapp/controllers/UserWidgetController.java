package com.draganddrop.webapp.controllers;


import com.draganddrop.webapp.clients.BFFClient;
import com.draganddrop.webapp.dto.UserWidgetDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users/{userId}/userDashboards/{userDashboardId}/userWidgets")
public class UserWidgetController {

    private final BFFClient BFFClient;

    public UserWidgetController(BFFClient BFFClient) {
        this.BFFClient = BFFClient;
    }

    @GetMapping
    public List<UserWidgetDto> findAll(@PathVariable("userId") UUID userId, @PathVariable("userDashboardId") UUID userDashboardId) {
        return BFFClient.findAllUserWidget(userId, userDashboardId);
    }

    @GetMapping("/{userWidgetId}")
    public UserWidgetDto find(@PathVariable("userId") UUID userId, @PathVariable("userDashboardId") UUID userDashboardId, @PathVariable("userWidgetId") UUID userWidgetId){
        return BFFClient.findUserWidget(userId, userDashboardId, userWidgetId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@PathVariable("userId") UUID userId, @PathVariable("userDashboardId") UUID userDashboardId, @RequestBody UserWidgetDto dto) {
        BFFClient.createUserWidget(userId, userDashboardId, dto);
    }

    @DeleteMapping("/{userWidgetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") UUID userId, @PathVariable("userDashboardId") UUID userDashboardId, @PathVariable("userWidgetId") UUID userWidgetId) {
        BFFClient.deleteUserWidget(userId, userDashboardId, userWidgetId);
    }

    @PutMapping("/{userWidgetId}")
    public void update(@PathVariable("userId") UUID userId, @PathVariable("userDashboardId") UUID userDashboardId, @PathVariable("userWidgetId") UUID userWidgetId, @RequestBody UserWidgetDto dto) {
        BFFClient.updateUserWidget(userId, userDashboardId, userWidgetId, dto);
    }
}
