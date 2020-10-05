package com.draganddrop.Widget.services;

import com.draganddrop.Widget.api.UserDto;
import com.draganddrop.Widget.assemblers.UserAssembler;
import com.draganddrop.Widget.entities.User;
import com.draganddrop.Widget.exceptions.NotFoundException;
import com.draganddrop.Widget.repositories.UserRepository;
import com.draganddrop.Widget.validators.UserValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserAssembler userAssembler;
    private final UserValidator userValidator;

    public UserService(UserRepository userRepository, UserAssembler userAssembler, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userAssembler = userAssembler;
        this.userValidator = userValidator;
    }

    public List<UserDto> findAll() { return userAssembler.assemble(userRepository.findAll()); }

    public UserDto find(UUID userId) {
        return userRepository.findById(userId)
                .map(userAssembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public void create(UserDto dto) {
        userValidator.validateAndThrow(dto);
        User entity = userAssembler.disassemble(dto);
        userRepository.save(entity);
    }

    public void delete(UUID userId) {
        boolean exists = userRepository.existsById(userId);

        if(exists) {
            userRepository.deleteById(userId);
        } else {
            throw new NotFoundException();
        }
    }

    public void update(UUID userId, UserDto dto) {
        userValidator.validateAndThrow(dto);
        userRepository.findById(userId)
           .map(entity -> userAssembler.disassembleInto(dto, entity))
           .map(userRepository::save)
           .orElseThrow(NotFoundException::new);
    }
}
