package com.draganddrop.Widget.validators;

import com.draganddrop.Widget.api.UserDashboardDto;
import com.draganddrop.Widget.exceptions.ValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserDashboardValidator {
    static String USER_DASHBOARD_NAME_REQUIRED = "USER_DASHBOARD_NAME_REQUIRED";
    static String USER_DASHBOARD_NAME_LT_20 = "USER_DASHBOARD_NAME_LT_20";

    public void validateAndThrow(UserDashboardDto dto) {
        Map<String, String> errors = validate(dto);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public Map<String, String> validate(UserDashboardDto dto) {
        Map<String, String> errors = new LinkedHashMap<>();

        if(StringUtils.isBlank(dto.getUserDashboardName())){
            errors.put("userDashboardName", USER_DASHBOARD_NAME_REQUIRED);
        } else if(dto.getUserDashboardName().length() > 20) {
            errors.put("userDashboardName", USER_DASHBOARD_NAME_LT_20);
        }

        return errors;
    }
}
