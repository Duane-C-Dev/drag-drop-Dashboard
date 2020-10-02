package com.draganddrop.Widget.api;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class UserDashboardDto {
    private UUID userDashboardId;
    private String userDashboardName;
    private UUID userId;
}
