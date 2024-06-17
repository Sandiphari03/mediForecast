package com.HealthCare.app.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.HealthCare.app.model.Doctor;
import com.HealthCare.app.service.DoctorService;

@Controller
public class findDoctorController {
	
	@Autowired
	DoctorService doctorService;
	
	Map<String, String[]> cities = new HashMap<>();
	String state;
	
	
	@GetMapping("/find-doctors")
	 public String findDoctorsByCity(@RequestParam String city, Model model) {
		
		List<String> statesList = new ArrayList<>(cities.keySet());
		model.addAttribute("statesList", statesList);
    
       String[] citie = cities.get(state);

       model.addAttribute("citiesList", citie);
		
	        List<Doctor> doctors = doctorService.findDoctorsByCity(city);
	        model.addAttribute("doctors", doctors);
	        return "findDoctor"; 
	    }
    
    @GetMapping("/findDoctor")
    public String findDoctor(Model model) {
    	
    	Map<String, String[]> citiesByState = new HashMap<>();
    	citiesByState.put("Andhra Pradesh", new String[]{
                "Visakhapatnam", "Tirupati", "Guntur", "Rajahmundry", "Kakinada", 
                "Kurnool", "Anantapur", "Kadapa", "Vizianagaram", "Chittoor", 
                "Srikakulam", "Eluru", "Machilipatnam", "Ongole", "Amaravati", 
                "Sri City", "Ichchapuram", "Kasibugga"
            });
            
            // Other states and their cities
            citiesByState.put("Maharashtra", new String[]{
                "Mumbai", "Pune", "Nagpur", "Nashik", "Aurangabad", 
                "Solapur", "Amravati", "Kolhapur", "Navi Mumbai", "Thane"
            });
            
            citiesByState.put("Tamil Nadu", new String[]{
                "Chennai", "Coimbatore", "Madurai", "Tiruchirappalli", "Salem", 
                "Tirunelveli", "Vellore", "Erode", "Thoothukudi", "Thanjavur"
            });
        citiesByState.put("Arunachal Pradesh", new String[]{"Itanagar", "Naharlagun"});
        citiesByState.put("Assam", new String[]{"Guwahati", "Silchar", "Dibrugarh"});
        citiesByState.put("Bihar", new String[]{"Patna", "Gaya", "Bhagalpur"});
        citiesByState.put("Chhattisgarh", new String[]{"Raipur", "Bilaspur", "Durg"});
        citiesByState.put("Goa", new String[]{"Panaji", "Margao"});
        citiesByState.put("Gujarat", new String[]{"Ahmedabad", "Surat", "Vadodara"});
        citiesByState.put("Haryana", new String[]{"Faridabad", "Gurgaon", "Panipat"});
        citiesByState.put("Himachal Pradesh", new String[]{"Shimla", "Dharamshala", "Kullu"});
        citiesByState.put("Jharkhand", new String[]{"Ranchi", "Jamshedpur", "Dhanbad"});
        citiesByState.put("Karnataka", new String[]{"Bangalore", "Mysore", "Hubli"});
        citiesByState.put("Kerala", new String[]{"Thiruvananthapuram", "Kochi", "Kozhikode"});
        citiesByState.put("Madhya Pradesh", new String[]{"Bhopal", "Indore", "Jabalpur"});
        
        citiesByState.put("Manipur", new String[]{"Imphal", "Thoubal"});
        citiesByState.put("Meghalaya", new String[]{"Shillong", "Tura"});
        citiesByState.put("Mizoram", new String[]{"Aizawl", "Lunglei"});
        citiesByState.put("Nagaland", new String[]{"Kohima", "Dimapur"});
        citiesByState.put("Odisha", new String[]{"Bhubaneswar", "Cuttack", "Rourkela"});
        citiesByState.put("Punjab", new String[]{"Ludhiana", "Amritsar", "Jalandhar"});
        citiesByState.put("Rajasthan", new String[]{"Jaipur", "Jodhpur", "Udaipur"});
        citiesByState.put("Sikkim", new String[]{"Gangtok", "Namchi"});
        
        citiesByState.put("Telangana", new String[]{"Hyderabad", "Warangal", "Karimnagar"});
        citiesByState.put("Tripura", new String[]{"Agartala", "Udaipur"});
        citiesByState.put("Uttar Pradesh", new String[]{"Lucknow", "Kanpur", "Agra"});
        citiesByState.put("Uttarakhand", new String[]{"Dehradun", "Haridwar", "Roorkee"});
        citiesByState.put("West Bengal", new String[]{"Kolkata", "Howrah", "Durgapur"});

        // Generate a list of states
        List<String> statesList = new ArrayList<>(citiesByState.keySet());

        // Pass the states list to the Thymeleaf template
        model.addAttribute("statesList", statesList);
  
        cities=citiesByState;
    	
    	return "findDoctor";
    }
    
    
    @GetMapping("/find-cities")
	 public String findDoctorsByState(@RequestParam String selectedState, Model model) {
    	
    		List<String> statesList = new ArrayList<>(cities.keySet());
    		model.addAttribute("statesList", statesList);
    		state=selectedState;
            String[] citie = cities.get(selectedState);
	        model.addAttribute("citiesList", citie);
	        return "findDoctor";
	    }
    
}

