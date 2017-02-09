package com.lister.Project.service;

import java.sql.SQLException;
import java.util.Map;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lister.Project.dao.EmployeeDaoReadTest;
import com.lister.Project.dao.EmployeeDaoTest;
import com.lister.Project.domain.Employee;




@SuppressWarnings({ "unused", "deprecation" })
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:testconfig.xml","classpath*:DefaultServlet-servlet.xml"})
public class EmployeeServiceTest {
	private MockMvc mockMvc;
    
    private EmployeeDaoTest empdaotest;
    private EmployeeDaoReadTest empdaoreadtest;
	private Resource rtest;
	private BeanFactory factorytest;
    
    @Autowired
    ApplicationContext context;
    
    
    @InjectMocks
    EmployeeService es;
    
    
    @Mock
    Map<String,Object> model;
    
    @InjectMocks
    Employee emp;
    
    @Before
	public void setUp() throws Exception{
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(es).build();
		rtest=new ClassPathResource("testconfig.xml");  	
	    factorytest=new XmlBeanFactory(rtest);  
	    empdaotest=(EmployeeDaoTest)factorytest.getBean("d");
	    
	    emp.setName("Rishabh");
	    emp.setSalary(23000);
	    empdaoreadtest=new EmployeeDaoReadTest();
	    empdaoreadtest.saveEmployee(emp);
	}
    
    @Test
    public void getEmployee(){
    	Assert.assertEquals(1, empdaoreadtest.getEmployees().size());
    }
    
    @After
    public void terminate() throws SQLException{
    	empdaoreadtest.rollback();
    }
}
