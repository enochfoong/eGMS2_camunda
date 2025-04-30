package com.example.app.demo.review_application;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class SendEmailNotiWorker {
    private final static Logger LOG = LoggerFactory.getLogger(SendEmailNotiWorker.class);

    @JobWorker(type = "er-review-notification")
    public Map<String, Double> sendErEmail() {
        LOG.info("Sending notification email to ER...");
        return Map.of("amountCharged", (double) 0);
    }

    @JobWorker(type = "rfs-review-notification")
    public Map<String, Double> sendRfsEmail() {
        LOG.info("Sending notification email to RFS...");
        return Map.of("amountCharged", (double) 0);
    }
}
