package com.homework.tests;

public class GroupData {
	public String name = "";
	public String header = "";
	public String footer = "";

	public GroupData(String name, String header, String footer) {
		this.name = name;
		this.header = header;
		this.footer = footer;
	}
 
	public GroupData() {
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}
}