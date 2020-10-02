package com.draganddrop.Widget.assemblers;

import com.draganddrop.Widget.api.UserDashboardDto;
import com.draganddrop.Widget.entities.UserDashboard;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDashboardAssembler {

    public List<UserDashboardDto> assemble(List<UserDashboard> entities){
        return entities.stream()
                .map(this::assemble)
                .collect(Collectors.toList());
    }

    public UserDashboardDto assemble(UserDashboard entity) {
        return new UserDashboardDto()
                .setUserDashboardId(entity.getUserDashboardId())
                .setUserDashboardName(entity.getUserDashboardName())
                .setUserId(entity.getUserId());
    }

    public UserDashboard disassemble(UserDashboardDto dto) {
        return disassembleInto(dto, UserDashboard.newInstance());
    }

    public UserDashboard disassembleInto(UserDashboardDto dto, UserDashboard entity) {
        return entity
                .setUserDashboardName(dto.getUserDashboardName());
    }
}
