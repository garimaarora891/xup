package com.xebia.xup.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.xebia.xup.model.Frequency;
import com.xebia.xup.model.MonitorRequest;

@RunWith(SpringRunner.class)
public class XupValidatorTest {

	private XupValidator validator = new XupValidator();

	@Test
	public void shouldBeSuccessfulForValidHoursInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.HOURS);
		req.setInterval(1);

		Errors errors = new BeanPropertyBindingResult(req, "validRequest");
		validator.validate(req, errors);

		assertFalse(errors.hasErrors());

	}

	@Test
	public void shouldBeSuccessfulForValid24HoursInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.HOURS);
		req.setInterval(24);

		Errors errors = new BeanPropertyBindingResult(req, "validRequest");
		validator.validate(req, errors);

		assertFalse(errors.hasErrors());

	}

	@Test
	public void shouldBeSuccessfulForValid59MinutesInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.MINUTES);
		req.setInterval(59);

		Errors errors = new BeanPropertyBindingResult(req, "validRequest");
		validator.validate(req, errors);

		assertFalse(errors.hasErrors());

	}

	@Test
	public void shouldBeSuccessfulForValidMinutesInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.MINUTES);
		req.setInterval(1);

		Errors errors = new BeanPropertyBindingResult(req, "validRequest");
		validator.validate(req, errors);

		assertFalse(errors.hasErrors());

	}

	@Test
	public void shouldBeFailureForInValidHoursInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.HOURS);
		req.setInterval(100);

		Errors errors = new BeanPropertyBindingResult(req, "invalidRequest");
		validator.validate(req, errors);

		assertTrue(errors.hasErrors());

	}

	@Test
	public void shouldBeFailureForInValidMinutesInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.MINUTES);
		req.setInterval(100);

		Errors errors = new BeanPropertyBindingResult(req, "invalidRequest");
		validator.validate(req, errors);

		assertTrue(errors.hasErrors());

	}

	@Test
	public void shouldBeFailureForInValid60MinutesInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.MINUTES);
		req.setInterval(60);

		Errors errors = new BeanPropertyBindingResult(req, "invalidRequest");
		validator.validate(req, errors);

		assertTrue(errors.hasErrors());

	}

	@Test
	public void shouldBeFailureForInValidNegativeMinutesInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.MINUTES);
		req.setInterval(-1);

		Errors errors = new BeanPropertyBindingResult(req, "invalidRequest");
		validator.validate(req, errors);

		assertTrue(errors.hasErrors());

	}

	@Test
	public void shouldBeFailureForInValidNegativeHoursInput() {
		MonitorRequest req = new MonitorRequest();
		req.setFrequency(Frequency.HOURS);
		req.setInterval(-1);

		Errors errors = new BeanPropertyBindingResult(req, "invalidRequest");
		validator.validate(req, errors);

		assertTrue(errors.hasErrors());

	}
}
