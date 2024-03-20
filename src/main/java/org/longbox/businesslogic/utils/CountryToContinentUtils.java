package org.longbox.businesslogic.utils;

import java.util.HashMap;

public class CountryToContinentUtils {
	
	private static HashMap<String, String> regionsHashMap = new HashMap<String, String>();
	
	static {
		regionsHashMap.put("Canada", "North_America");
		regionsHashMap.put("Mexico", "North_America");
		regionsHashMap.put("United States", "North_America");
	}
	
	public static String getContinent(String country) {
		return regionsHashMap.get(country);
	}

}
