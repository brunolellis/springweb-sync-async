package com.github.brunolellis.web;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import com.github.brunolellis.task.TaskService;

@RestController
public class DeferredAsyncController {

	private static final Logger LOG = LoggerFactory.getLogger(DeferredAsyncController.class);
	private final TaskService taskService;

	@Autowired
	public DeferredAsyncController(TaskService taskService) {
		this.taskService = taskService;
	}

	@RequestMapping(value = "/deferred")
	public DeferredResult<String> executeSlowTask() {
		LOG.info("Request received");

		DeferredResult<String> deferredResult = new DeferredResult<>();
		CompletableFuture.supplyAsync(taskService::execute)
				.whenCompleteAsync((result, throwable) -> deferredResult.setResult(result));

		LOG.info("Thread released but client connection is waiting the response (another thread)...");

		return deferredResult;
	}
}