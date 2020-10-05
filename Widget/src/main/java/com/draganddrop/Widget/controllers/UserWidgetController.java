package com.draganddrop.Widget.controllers;

import com.draganddrop.Widget.api.UserWidgetDto;
import com.draganddrop.Widget.services.UserWidgetService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users/{userId}/userDashboards/{userDashboardId}/userWidgets")
public class UserWidgetController {

    UserWidgetService userwidgetService;

    public UserWidgetController(UserWidgetService userWidgetService) {
        this.userwidgetService = userWidgetService;
    }

    @GetMapping
    public List<UserWidgetDto> findAll(@PathVariable("userDashboardId") UUID userDashboardId) {
        return userwidgetService.findAll(userDashboardId);
    }

    @GetMapping("/{userWidgetId}")
    public UserWidgetDto find(@PathVariable("userWidgetId") UUID userWidgetId){
        return userwidgetService.find(userWidgetId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserWidgetDto dto) {
        userwidgetService.create(dto);
    }

    @DeleteMapping("/{userWidgetId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userWidgetId") UUID userWidgetId) {
        userwidgetService.delete(userWidgetId);
    }

    @PutMapping("/{userWidgetId}")
    public void update(@PathVariable("userWidgetId") UUID userWidgetId, @RequestBody UserWidgetDto dto) {
        userwidgetService.update(userWidgetId, dto);
    }
}
