package com.xebia.xup.service;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.ScheduledFuture;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

@Service
public class ScheduleTaskService {

	// Task Scheduler
	TaskScheduler scheduler;

	// A map for keeping scheduled tasks
	Map<Long, ScheduledFuture<?>> jobsMap = new HashMap<>();

	public ScheduleTaskService(TaskScheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void addTaskToScheduler(Long id, Runnable task, String cronExpression) {
		ScheduledFuture<?> scheduledTask = scheduler.schedule(task,
				new CronTrigger(cronExpression, TimeZone.getTimeZone(TimeZone.getDefault().getID())));
		jobsMap.put(id, scheduledTask);
	}

	// Remove scheduled task
	public void removeTaskFromScheduler(Long id) {
		ScheduledFuture<?> scheduledTask = jobsMap.get(id);
		if (scheduledTask != null) {
			scheduledTask.cancel(true);
			System.out.println("Removing Task");
			jobsMap.put(id, null);
		}
	}

	// A context refresh event listener
	@EventListener({ ContextRefreshedEvent.class })
	void contextRefreshedEvent() {
		// Get all tasks from DB and reschedule them in case of context restarted
	}
}
