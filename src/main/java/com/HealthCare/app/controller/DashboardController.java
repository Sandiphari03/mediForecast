package com.HealthCare.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.HealthCare.app.model.Feedback;
import com.HealthCare.app.repository.FeedbackRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class DashboardController {
	 @Autowired
	    private RestTemplate restTemplate;
	 @Autowired
	    private FeedbackRepository feedbackRepository;
	 
	 @GetMapping("/")
	 public String home(Model model) {
		
		        List<Feedback> feedbackList = feedbackRepository.findAll();
		     //   List<Feedback> firstFiveFeedback = feedbackList.subList(0,6);
		        
		        model.addAttribute("feedbackList", feedbackList);
		        return "index";
		  
	 }
	 
	@GetMapping("/heartDiseaseForm")
	public String heartForm() {
		return "heartDiseaseForm";
	}
	
	@GetMapping("/contactUs")
	public String contactUs() {
		return "contactUs";
	}
	 
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	 @GetMapping("/kidneyStatusForm")
		public String kidneyForm() {
			return "kidneyStatusForm";
		}
	 
	 @GetMapping("/liverProblemForm")
		public String liverForm() {
			return "liverProblemForm";
		}
	 
	 @GetMapping("/diabetesStatusForm")
		public String Form() {
			return "diabetesStatusForm";
		}
	
 @PostMapping("/predictHeartDisease")
 public String heart(
         @RequestParam("Total_Cholesterol") String totalCholesterol,
         @RequestParam("LDL_Cholesterol") String ldlCholesterol,
         @RequestParam("HDL_Cholesterol") String hdlCholesterol,
         @RequestParam("Systolic_BP") String systolicBP,
         @RequestParam("Diastolic_BP") String diastolicBP,
         @RequestParam("Blood_Glucose") String bloodGlucose,
         @RequestParam("Troponin") String troponin,
         @RequestParam("CK_MB") String ckMb,
         @RequestParam("LDH") String ldh,
         @RequestParam("Potassium") String potassium,
         @RequestParam("Sodium") String sodium,
         @RequestParam("Hemoglobin") String hemoglobin,
         @RequestParam("Red_Blood_Cells") String redBloodCells,
         @RequestParam("White_Blood_Cells") String whiteBloodCells,
         @RequestParam("Platelet_Count") String plateletCount,
         @RequestParam("CRP") String crp,
         @RequestParam("ESR") String esr,
         Model model
 ) throws JsonMappingException, JsonProcessingException {
     String apiUrl = "http://localhost:9538/predict_heart_disease?"+
     		"Total_Cholesterol=" + totalCholesterol +
             "&LDL_Cholesterol=" + ldlCholesterol +
             "&HDL_Cholesterol=" + hdlCholesterol +
             "&Systolic_BP=" + systolicBP +
             "&Diastolic_BP=" + diastolicBP +
             "&Blood_Glucose=" + bloodGlucose +
             "&Troponin=" + troponin +
             "&CK_MB=" + ckMb +
             "&LDH=" + ldh +
             "&Potassium=" + potassium +
             "&Sodium=" + sodium +
             "&Hemoglobin=" + hemoglobin +
             "&Red_Blood_Cells=" + redBloodCells +
             "&White_Blood_Cells=" + whiteBloodCells +
             "&Platelet_Count=" + plateletCount +
             "&CRP=" + crp +
             "&ESR=" + esr;

     // Call the prediction API
    String response = restTemplate.getForObject(apiUrl, String.class);
    
     if (response=="1") {
			model.addAttribute("predictionResult", "Heart Disease");
		} else {
			model.addAttribute("predictionResult", "No Heart Disease");
		}

     return "predictionResult"; // Thymeleaf template name for displaying result
 }


 
 @PostMapping("/predictDiabetes")
 public String diabetes(
         @RequestParam("fastingGlucose") String fastingGlucose,
         @RequestParam("hba1c") String hba1c,
         @RequestParam("randomGlucose") String randomGlucose,
         @RequestParam("urineGlucose") String urineGlucose,
         @RequestParam("microalbumin") String microalbumin,
         @RequestParam("creatinine") String creatinine,
         Model model
 ) {
     // Call API with the parameters
     String apiUrl = "http://localhost:6262/predict_diabetes?"+
    		 "Fasting_Blood_Glucose=" + fastingGlucose +
             "&HbA1c=" + hba1c +
             "&Random_Blood_Glucose=" + randomGlucose +
             "&Urine_Glucose=" + urineGlucose +
             "&Urine_Microalbumin=" + microalbumin +
             "&Serum_Creatinine=" + creatinine;
     String response = restTemplate.getForObject(apiUrl, String.class);

     if (response=="1") {
  			model.addAttribute("predictionResult", "Diabetes");
  		} else {
  			model.addAttribute("predictionResult", "No Diabetes");
  		}
     return "predictionResult";  
 }

 
 @PostMapping("/predictLiverProblem")
 public String submitForm(
         @RequestParam("ALT") String alt,
         @RequestParam("AST") String ast,
         @RequestParam("ALP") String alp,
         @RequestParam("TotalBilirubin") String bilirubin,
         @RequestParam("GGT") String ggt,
         @RequestParam("Albumin") String albumin,
         @RequestParam("PT") String ptSeconds,
         Model model) {

     // Prepare the data to send to the API
     String apiUrl = "http://localhost:6262/predict_liver_problem?"+ 
     		 "ALT_U_L=" + alt +
             "&AST_U_L=" + ast +
             "&ALP_U_L=" + alp +
             "&Total_Bilirubin_mg_dL=" + bilirubin +
             "&GGT_U_L=" + ggt +
             "&Albumin_g_dL=" + albumin +
             "&PT_seconds=" + ptSeconds;

     String response = restTemplate.getForObject(apiUrl, String.class);

     if (response=="1") {
			model.addAttribute("predictionResult", "Problem In Liver");
		} else {
			model.addAttribute("predictionResult", "No Problem In Liver");
		}
return "predictionResult";
 }
 

 
 @PostMapping("/predictKidneyStatus")
 public String kidney(@RequestParam("serumCreatinine") String serumCreatinine,
		 					@RequestParam("BUN") String bun,
                             @RequestParam("eGFR") String egfr,
                             @RequestParam("ACR") String acr,
                             @RequestParam("serumSodium") String serumSodium,
                             @RequestParam("serumPotassium") String serumPotassium,
                             @RequestParam("urineSpecificGravity") String urineSpecificGravity,
                             @RequestParam("serumCystatinC") String serumCystatinC,
                             Model model) {

     // Construct the URL for the API
     String apiUrl = "http://localhost:6262/predict_kidney_status?"
             + "Serum_Creatinine=" + serumCreatinine
             + "&BUN=" + bun
             + "&eGFR=" + egfr
             + "&ACR=" + acr
             + "&Serum_Sodium=" + serumSodium
             + "&Serum_Potassium=" + serumPotassium
             + "&Urine_Specific_Gravity=" + urineSpecificGravity
             + "&Serum_Cystatin_C=" + serumCystatinC;

     // Send a GET request to the API and get the response
     String response = restTemplate.getForObject(apiUrl, String.class);

     if (response=="1") {
			model.addAttribute("predictionResult", "Problem In Kidney");
		} else {
			model.addAttribute("predictionResult", "No Problem In Kidney");
		}
  return "predictionResult"; 
  
  
  
  
 }

 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
}
