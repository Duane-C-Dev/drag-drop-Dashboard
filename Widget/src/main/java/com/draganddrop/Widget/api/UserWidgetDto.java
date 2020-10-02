package com.draganddrop.Widget.api;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class UserWidgetDto {
    private UUID userWidgetId;
    private UUID userDashboardId;
    private UUID widgetId;
    private String positionX;
    private String positionY;
}
