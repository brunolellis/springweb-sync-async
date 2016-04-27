package com.github.brunolellis.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
	private static final Logger LOG = LoggerFactory.getLogger(TaskServiceImpl.class);

	@Override
	public String execute() {
		try {
			Date start = new Date();
			Thread.sleep(5000);
			Date end = new Date();
			LOG.info("Slow task executed");
			return "Task finished. " + start + " ~ " + end;

		} catch (InterruptedException e) {
			throw new RuntimeException();
		}
	}

	@Async
	@Override
	public void executeAsync() {
		execute();
	}
}