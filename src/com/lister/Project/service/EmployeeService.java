package com.lister.Project.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import org.springframework.beans.factory.BeanFactory;  
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;  
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.lister.Project.dao.EmployeeDao;
import com.lister.Project.dao.GenerateReport;
import com.lister.Project.domain.Employee;

/**
 * @author souvik.p
 *
 */

@SuppressWarnings({ "deprecation" })
@Component
public class EmployeeService {
	EmployeeDao empdao;
	Resource r;
	BeanFactory factory;
	GenerateReport gr;
	public EmployeeService(){
		r=new ClassPathResource("DefaultServlet-servlet.xml");  	
	    factory=new XmlBeanFactory(r);  
	    empdao=(EmployeeDao)factory.getBean("d");
	}
	/**
	 * @param emp
	 */
	public void addemployee(Employee emp){
		empdao.saveEmployee(emp);
	}
	/**
	 * @param id
	 */
	public void removeEmployeeByID(int id){
		empdao.deleteEmployee(empdao.getById(id));
	}
	/**
	 * @return
	 */
	public List<Employee> getEmployeeList(){
	    return empdao.getEmployees();
	}
	/**
	 * @param name
	 * @return
	 */
	public List<Employee> getEmployeeList(String name){
	    return empdao.getEmployeeByName(name);
	}
	/**
	 * @return
	 * @throws ReportSDKException
	 * @throws IOException
	 */
	public boolean generate() throws ReportSDKException, IOException{
		gr=new GenerateReport();
		return gr.generate();
	}
	
	public List<String> download(){
		File directory=new File("D://GeneratedReports");
		File lf[]=directory.listFiles();
		Arrays.sort(lf, (a, b) -> Long.compare(a.lastModified(), b.lastModified()));
	    List<String> fname=new ArrayList<String>();
		for(File oListItem:lf){
			if(!oListItem.isDirectory()){
				fname.add(oListItem.getName());
				System.out.println(oListItem.getName());
			}
		}
		return fname;
	}
}
