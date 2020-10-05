package com.draganddrop.Widget.validators;

import com.draganddrop.Widget.api.UserWidgetDto;
import com.draganddrop.Widget.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserWidgetValidator {

    static String POSTION_X_REQUIRED = "POSTION_X_REQUIRED";
    static String POSTION_Y_REQUIRED = "POSTION_Y_REQUIRED";
    static String POSTION_X_LT_20 = "POSTION_X_LT_20";
    static String POSTION_Y_LT_20 = "POSTION_Y_LT_20";

    public void validateAndThrow(UserWidgetDto dto) {
        Map<String, String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public Map<String, String> validate(UserWidgetDto dto) {
        Map<String, String> errors = new LinkedHashMap<>();

        if(StringUtils.isBlank(dto.getPositionX())){
            errors.put("postionX", POSTION_X_REQUIRED);
        } else if(dto.getPositionX().length() > 20) {
            errors.put("positionX", POSTION_X_LT_20);
        }

        if(StringUtils.isBlank(dto.getPositionY())){
            errors.put("positionY", POSTION_Y_REQUIRED);
        } else if(dto.getPositionY().length() > 20) {
            errors.put("positionY", POSTION_Y_LT_20);
        }

        return errors;
    }
}
