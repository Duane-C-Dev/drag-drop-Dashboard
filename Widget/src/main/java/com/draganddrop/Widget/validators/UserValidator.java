package com.draganddrop.Widget.validators;


import com.draganddrop.Widget.api.UserDto;
import com.draganddrop.Widget.exceptions.ValidationException;
import com.draganddrop.Widget.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UserValidator {

	static String FIRST_NAME_REQUIRED = "FIRST_NAME_REQUIRED";
	static String LAST_NAME_REQUIRED = "LAST_NAME_REQUIRED";
	static String USERNAME_REQUIRED = "USERNAME_REQUIRED";
	static String FIRST_NAME_LT_20 = "FIRST_NAME_LT_20";
	static String LAST_NAME_LT_20 = "LAST_NAME_LT_20";
	static String USERNAME_LT_20 = "USERNAME_LT_20";
	static String USERNAME_ALREADY_EXISTS = "USERNAME_ALREADY_EXISTS";

	private UserRepository userRepository;

	UserValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public void validateAndThrow(UserDto dto) {
		Map<String, String> errors = validate(dto);
		if (!errors.isEmpty()) {
			throw new ValidationException(errors);
		}
	}


	public Map<String, String> validate(UserDto dto) {
		Map<String, String> errors = new LinkedHashMap<>();

		if (StringUtils.isBlank(dto.getUsername())) {
			errors.put("username", USERNAME_REQUIRED);
		} else if (dto.getUsername().length() > 20) {
			errors.put("username", USERNAME_LT_20);
		} else if ((isNewUser(dto) || isUsernameEdited(dto))
				&& userRepository.existsUserByUsername(dto.getUsername())) {
			errors.put("username", USERNAME_ALREADY_EXISTS);
		}

		if (StringUtils.isBlank(dto.getFirstName())) {
			errors.put("firstName", FIRST_NAME_REQUIRED);
		} else if (dto.getFirstName().length() > 20) {
			errors.put("firstName", FIRST_NAME_LT_20);
		}

		if (StringUtils.isBlank(dto.getLastName())) {
			errors.put("lastName", LAST_NAME_REQUIRED);
		} else if (dto.getLastName().length() > 20) {
			errors.put("lastName", LAST_NAME_LT_20);
		}
		
		return errors;
	}

	private boolean isNewUser(UserDto dto) {
		return dto.getUserId() == null;
	}

	private boolean isUsernameEdited(UserDto dto) {
		if (dto.getUserId() == null) {
			return false;
		}
		return userRepository.findById(dto.getUserId())
				.map(databaseUser -> !databaseUser.getUsername().equals(dto.getUsername()))
				.orElse(false);
	}
}
