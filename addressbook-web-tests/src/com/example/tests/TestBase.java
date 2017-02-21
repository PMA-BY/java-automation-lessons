package com.example.tests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.example.fw.ApplicationManager;

public class TestBase {

	protected ApplicationManager app;
	

	@BeforeSuite
	public void setUp() throws Exception {
		app = new ApplicationManager();
		
	}

	@AfterSuite
	public void tearDown() throws Exception {
		app.stop();
		
	}

}
