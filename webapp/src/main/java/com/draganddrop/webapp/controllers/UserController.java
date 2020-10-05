package com.draganddrop.webapp.controllers;


import com.draganddrop.webapp.clients.BFFClient;
import com.draganddrop.webapp.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final BFFClient BFFClient;

    public UserController(BFFClient BFFClient) {
        this.BFFClient = BFFClient;
    }

    @GetMapping
    public List<UserDto> findAll() {
        return BFFClient.findAllUser();
    }

    @GetMapping("/{userId}")
    public UserDto find(@PathVariable("userId") UUID userId) {
        return BFFClient.findUser(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody UserDto dto) {
        BFFClient.createUser(dto);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("userId") UUID userId) {
        BFFClient.deleteUser(userId);
    }

    @PutMapping("/{userId}")
    public void update(@PathVariable("userId") UUID userId, @RequestBody UserDto dto) {
        BFFClient.updateUser(userId, dto);
    }
}
