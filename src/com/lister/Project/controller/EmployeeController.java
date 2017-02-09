package com.lister.Project.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.lister.Project.domain.Employee;
import com.lister.Project.service.EmployeeService;

/**
 * @author souvik.p
 *
 */
@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService es;
	
	
	/**
	 * @param emp
	 * @param model
	 * @return
	 */
	
	@RequestMapping(value="/save")
	public String showredundant(@ModelAttribute("Employee") Employee emp,Map<String,Object> model){
		es.addemployee(emp);
		List<Employee> le=es.getEmployeeList();
		model.put("Employees", le);
		return "employeedtls";
	}
	
	/**
	 * @param id
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/delete")
	public String remove(@RequestParam  String id,Map<String,Object> model){
		es.removeEmployeeByID(Integer.parseInt(id));
		List<Employee> le=es.getEmployeeList();
		model.put("Employees", le);
		return "employeedtls";
	}
	
	/**
	 * @param model
	 * @return
	 * @throws ReportSDKException
	 * @throws IOException
	 */
	@RequestMapping("/generate")
	public String generate(Model model) throws ReportSDKException, IOException{
		if(es.generate()){
			model.addAttribute("message", "Report published succesfully");
		}
		else{
			model.addAttribute("message", "The output report file must be open in some other application.");
			List<Employee> le=es.getEmployeeList();
			model.addAttribute("Employees", le);
			return "employeedtls";
		}
		model.addAttribute("reports",es.download());
		return "reportlist";
	}
}
