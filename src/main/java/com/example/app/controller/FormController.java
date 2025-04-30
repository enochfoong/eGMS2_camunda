package com.example.app.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/form")
public class FormController {

    @PostMapping("/submit")
    public String submitForm(@RequestBody UserInput input) {
        // Log the received input
        System.out.println("Received COI value: " + input.getCoi());

        // Process the input (e.g., save to database or perform business logic)
        if ("yes".equalsIgnoreCase(input.getCoi())) {
            return "Conflict of Interest (COI) identified!";
        } else if ("no".equalsIgnoreCase(input.getCoi())) {
            return "No Conflict of Interest (COI) identified!";
        } else {
            return "Invalid input received!";
        }
    }

    // Class to represent user input
    public static class UserInput {
        private String coi; // Field for the "COI?" dropdown value

        // Getter and Setter
        public String getCoi() {
            return coi;
        }

        public void setCoi(String coi) {
            this.coi = coi;
        }
    }
}