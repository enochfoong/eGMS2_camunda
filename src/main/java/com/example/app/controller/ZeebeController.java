package com.example.app.controller;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zeebe")
public class ZeebeController {

    @Autowired
    private ZeebeClient zeebeClient;

    /**
     * Get all open tasks (jobs)
     * 
     * @return List of open tasks
     */
    @GetMapping("/tasks")
    public List<Map<String, Object>> getOpenTasks() {
        List<Map<String, Object>> tasksList = new ArrayList<>();

        try {
            // Fetch and activate jobs (tasks) from Zeebe
            zeebeClient.newActivateJobsCommand()
                    .jobType("user-task") // Specify the job type, e.g., "user-task"
                    .maxJobsToActivate(10) // Maximum number of jobs to fetch
                    .send()
                    .join()
                    .getJobs()
                    .forEach(job -> {
                        // Add job details to the response list
                        Map<String, Object> taskDetails = new HashMap<>();
                        taskDetails.put("id", job.getKey());
                        taskDetails.put("name", job.getElementId());
                        taskDetails.put("processInstanceId", job.getProcessInstanceKey());
                        taskDetails.put("variables", job.getVariablesAsMap());
                        tasksList.add(taskDetails);
                    });

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch tasks: " + e.getMessage());
        }

        return tasksList;
    }

    /**
     * Complete a task with the given taskId
     * 
     * @param taskId    The ID of the task to complete
     * @param variables Variables to pass when completing the task
     * @return Task completion status
     */
    @PostMapping("/tasks/{taskId}/complete")
    public Map<String, Object> completeTask(
            @PathVariable long taskId,
            @RequestBody(required = false) Map<String, Object> variables) {
        try {
            // Complete the task
            zeebeClient.newCompleteCommand(taskId)
                    .variables(variables != null ? variables : new HashMap<>()) // Pass variables if provided
                    .send()
                    .join();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Task completed successfully");
            response.put("taskId", taskId);

            return response;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to complete task: " + e.getMessage());
        }
    }
}