package com.draganddrop.Widget.services;

import com.draganddrop.Widget.api.WidgetDto;
import com.draganddrop.Widget.assemblers.WidgetAssembler;
import com.draganddrop.Widget.entities.Widget;
import com.draganddrop.Widget.exceptions.NotFoundException;
import com.draganddrop.Widget.repositories.WidgetRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public class WidgetService {

    private final WidgetRepository widgetRepository;
    private final WidgetAssembler widgetAssembler;

    WidgetService(WidgetRepository widgetRepository, WidgetAssembler widgetAssembler) {
        this.widgetRepository = widgetRepository;
        this.widgetAssembler = widgetAssembler;
    }

    public List<WidgetDto> findAll() {
        return widgetAssembler.assemble(widgetRepository.findAll());
    }

    public WidgetDto find(UUID widgetId) {
        return widgetRepository.findById(widgetId)
                .map(widgetAssembler::assemble)
                .orElseThrow(NotFoundException::new);
    }

    public void create(WidgetDto dto) {
        Widget entity = widgetAssembler.disassemble(dto);
        widgetRepository.save(entity);
    }

    public void delete(UUID widgetId) {
        boolean exists = widgetRepository.existsById(widgetId);

        if(exists) {
            widgetRepository.deleteById(widgetId);
        } else {
            throw new NotFoundException();
        }
    }

    public void update(UUID widgetId, WidgetDto dto) {
        widgetRepository.findById(widgetId)
            .map(entity -> widgetAssembler.disassembleInto(dto, entity))
            .map(widgetRepository::save)
            .orElseThrow(NotFoundException::new);
    }

}
