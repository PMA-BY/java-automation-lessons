package com.example.fw;

import java.util.Properties;

public class ApplicationManager {

	private static ApplicationManager singleton;
	private Properties props;
	private ContactHelper contactHelper;

	public static ApplicationManager getInstance() {

		if (singleton == null) {
			singleton = new ApplicationManager();
		}
		return singleton;
	}

	public void stop() {

	}

	public ContactHelper getContactHelper() {
		
		if (contactHelper == null) {
			contactHelper = new ContactHelper(this);
		}
		return contactHelper;
	}

//	public ContactHelper getContactHelper() {
//		if (contactHelper == null) {
//			contactHelper = new ContactHelper(this);
//		}
//		return contactHelper;
//	}
}
