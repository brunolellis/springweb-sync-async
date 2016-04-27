package com.github.brunolellis.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.brunolellis.task.TaskService;

@RestController
public class SyncController {

	private static final Logger LOG = LoggerFactory.getLogger(SyncController.class);
	private final TaskService taskService;

	@Autowired
	public SyncController(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value = "/block")
	public String executeSlowTask() {
		LOG.info("Request received");
		String result = taskService.execute();
		LOG.info("Response sent");

		return result;
	}
}