package com.xebia.xup.task;

import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.xebia.xup.model.XupMonitoring;
import com.xebia.xup.repo.XupMonitoringRepo;

@Component
@Scope("prototype")
public class MonitoringTask implements Runnable {

	private static final String UP_AND_RUNNING = "UP and Running";

	private static final String DOWN_AND_NOT_RUNNING = "Down and Not Running";

	private XupMonitoringRepo repo;

	private RestTemplate restTemplate = new RestTemplate();

	private String url;

	private Long id;

	public MonitoringTask(XupMonitoringRepo repo, String url, Long id) {
		super();
		this.repo = repo;
		this.url = url;
		this.id = id;
	}

	@Override
	public void run() {
		XupMonitoring obj = null;
		try {
			System.out.println("I am Exexuting Task Now Buddy!");
			ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);
			if (result.getStatusCode() == HttpStatus.OK) {
				obj = saveSuccessState(obj);
			} else {
				obj = saveFailedState(obj);
			}
		} catch (Exception e) {
			obj = saveFailedState(obj);
		} finally {
			repo.save(obj);
		}

	}

	private XupMonitoring saveSuccessState(XupMonitoring obj) {
		System.out.println("It is Successful, Yo! for " + this.id);
		Optional<XupMonitoring> findById = repo.findById(id);
		if (findById.isPresent()) {
			obj = findById.get();
			if (null == obj.getStatus() || obj.getStatus().equals(DOWN_AND_NOT_RUNNING)) {
				obj.setCount(1);
				obj.setStatus(UP_AND_RUNNING);
			} else {
				obj.setCount(obj.getCount() + 1);
				obj.setStatus(UP_AND_RUNNING);
			}
		}
		return obj;
	}

	private XupMonitoring saveFailedState(XupMonitoring obj) {
		System.out.println("Oh no! It is not Successful for " + this.id);
		Optional<XupMonitoring> findById = repo.findById(id);
		if (findById.isPresent()) {
			obj = findById.get();
			if (null == obj.getStatus() || obj.getStatus().equals(DOWN_AND_NOT_RUNNING)) {
				obj.setCount(obj.getCount() + 1);
				obj.setStatus(DOWN_AND_NOT_RUNNING);
			} else {
				obj.setCount(1);
				obj.setStatus(DOWN_AND_NOT_RUNNING);
			}
		}
		return obj;
	}

	public String getUrl() {
		return url;
	}

	public Long getId() {
		return id;
	}

}
