package com.HealthCare.app.controller;

import com.HealthCare.app.model.Feedback;
import com.HealthCare.app.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @GetMapping("/feedback")
    public String showFeedbackPage(Model model) {
        List<Feedback> feedbackList = feedbackRepository.findAll();
        model.addAttribute("feedbackList", feedbackList);
        return "feedback";
    }
    
    
    @PostMapping("/feedback")
    public String submitFeedback(Feedback feedback) {
    	
LocalDateTime now = LocalDateTime.now();
        
        // Define the format pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy HH:mm");
        
        // Format the date and time using the formatter
        String formattedDateTime = now.format(formatter);
    	
    	 feedback.setCreatedAt(formattedDateTime);
        feedbackRepository.save(feedback);
        return "redirect:/feedback";
    }
}
