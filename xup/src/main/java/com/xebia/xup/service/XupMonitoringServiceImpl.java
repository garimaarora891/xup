package com.xebia.xup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xebia.xup.Helper;
import com.xebia.xup.model.MonitorRequest;
import com.xebia.xup.model.XupMonitoring;
import com.xebia.xup.repo.XupMonitoringRepo;
import com.xebia.xup.task.MonitoringTask;

@Service
public class XupMonitoringServiceImpl implements XupMonitoringService {

	@Autowired
	private XupMonitoringRepo repository;

	@Autowired
	private ScheduleTaskService taskService;

	@Override
	public XupMonitoring saveData(MonitorRequest request) {
		XupMonitoring existingSchedule = this.getMonitoring(request.getName());
		if (null != existingSchedule) {
			// logic to Update
			existingSchedule.setDuration(request.getInterval());
			existingSchedule.setFrequency(request.getFrequency().toString());
			XupMonitoring save = repository.save(existingSchedule);
			taskService.removeTaskFromScheduler(existingSchedule.getId());
			// taskService.addTaskToScheduler(id, task, cronExpression);
			return save;
		} else {
			// Logic for New Entry
			XupMonitoring data = new XupMonitoring();
			data.setName(request.getName());
			data.setUrl(request.getUrl());
			data.setFrequency(request.getFrequency().toString());
			data.setDuration(request.getInterval());
			data.setCount(0);
			System.out.println(data);
			XupMonitoring savedData = repository.save(data);
			MonitoringTask task = new MonitoringTask(repository, request.getUrl(), savedData.getId());
			taskService.addTaskToScheduler(savedData.getId(), task, Helper.createCronExpression(request));
			return savedData;
		}
	}

	@Override
	public XupMonitoring getMonitoring(String name) {
		// Logic to get Single Schedule for Monitoring
		Optional<XupMonitoring> dataByName = repository.findByName(name);
		if (dataByName.isPresent()) {
			System.out.println(dataByName.get());
			return dataByName.get();
		} else {
			return null;
		}
	}

	@Override
	public void deleteSchedule(Long id) {
		// Logic to delete Schedule
		repository.deleteById(id);
		taskService.removeTaskFromScheduler(id);
	}

	@Override
	public List<XupMonitoring> getAllMonitoring() {
		// Logic to fetch All Monitoring
		return repository.findAll();
	}

	@Override
	public XupMonitoring getMonitoring(Long id) {
		Optional<XupMonitoring> findById = repository.findById(id);
		if (findById.isPresent()) {
			return findById.get();
		} else {
			return null;
		}
	}

}
