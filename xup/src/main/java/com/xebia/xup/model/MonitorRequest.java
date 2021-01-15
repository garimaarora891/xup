package com.xebia.xup.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import com.sun.istack.NotNull;

@Component
@Validated
public class MonitorRequest {

	@NotBlank
	private String name;

	@NotBlank
	private String url;

	private Integer interval;

	private Frequency frequency;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getInterval() {
		return interval;
	}

	public void setInterval(Integer interval) {
		this.interval = interval;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "MonitorRequest [name=" + name + ", url=" + url + ", interval=" + interval + ", frequency=" + frequency
				+ "]";
	}

}
