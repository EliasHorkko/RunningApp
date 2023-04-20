package com.example.RunningApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	// This method handles GET requests to the root URL
	@GetMapping("/")
	public String home() {
	    return "index";
	}

	// This method handles GET requests to the /dashboard URL
	@GetMapping("/dashboard")
	public String dashboard() {
	    return "dashboard";
	}
}