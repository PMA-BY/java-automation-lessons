package com.example.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.fw.Contact;

public class ContactDataGenerator {

	public static void main(String[] args) throws IOException {
		if(args.length < 2) {
			System.out.println("Please specify parameters: <amount of test data>, <file>");
			return;
		}
		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
			
		if (file.exists()) {
			file.delete();
			System.out.println("OLD FILE <" + file + "> HAS BEEN DELETED");
		}
		
		List<Contact> contacts = generateRandomContacts(amount);
		saveContactsToCsvFile(contacts,file);
	}

	private static void saveContactsToCsvFile(List<Contact> contacts, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		for (Contact contact : contacts) {
			writer.write(
					contact.getName() 				+ "," + 
					contact.getSurname() 			+ "," + 
					contact.getEmail()				+ "," + 
					contact.getCity() 				+ "," + 
					contact.getInternetContact() 	+ "," + 
					contact.getStreetAddress() 		+ "," + 
					contact.getPostalCode() 		+ "," + 
					contact.getCountry() 			+ "," + 
					contact.getPhone() 				+ "," + 
					contact.getFax() 				+ "," + 
					contact.getCell() 				+ "," + 
					contact.getWebPage() 			+ ",!" + "\n"
				);
		}
		writer.close();
	}
	
	public static List<Contact> loadContactsFromFile(File file) throws IOException {
		List<Contact> list = new ArrayList<Contact>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		while(line != null) {
			String[] part = line.split(",");
			Contact contact = new Contact()
						.setName(part[0]) 		
						.setSurname(part[1]) 		
						.setEmail(part[2]) 		
						.setInternetContact(part[3]) 	
						.setStreetAddress(part[4]) 	
						.setCity(part[5]) 		
						.setPostalCode(part[6]) 		
						.setCountry(part[7]) 		
						.setPhone(part[8]) 		
						.setFax(part[9]) 	
						.setCell(part[10]) 		
						.setWebPage(part[11]);
			list.add(contact);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return list;
	}

	public static List<Contact> generateRandomContacts(int amount) throws IOException {
		List<Contact> list = new ArrayList<Contact>();
		Random rnd = new Random();
		for (int i = 0; i < amount; i++) {
			Contact contact = new Contact()
					.setName(randomValue(randomStringAlphaNumeric(rnd.nextInt(10)))) 		
					.setSurname(randomValue(randomStringAlphaNumeric(rnd.nextInt(15)))) 		
					.setEmail(randomValue(randomEMail(9,5,3))) 		
					.setInternetContact(randomValue(randomStringLatAlphaNumericWithoutSpace(rnd.nextInt(10))))
					.setStreetAddress(randomValue(randomStringAlphaNumeric(rnd.nextInt(40)))) 	
					.setCity(randomValue(randomStringAlphaNumeric(rnd.nextInt(40)))) 		
					.setPostalCode(randomValue(randomStringNumeric(6))) 		
					.setCountry(randomValue(randomStringAlphaNumeric(rnd.nextInt(40)))) 		
					.setPhone(randomValue(randomPhoneNumber(1,3,7))) 		
					.setFax(randomValue(randomPhoneNumber(1,3,7))) 	
					.setCell(randomValue(randomPhoneNumber(1,3,7))) 		
					.setWebPage(randomValue("www." + randomStringLatAlphaNumericWithoutSpace(rnd.nextInt(20)) + randomStringLatAlphaNumericWithoutSpace(rnd.nextInt(4))));
			list.add(contact);
		}
		return list;
	}

	private static String randomValue(String randomValue) {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return "";
		if (rnd.nextInt(10) == 1)
			return null;
		else 
			return randomValue;
	}
	
	private static String randomEMail(int lengthName, int lengthDomen, int lengthZone) {
		String randomEmail = randomStringLatAlphaNumericWithoutSpace(lengthName) 
				+ "@" + randomStringLatAlphaNumericWithoutSpace(lengthDomen) 
				+ "." + randomStringLatAlphaNumericWithoutSpace(lengthZone);
		return randomEmail;
	}
	
	private static String randomPhoneNumber(int lengthCountryCode, int lengthCityCode, int lengthNumber) {
		String randomPhoneNumber = randomStringNumeric(lengthCountryCode)
				+ " (" + randomStringNumeric(lengthCityCode) + ") "
				+ randomStringNumeric(lengthNumber);
		return randomPhoneNumber;
	}
	
	private static String randomStringLatAlphaNumericWithoutSpace(final int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM1234567890._".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	private static String randomStringNumeric(final int length) {
		char[] chars = "1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	private static String randomStringAlphaNumeric(final int length) {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return "";
		else if (rnd.nextInt(10) == 0)
			return null;
		char[] chars = "abcdefghijklmnopqrstuvwxyz QWERTYUIOPASDFGHJKLZXCVBNM 1234567890 ._".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
}
