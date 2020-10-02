package com.draganddrop.Widget.assemblers;

import com.draganddrop.Widget.api.UserWidgetDto;
import com.draganddrop.Widget.entities.UserWidget;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserWidgetAssembler {

    public List<UserWidgetDto> assemble (List<UserWidget> entities) {
        return entities.stream()
                .map(this::assemble)
                .collect(Collectors.toList());
    }

    public UserWidgetDto assemble (UserWidget entity) {
        return new UserWidgetDto()
                .setUserWidgetId(entity.getUserWidgetId())
                .setUserDashboardId(entity.getUserDashboardId())
                .setWidgetId(entity.getWidgetId())
                .setPositionX(entity.getPositionX())
                .setPositionY(entity.getPositionY());
    }

    public UserWidget disassemble (UserWidgetDto dto) {
        return disassembleInto(dto, UserWidget.newInstance());
    }

    //todo create informative error for attempting to update userDashboardId and widgetId (its ok on create)
    public UserWidget disassembleInto(UserWidgetDto dto, UserWidget entity) {
        if(entity.getUserDashboardId() == null) {
            entity.setUserDashboardId(dto.getUserDashboardId());
        }

        if(entity.getWidgetId() == null) {
            entity.setWidgetId(dto.getWidgetId());
        }

        entity.setPositionX(dto.getPositionX());
        entity.setPositionY(dto.getPositionY());
        return entity;
    }
}
