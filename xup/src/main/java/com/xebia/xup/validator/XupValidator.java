package com.xebia.xup.validator;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.xebia.xup.model.Frequency;
import com.xebia.xup.model.MonitorRequest;

@Component
public class XupValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return MonitorRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (Optional.ofNullable(target).isPresent()) {
			MonitorRequest request = (MonitorRequest) target;
			if (request.getFrequency().equals(Frequency.HOURS)
					&& (request.getInterval() <= 0 || request.getInterval() > 24)) {
				errors.reject("1001", "Monitoring for given Interval is not permissible");
			} else if (request.getFrequency().equals(Frequency.MINUTES)
					&& (request.getInterval() < 1 || request.getInterval() > 59)) {
				errors.reject("1001", "Monitoring for given Interval is not permissible");
			}
		}

	}

}
