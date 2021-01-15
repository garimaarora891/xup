package com.xebia.xup.service;

import java.util.List;

import com.xebia.xup.model.MonitorRequest;
import com.xebia.xup.model.XupMonitoring;

public interface XupMonitoringService {

	XupMonitoring saveData(MonitorRequest request);
	
	XupMonitoring getMonitoring(String name);

	void deleteSchedule(Long id);

	List<XupMonitoring> getAllMonitoring();

	XupMonitoring getMonitoring(Long id);

}
