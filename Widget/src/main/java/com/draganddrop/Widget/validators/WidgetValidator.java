package com.draganddrop.Widget.validators;

import com.draganddrop.Widget.api.WidgetDto;
import com.draganddrop.Widget.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class WidgetValidator {
    static String WIDGET_NAME_REQUIRED = "WIDGET_NAME_REQUIRED";
    static String WIDGET_TYPE_REQUIRED = "WIDGET_TYPE_REQUIRED";
    static String WIDGET_NAME_LT_20 = "WIDGET_NAME_LT_20";
    static String WIDGET_TYPE_LT_50 = "WIDGET_TYPE_LT_50";

    public void validateAndThrow(WidgetDto dto) {
        Map<String, String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public Map<String, String> validate(WidgetDto dto) {
        Map<String, String> errors = new LinkedHashMap<>();

        if(StringUtils.isBlank(dto.getWidgetName())){
            errors.put("widgetName", WIDGET_NAME_REQUIRED);
        } else if(dto.getWidgetName().length() > 20) {
            errors.put("widgetName", WIDGET_NAME_LT_20);
        }

        if(StringUtils.isBlank(dto.getWidgetType())) {
            errors.put("widgetType", WIDGET_TYPE_REQUIRED);
        } else if(dto.getWidgetName().length() > 20) {
            errors.put("widgetType", WIDGET_TYPE_LT_50);
        }

        return errors;
    }
}
