package me.stevesite.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Simple class that opens and reads the config.ini file. Data is retrievable from its get methods.
 */
public class ConfigReader {
	private String APIkey;
	private String DBurl;
	private String DBuser;
	private String DBpassword;
	
	/**
	 * Creates the ConfigReader by reading data from the config.ini file and storing it within this class. 
	 * */
	public ConfigReader() {
		readConfig();
	}
	
	/**
	 * Determines which absolute path to the configuration file should be used depending on if it's the 
	 * development or deployment environment.
	 * @return the String path to the config file.
	 */
	private String getConfigPath() {
		if (System.getProperty("os.name").contains("Windows")) {
			return "C:/Users/Steven/Documents/Eclipse Workspace/FreeWeek/config.ini";
		} else {
			return "/home/steven/config.ini";
		}
	}

	/**
	 * Uses java.util.Properties to actually parse through the .ini file for data.
	 * */
	private void readConfig() {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(getConfigPath()));
			APIkey = props.getProperty("APIkey");
			DBurl = props.getProperty("DBurl");
			DBuser = props.getProperty("DBuser");
			DBpassword = props.getProperty("DBpassword");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAPIkey() {
		return APIkey;
	}

	public String getDBurl() {
		return DBurl;
	}

	public String getDBuser() {
		return DBuser;
	}

	public String getDBpassword() {
		return DBpassword;
	}
}
