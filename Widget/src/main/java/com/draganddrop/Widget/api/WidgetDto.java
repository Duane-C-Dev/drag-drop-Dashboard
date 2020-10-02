package com.draganddrop.Widget.api;

import lombok.Data;
import lombok.experimental.Accessors;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class WidgetDto {
    private UUID widgetId;
    private String widgetName;
    private String widgetType;
}
