package com.xebia.xup.model;

import org.springframework.stereotype.Component;

@Component
public class MonitorResponse {

	private Status status;
	private Double avgResponseTime;
	private Integer activityTime;

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Double getAvgResponseTime() {
		return avgResponseTime;
	}

	public void setAvgResponseTime(Double avgResponseTime) {
		this.avgResponseTime = avgResponseTime;
	}

	public Integer getActivityTime() {
		return activityTime;
	}

	public void setActivityTime(Integer activityTime) {
		this.activityTime = activityTime;
	}

}
