package com.github.brunolellis.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.brunolellis.task.TaskService;

@RestController
public class AsyncController {

	private static final Logger LOG = LoggerFactory.getLogger(AsyncController.class);
	private final TaskService taskService;

	@Autowired
	public AsyncController(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value = "/async")
	public void executeSlowTask() {
		LOG.info("Request received");
		taskService.executeAsync();
		LOG.info("Thread released and response sent. Task is still running in another thread...");
	}
}