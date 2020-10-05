package com.draganddrop.Widget.controllers;

import com.draganddrop.Widget.api.UserDto;
import com.draganddrop.Widget.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{userId}")
    public UserDto find(@PathVariable("userId") UUID userId) {
        return userService.find(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto dto) {
        userService.create(dto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") UUID userId) {
        userService.delete(userId);
    }

    @PutMapping("/{userId}")
    public void update(@PathVariable("userId") UUID userId, @RequestBody UserDto dto) {
        userService.update(userId, dto);
    }
}
