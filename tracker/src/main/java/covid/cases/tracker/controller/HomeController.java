package covid.cases.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import covid.cases.tracker.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import covid.cases.tracker.services.TrackerService;

@Controller

public class HomeController 
{
	@Autowired
	private TrackerService coronaVirusDataService;
	
	@GetMapping("/")
	public String home(Model model)
	{
		    List<LocationDetails> allStats = coronaVirusDataService.getDetails();
	        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getTotalCase()).sum();
	        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
	        model.addAttribute("locationStats", allStats);
	        model.addAttribute("totalReportedCases", totalReportedCases);
	        model.addAttribute("totalNewCases", totalNewCases);
		
		return "home";
		
	}

}
