package com.example.fw;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.testng.log4testng.Logger;

public class FtpHelper extends HelperBase {

	// public static Logger log = Logger.getLogger(FtpHelper.class.getName());

	private FTPClient ftp;

	public FtpHelper(ApplicationManager manager) {
		super(manager);
	}

	private void initFtpConnection() {
		String ftpserver = manager.getProperty("ftp.host");
		String login = manager.getProperty("ftp.login");
		String password = manager.getProperty("ftp.password");
		String appPath = manager.getProperty("ftp.appPath");

//		ftp = new FTPClient();
//		try {
//			ftp.connect(ftpserver);
//			ftp.login(login, password);
//			System.out.println("Connected to " + ftpserver + ": " + ftp.getReplyString());
//			ftp.changeWorkingDirectory(appPath);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

	private void closeFtpConnection() {
		try {
			ftp.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void installConfigWithoutCapcha() {
//		String configFile = manager.getProperty("ftp.configFile");
//
//		initFtpConnection();
//
//		try {
//			// Check if there is backup of config file
//			boolean backupExists = false;
//			FTPFile[] files = ftp.listFiles();
//			for (int i = 0; i < files.length; i++) {
//				if (files[i].getName().equals(configFile + ".bak")) {
//					backupExists = true;
//					break;
//				}
//			}
//
//			if (!backupExists) {
//				ftp.rename(configFile, configFile + ".bak");
//			}
//
//			InputStream in = this.getClass().getResourceAsStream("/" + configFile);
//			ftp.storeFile(configFile, in);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		closeFtpConnection();
	}

	public void restoreConfig() {
		String configFile = manager.getProperty("ftp.configFile");
//		initFtpConnection();
//
//		try {
//			// Check if there is backup of config file
//			boolean backupExists = false;
//			FTPFile[] files = ftp.listFiles();
//			for (int i = 0; i < files.length; i++) {
//				if (files[i].getName().equals(configFile + ".bak")) {
//					backupExists = true;
//					break;
//				}
//			}
//
//			if (backupExists) {
//				ftp.deleteFile(configFile);
//				ftp.rename(configFile + ".bak", configFile);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		closeFtpConnection();
	}

}
