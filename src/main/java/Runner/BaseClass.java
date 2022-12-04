package Runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {
	
	// Variable to fetch details from Global Properties
	static File Globalfile;
	static Properties Globalprop;
	static FileInputStream Globalfip;
	
	// Variable to fetch details from Application Properties
	static File Applicationfile;
	static Properties Applicationprop;
	static FileInputStream Applicationfip;
	
	// Variable to fetch details from connectionDetails Properties
	static File connectionDetailsfile;
	static Properties connectionDetailsprop;
	static FileInputStream connectionDetailsfip;
		
    // Variable to fetch details from Application Properties
	static File log4jfile;
	static Properties log4jprop;
	static FileInputStream log4jfip;
	
	// Method to initialize Global Properties Files
	public static void  initializeGlobalProp() {
		String filepath = System.getProperty("user.dir")+"//src//main//resources//config//global.properties";
		Globalfile = new File(filepath);
		try {
			Globalfip = new FileInputStream(Globalfile);
			Globalprop = new Properties();
			try {
				Globalprop.load(Globalfip);
			} catch (IOException e) {
				System.out.println("IOException found in initializeProp : " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException found in initializeProp : " + e.getMessage());
		}
		
	}
	
	// Method to initialize connectionDetails Properties Files
	public static void  connectionDetailsProp() {
		String filepath = System.getProperty("user.dir")+"//src//main//resources//config//Connection_details.properties";
		connectionDetailsfile = new File(filepath);
		try {
			connectionDetailsfip = new FileInputStream(connectionDetailsfile);
			connectionDetailsprop = new Properties();
			try {
				connectionDetailsprop.load(connectionDetailsfip);
			} catch (IOException e) {
				System.out.println("IOException found in initializeProp : " + e.getMessage());
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException found in initializeProp : " + e.getMessage());
		}
		
	}
	
	// Method to initialize Application Properties Files
		public static void  initializeapplicationProp() {
			String filepath = System.getProperty("user.dir")+"//src//main//resources//config//application.properties";
			Applicationfile = new File(filepath);
			try {
				Applicationfip = new FileInputStream(Applicationfile);
				Applicationprop = new Properties();
				try {
					Applicationprop.load(Applicationfip);
				} catch (IOException e) {
					System.out.println("IOException found in initializeProp : " + e.getMessage());
				}
			} catch (FileNotFoundException e) {
				System.out.println("FileNotFoundException found in initializeProp : " + e.getMessage());
			}
			
		}
	
		// Method to initialize log4j Properties Files
				public static void  initializelog4jProp() {
					String filepath = System.getProperty("user.dir")+"//src//main//resources//config//log4j.properties";
					log4jfile = new File(filepath);
					try {
						log4jfip = new FileInputStream(log4jfile);
						log4jprop = new Properties();
						try {
							log4jprop.load(log4jfip);
						} catch (IOException e) {
							System.out.println("IOException found in initializeProp : " + e.getMessage());
						}
					} catch (FileNotFoundException e) {
						System.out.println("FileNotFoundException found in initializeProp : " + e.getMessage());
					}
					
				}
		
	// Method to Fetch details from Properties Files
	public static String readProperty(String property,String file) {

		switch(file) {
		case "global": return Globalprop.getProperty(property);
		case "application": return Applicationprop.getProperty(property);
		case "Connection_details": return connectionDetailsprop.getProperty(property);
		case "log4j": return log4jprop.getProperty(property);
		default : return "Not able to find Properity file: " + file;
		}
	}

}
