
package com.xebia.xup.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.xebia.xup.service.XupMonitoringService;
import com.xebia.xup.validator.XupValidator;

@WebMvcTest(XupController.class)
public class XupControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private XupMonitoringService service;
	
	@MockBean
	private XupValidator validator;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get("/getAllSchedules")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void shouldRegisterStatusRequest() throws Exception {
		this.mockMvc
				.perform(put("/registerMonitoring").contentType(MediaType.APPLICATION_JSON)
						.content("{\r\n" + "	\"url\":\"https://www.facebook.com/\",\r\n" + "	\"interval\":\"1\",\r\n"
								+ "	\"frequency\":\"MINUTES\",\r\n" + "	\"name\":\"sid\"\r\n" + "}"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void shouldFailRegisterStatusRequestForWrongFrequency() throws Exception {
		this.mockMvc
		.perform(put("/registerMonitoring").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "	\"url\":\"https://www.facebook.com/\",\r\n" + "	\"interval\":\"1\",\r\n"
						+ "	\"frequency\":\"DAY\",\r\n" + "	\"name\":\"sid\"\r\n" + "}"))
		.andExpect(status().isBadRequest());
	}
	
	
}
