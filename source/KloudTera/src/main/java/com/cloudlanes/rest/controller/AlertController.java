package com.cloudlanes.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cloudlanes.db.entities.Alert;
import com.cloudlanes.db.entities.AlertRecommendation;
import com.cloudlanes.rest.outobject.Status;
import com.cloudlanes.service.AlertRecommendationService;
import com.cloudlanes.service.AlertService;

@RestController
@RequestMapping(value = "/alertservices")
public class AlertController {

	@Autowired
	private AlertService alertService;
	@Autowired
	private AlertRecommendationService recommendationService;

	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status add(@RequestBody Alert a) throws Exception {
		if (alertService.add(a)) {
			return new Status(true, "OK");
		} else {
			return new Status(false, "Not OK");
		}

	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status update(@RequestBody Alert a) throws Exception {

		if (alertService.update(a)) {
			return new Status(true, "OK");
		} else {
			return new Status(false, "Not OK");
		}

	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Status delete(@RequestBody Alert a) throws Exception {

		if (alertService.delete(a)) {
			return new Status(true, "OK");
		} else {
			return new Status(false, "Not OK");
		}

	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Alert> list() throws Exception {
		return alertService.listAlert();
	}

	@RequestMapping(value = "/recommendation", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public AlertRecommendation getRecommendation(Double code) throws Exception {
		return recommendationService.getRecommendation(code);
	}
}
