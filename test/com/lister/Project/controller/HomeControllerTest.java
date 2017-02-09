package com.lister.Project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import com.crystaldecisions.sdk.occa.report.lib.ReportSDKException;
import com.lister.Project.rules.MockitoInitializerRule;


@SuppressWarnings("unused")
@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

	    private MockMvc mockMvc;
	    
	    @Autowired
	    ApplicationContext context;
	    
	    @InjectMocks
	    HomeController object;
	    
	    @Mock
	    Model model;
	    
	    
	    @Rule
	    public TestRule mockitoInitializerRule = new MockitoInitializerRule(this);
	    
	
		@BeforeClass
		public static void initHomeController() throws IOException{
			
		}
		
		@Before
		public void setUp() throws Exception{
			
			MockitoAnnotations.initMocks(this);
			this.mockMvc = MockMvcBuilders.standaloneSetup((new HomeController())).build();
			
			System.out.println("Testing Started");
		}
		
		
		@After
		public void tearDown() throws ReportSDKException, IOException{
			System.out.println("Testing completed");
		}
		
		@Test
		public void testHandleLogin() throws Exception {
			
			String s = object.Hello(model);
			Assert.assertEquals("welcome", s);
			this.mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("welcome"));
		}
	
}
