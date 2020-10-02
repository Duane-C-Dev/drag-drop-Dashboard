package com.draganddrop.Widget.assemblers;

import com.draganddrop.Widget.api.WidgetDto;
import com.draganddrop.Widget.entities.Widget;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WidgetAssembler {

    public List<WidgetDto> assemble (List<Widget> entities) {
        return entities.stream()
                .map(this::assemble)
                .collect(Collectors.toList());
    }

    public WidgetDto assemble (Widget entity) {
        return new WidgetDto()
                .setWidgetId(entity.getWidgetId())
                .setWidgetName(entity.getWidgetName())
                .setWidgetType(entity.getWidgetType());
    }

    public Widget disassemble (WidgetDto dto) {
        return disassembleInto(dto, Widget.newInstance());
    }

    public Widget disassembleInto (WidgetDto dto, Widget entity) {
        return entity
                .setWidgetName(dto.getWidgetName())
                .setWidgetType(dto.getWidgetType());
    }
}
