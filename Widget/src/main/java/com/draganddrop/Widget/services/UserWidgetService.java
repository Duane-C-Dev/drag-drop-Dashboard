package com.draganddrop.Widget.services;

import com.draganddrop.Widget.api.UserWidgetDto;
import com.draganddrop.Widget.assemblers.UserWidgetAssembler;
import com.draganddrop.Widget.entities.UserWidget;
import com.draganddrop.Widget.exceptions.NotFoundException;
import com.draganddrop.Widget.repositories.UserWidgetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserWidgetService {

    private final UserWidgetAssembler userWidgetAssembler;
    private final UserWidgetRepository userWidgetRepository;

    public UserWidgetService(UserWidgetAssembler userWidgetAssembler, UserWidgetRepository userWidgetRepository) {
        this.userWidgetAssembler = userWidgetAssembler;
        this.userWidgetRepository = userWidgetRepository;
    }

    public List<UserWidgetDto> findAll(UUID userDashboardId) {
        return userWidgetAssembler.assemble(userWidgetRepository.findAllByUserDashboardId(userDashboardId));
    }

    public UserWidgetDto find(UUID userWidgetId) {
        return userWidgetRepository.findById(userWidgetId)
                .map(userWidgetAssembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public void create(UserWidgetDto dto) {
        UserWidget entity = userWidgetAssembler.disassemble(dto);
        userWidgetRepository.save(entity);
    }

    public void delete(UUID userWidgetId) {
        boolean exists = userWidgetRepository.existsById(userWidgetId);

        if(exists) {
            userWidgetRepository.deleteById(userWidgetId);
        } else {
            throw new NotFoundException();
        }
    }

    public void update(UUID userWidgetDto, UserWidgetDto dto) {
        userWidgetRepository.findById(userWidgetDto)
                .map(entity -> userWidgetAssembler.disassembleInto(dto, entity))
                .map(userWidgetRepository::save)
                .orElseThrow(NotFoundException::new);
    }
}
