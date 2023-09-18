package com.qa.tutorialsninja.Utilities;

public class SystemInformation {

	public static void main(String[] args) {
		System.getProperties().list(System.out);
		System.out.println("-------------------------------------------");
		System.out.println(System.getProperty("os.name"));

	}

}
