package com.xebia.xup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "xup_monitoring")
public class XupMonitoring {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long Id;

	@Column(name = "name", unique=true)
	private String name;

	@Column(name = "url", unique=true)
	private String url;

	@Column(name = "frequency")
	private String frequency;

	@Column(name = "duration")
	private Integer duration;

	@Column(name = "status")
	private String status;

	@Column(name = "count")
	private Integer count;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "XupMonitoring [Id=" + Id + ", name=" + name + ", url=" + url + ", frequency=" + frequency
				+ ", duration=" + duration + ", status=" + status + ", count=" + count + "]";
	}

}
