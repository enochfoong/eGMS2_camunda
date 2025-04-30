package com.example.app.demo.review_application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.client.ZeebeClient;

@SpringBootApplication
public class ReviewApplication implements CommandLineRunner {

	private static final Logger LOG = LoggerFactory.getLogger(ReviewApplication.class);

	@Autowired
	private ZeebeClient zeebeClient;

	public static void main(String[] args) {
		SpringApplication.run(ReviewApplication.class, args);
	}

	@Override
	public void run(final String... args) {
		var bpmnProcessId = "review-application";
		var event = zeebeClient.newCreateInstanceCommand()
				.bpmnProcessId(bpmnProcessId)
				.latestVersion()
				.send()
				.join();
		LOG.info("started a process instance: {}", event.getProcessInstanceKey());
	}
}
