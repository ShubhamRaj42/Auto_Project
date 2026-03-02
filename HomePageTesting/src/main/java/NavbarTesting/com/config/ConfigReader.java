package NavbarTesting.com.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	private static Properties prop = new Properties();
	/*

	private static final String BASE_URL = "https://www.myntra.com";
	private static final String BROWSER = "chrome";
	
	*/

	static {
		try {

			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Generic getter
	public static String get(String key) {
		return prop.getProperty(key);
	}

	// Dedicated method for URL (clean + readable)
	public static String getBaseUrl() {
		return prop.getProperty("baseUrl");
	}

	/*
	 * public static String getBaseUrl() { 
	 * return BASE_URL; 
	 * }
	 */

	/*
	 * public static String getBrowser() { 
	 * return BROWSER; 
	 * }
	 */
}