package com.example.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class GroupDataGenerator {

	public static void main(String[] args) throws IOException {
		if (args.length < 3) {
			System.out.println("Please specify parameters: <amount of test data> <file name> <format csv|xml>");
			return;
		}

		int amount = Integer.parseInt(args[0]); // NumberFormatException
		File file = new File(args[1]);
		String format = args[2];

		if (file.exists()) {
			System.out.println("The file with name " + file + " is exists");
			return;

		}

		List<GroupData> groups = generateRandomGroups(amount);

		if ("csv".equals(format)) {
			saveGroupsToCsvFile(groups, file);
		} else if ("xml".equals(format)) {
			saveGroupsToXmlFile(groups, file);
		} else {
			System.out.println("Unknown format" + format);
			return;
		}
	}

	private static void saveGroupsToXmlFile(List<GroupData> groups, File file) throws IOException {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("group", GroupData.class);
		String xml = xstream.toXML(groups);
		FileWriter writer = new FileWriter(file);
		writer.write(xml);
		writer.close();
	}

	public static List<GroupData> loadGroupsFromXmlFile(File file) {
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("group", GroupData.class);
		return (List<GroupData>) xstream.fromXML(file);
	}

	private static void saveGroupsToCsvFile(List<GroupData> groups, File file) throws IOException {
		FileWriter writer = new FileWriter(file);

		for (GroupData group : groups) {
			writer.write(group.getName() + "," + group.getHeader() + "," + group.getFooter() + ",!" + "\n");
		}

		writer.close();
	}

	public static List<GroupData> loadGroupsFromCsvFile(File file) throws IOException {
		List<GroupData> list = new ArrayList<GroupData>();
		FileReader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line = bufferedReader.readLine();
		while (line != null) {
			String[] part = line.split(",");
			GroupData group = new GroupData().withName(part[0]).withHeader(part[1]).withFooter(part[2]);
			list.add(group);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		reader.close();
		return list;
	}

	public static List<GroupData> generateRandomGroups(int amount) {
		List<GroupData> list = new ArrayList<GroupData>();
		for (int iterator = 0; iterator < amount; iterator++) {
			GroupData group = new GroupData().withName(generateRandomString()).withHeader(generateRandomString())
					.withFooter(generateRandomString());
			list.add(group);
		}
		return list;
	}

	public static String generateRandomString() {
		Random rnd = new Random();
		String string;
		if (rnd.nextInt(5) == 0) {
			string = "";
		} else {
			string = "test_" + rnd.nextInt();
		}
		return string;
	}
}
