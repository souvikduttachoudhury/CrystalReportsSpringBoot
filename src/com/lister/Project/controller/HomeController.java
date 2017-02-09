package com.lister.Project.controller;



import java.io.IOException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.lister.Project.domain.Employee;

/**
 * @author souvik.p
 *
 */
@Component
@Controller
public class HomeController {
	
	/**
	 * @param model
	 * @return
	 * @throws ReportSDKException
	 * @throws IOException
	 */
	
	@RequestMapping(value="/",method = RequestMethod.GET)
	public String Hello(Model model) throws ReportSDKException, IOException{
		Employee e=new Employee();
		model.addAttribute("Employee", e);
		return "welcome";
	}
}
