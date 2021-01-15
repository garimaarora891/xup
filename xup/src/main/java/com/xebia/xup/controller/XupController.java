package com.xebia.xup.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xebia.xup.model.MonitorRequest;
import com.xebia.xup.model.XupMonitoring;
import com.xebia.xup.service.XupMonitoringService;
import com.xebia.xup.validator.XupValidator;

@RestController
@Validated
public class XupController {

	@Autowired
	private XupMonitoringService service;
	
	@Autowired
	private XupValidator validator;

	@PutMapping("/registerMonitoring")
	public ResponseEntity<String> register(@Valid @RequestBody MonitorRequest request, BindingResult bindingResult) {
		validator.validate(request, bindingResult);
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Invalid Inputs",HttpStatus.BAD_REQUEST);
		}
		System.out.println("Yipee I am here");
		System.out.println(request);
		service.saveData(request);
		// Put Validation on Requests
		// put the request in DB to monitor data
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@GetMapping("/fetchScheduleByName")
	public XupMonitoring getMonitoringByName(@RequestParam String name) {
		XupMonitoring monitoring = service.getMonitoring(name);
		return monitoring;

	}

	@GetMapping("/getAllSchedules")
	public List<XupMonitoring> getAllMonitorig() {
		return service.getAllMonitoring();

	}

	@DeleteMapping("/deleteSchedule")
	public void deleteById(@RequestParam Long id) {
		service.deleteSchedule(id);
	}

	@GetMapping("/getState")
	public ResponseEntity<String> getState(@RequestParam Long id) {
		XupMonitoring monitoring = service.getMonitoring(id);
		if (null == monitoring) {
			return new ResponseEntity<String>("State not found", HttpStatus.OK);
		}
		return buildResponse(monitoring);
	}

	@GetMapping("/getStateByName")
	public ResponseEntity<String> getState(@RequestParam String name) {
		XupMonitoring monitoring = service.getMonitoring(name);
		if (null == monitoring) {
			return new ResponseEntity<String>("State not found", HttpStatus.OK);
		}
		return buildResponse(monitoring);
	}

	private ResponseEntity<String> buildResponse(XupMonitoring monitoring) {
		StringBuilder builder = new StringBuilder();
		builder.append("The Site " + monitoring.getUrl() + " is " + monitoring.getStatus() + " from last "
				+ monitoring.getCount() + " " + monitoring.getFrequency());
		return new ResponseEntity<String>(builder.toString(), HttpStatus.OK);
	}

}
