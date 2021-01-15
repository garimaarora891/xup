package com.xebia.xup.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xebia.xup.model.XupMonitoring;

@Repository
public interface XupMonitoringRepo extends JpaRepository<XupMonitoring, Long> {
	
	Optional<XupMonitoring> findByName(String name);

}
