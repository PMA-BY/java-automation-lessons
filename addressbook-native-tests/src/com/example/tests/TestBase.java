package com.example.tests;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.example.fw.ApplicationManager;
import com.example.fw.Contact;

public class TestBase {
	
	public ApplicationManager app;
	
	@BeforeTest
	@Parameters({"configFile"})
	public void setUp(@Optional String configFile) throws Exception {
		if (configFile == null)
			configFile = System.getProperty("configFile");
		if (configFile == null)
			configFile = System.getenv("configFile");
		if (configFile == null)
			configFile = "application.properties";
		Properties props = new Properties();
		props.load(new FileReader(configFile));
		app = ApplicationManager.getInstance(props);
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		ApplicationManager.getInstance(null).stop();
	  }
	
	public static List<Object[]> wrapContactsForDataProvider(List<Contact> contacts) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (Contact contact : contacts) {
			list.add(new Object[]{contact});
		}
		return list;
	}

}
