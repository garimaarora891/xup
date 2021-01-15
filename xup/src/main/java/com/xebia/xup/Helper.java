package com.xebia.xup;

import org.springframework.stereotype.Component;

import com.xebia.xup.model.Frequency;
import com.xebia.xup.model.MonitorRequest;

@Component
public final class Helper {

	private Helper() {
		// Private Constructor for Utility Class
	}
	
	public static String createCronExpression(MonitorRequest request) {
		String cronExpression;
		if (request.getFrequency().equals(Frequency.MINUTES)) {
			cronExpression = "* 0/A * * * *";
		} else {
			cronExpression = "* * 0/A * * *";
		}
		cronExpression = cronExpression.replaceAll("A", request.getInterval().toString());
		return cronExpression;
	}
	
}
