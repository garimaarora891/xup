package com.xebia.xup.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.CollectionUtils;

import com.xebia.xup.model.Frequency;
import com.xebia.xup.model.MonitorRequest;
import com.xebia.xup.model.XupMonitoring;
import com.xebia.xup.repo.XupMonitoringRepo;

@SpringBootTest
public class XupMonitoringServiceImplTest {

	@Autowired
	private XupMonitoringServiceImpl service;

	@MockBean
	private XupMonitoringRepo repository;

	@Test
	@DisplayName("Test findByName Success")
	public void whenValidName_thenXupMonitoringShouldBeFound() {
		XupMonitoring xupMonitoring = createMonitoringObject("Test", Frequency.HOURS, 1, 1L);
		doReturn(Optional.of(xupMonitoring)).when(repository).findByName("Test");

		String name = "Test";
		XupMonitoring found = service.getMonitoring(name);

		// Assert the response
		Assertions.assertTrue(null != found, "XupMonitoring was not found");
		Assertions.assertSame(found, xupMonitoring, "The XupMonitoring returned was not the same as the mock");
	}

	@Test
	@DisplayName("Test findById Success")
	public void whenValidId_thenXupMonitoringShouldBeFound() {
		XupMonitoring xupMonitoring = createMonitoringObject("FoundById", Frequency.HOURS, 1, 1L);
		doReturn(Optional.of(xupMonitoring)).when(repository).findById(1L);
		XupMonitoring found = service.getMonitoring(1L);

		// Assert the response
		Assertions.assertTrue(null != found, "XupMonitoring was not found");
		Assertions.assertSame(found, xupMonitoring, "The XupMonitoring returned was not the same as the mock");
	}

	@Test
	@DisplayName("Test findAll Success")
	public void shpuldReturnSuccessForFoundAll() {
		XupMonitoring xupMonitoring = createMonitoringObject("FoundById", Frequency.HOURS, 1, 1L);
		XupMonitoring xupMonitoring1 = createMonitoringObject("FoundById1", Frequency.HOURS, 1, 1L);
		doReturn(Arrays.asList(xupMonitoring, xupMonitoring1)).when(repository).findAll();
		List<XupMonitoring> found = service.getAllMonitoring();
		// Assert the response
		Assertions.assertFalse(CollectionUtils.isEmpty(found), "XupMonitoring was not found");
		Assertions.assertSame(found.get(0), xupMonitoring, "The XupMonitoring returned was not the same as the mock");
		Assertions.assertSame(found.get(1), xupMonitoring1, "The XupMonitoring1 returned was not the same as the mock");
	}

	@Test
	@DisplayName("Test save widget")
	void testSave() {
		// Setup our mock repository
		XupMonitoring xupMonitoring = createMonitoringObject("Test", Frequency.HOURS, 1, 1L);
		doReturn(xupMonitoring).when(repository).save(Mockito.any());
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.HOURS);
		req.setInterval(1);
		req.setName("Test");
		req.setUrl("https://www.google.com");

		// Execute the service call
		XupMonitoring returnedWidget = service.saveData(req);

		// Assert the response
		Assertions.assertNotNull(returnedWidget, "The saved widget should not be null");
		Assertions.assertEquals("Test", returnedWidget.getName());
	}

	@Test
	@DisplayName("Test save update")
	void testUpdate() {
		// Setup our mock repository
		XupMonitoring xupMonitoring = createMonitoringObject("Test", Frequency.HOURS, 1, 1L, "https://www.google.com");
		XupMonitoring xupMonitoring1 = createMonitoringObject("Test1", Frequency.HOURS, 1, 1L,
				"https://www.facebook.com");
		doReturn(Optional.of(xupMonitoring)).when(repository).findByName(Mockito.any());
		doReturn(xupMonitoring1).when(repository).save(Mockito.any());
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.HOURS);
		req.setInterval(1);
		req.setName("Test");
		req.setUrl("https://www.facebook.com");

		// Execute the service call
		XupMonitoring returnedWidget = service.saveData(req);

		// Assert the response
		Assertions.assertNotNull(returnedWidget, "The saved widget should not be null");
		Assertions.assertEquals("https://www.facebook.com", returnedWidget.getUrl(), "Url is Changed");
	}

	private XupMonitoring createMonitoringObject(String name, Frequency frequency, int duration, long id, String url) {
		XupMonitoring createMonitoringObject = this.createMonitoringObject(name, frequency, duration, id);
		createMonitoringObject.setUrl(url);
		return createMonitoringObject;
	}

	@Test
	@DisplayName("Test save widget")
	void testSaveMinutes() {
		// Setup our mock repository
		XupMonitoring xupMonitoring = createMonitoringObject("Test", Frequency.MINUTES, 1, 1L);
		doReturn(xupMonitoring).when(repository).save(Mockito.any());
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.MINUTES);
		req.setInterval(1);
		req.setName("Test");
		req.setUrl("https://www.google.com");

		// Execute the service call
		XupMonitoring returnedWidget = service.saveData(req);

		// Assert the response
		Assertions.assertNotNull(returnedWidget, "The saved widget should not be null");
		Assertions.assertEquals("Test", returnedWidget.getName());
	}

	@Test
	@DisplayName("Test delete")
	void testDelete() {
		// Setup our mock repository
		doNothing().when(repository).deleteById(1L);

		// Execute the service call
		service.deleteSchedule(1L);

		// Assert the response
		verify(repository, times(1)).deleteById(1L);
	}

	private XupMonitoring createMonitoringObject(String name, Frequency frequency, Integer duration, Long id) {
		XupMonitoring xupMonitoring = new XupMonitoring();
		xupMonitoring.setName(name);
		xupMonitoring.setFrequency(frequency.toString());
		xupMonitoring.setDuration(duration);
		xupMonitoring.setId(id);
		return xupMonitoring;
	}

}
