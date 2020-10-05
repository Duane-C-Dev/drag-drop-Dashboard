package com.draganddrop.Widget.services;

import com.draganddrop.Widget.api.UserDashboardDto;
import com.draganddrop.Widget.assemblers.UserDashboardAssembler;
import com.draganddrop.Widget.entities.UserDashboard;
import com.draganddrop.Widget.exceptions.NotFoundException;
import com.draganddrop.Widget.repositories.UserDashboardRepository;
import com.draganddrop.Widget.validators.UserDashboardValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserDashboardService {

    private final UserDashboardAssembler userDashboardAssembler;
    private final UserDashboardRepository userDashboardRepository;
    private final UserDashboardValidator userDashboardValidator;

    public UserDashboardService(UserDashboardAssembler userDashboardAssembler, UserDashboardRepository userDashboardRepository, UserDashboardValidator userDashboardValidator) {
        this.userDashboardAssembler = userDashboardAssembler;
        this.userDashboardRepository = userDashboardRepository;
        this.userDashboardValidator = userDashboardValidator;
    }

    public List<UserDashboardDto> findAll(UUID userId) { return userDashboardAssembler.assemble(userDashboardRepository.findAllByUserId(userId)); }

    public UserDashboardDto find(UUID userDashboardId) {
        return userDashboardRepository.findById(userDashboardId)
                .map(userDashboardAssembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public void create(UserDashboardDto dto) {
        userDashboardValidator.validateAndThrow(dto);
        UserDashboard entity = userDashboardAssembler.disassemble(dto);
        userDashboardRepository.save(entity);
    }

    public void delete(UUID userDashboardId) {
        boolean exists = userDashboardRepository.existsById(userDashboardId);

        if(exists) {
            userDashboardRepository.deleteById(userDashboardId);
        } else {
            throw new NotFoundException();
        }
    }

    public void update(UUID userDashboardId, UserDashboardDto dto) {
        userDashboardValidator.validateAndThrow(dto);
        userDashboardRepository.findById(userDashboardId)
            .map(entity -> userDashboardAssembler.disassembleInto(dto, entity))
            .map(userDashboardRepository::save)
            .orElseThrow(NotFoundException::new);
    }

}
