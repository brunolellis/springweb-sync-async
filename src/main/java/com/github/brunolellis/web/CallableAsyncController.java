package com.github.brunolellis.web;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.brunolellis.task.TaskService;

@RestController
public class CallableAsyncController {

	private static final Logger LOG = LoggerFactory.getLogger(CallableAsyncController.class);
	private final TaskService taskService;

	@Autowired
	public CallableAsyncController(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value = "/callable")
	public Callable<String> executeSlowTask() {
		LOG.info("Request received");
		Callable<String> callable = taskService::execute;
		LOG.info("Thread released but client connection is waiting the response (another thread)...");

		return callable;
	}
}