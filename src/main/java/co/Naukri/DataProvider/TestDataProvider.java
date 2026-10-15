package co.Naukri.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import co.Naukri.DataReader.DataReader;

public class TestDataProvider {

	private final String currentWorkingDir;
	private static String envDir;

	public TestDataProvider() {
		currentWorkingDir = System.getProperty("user.dir");
		envDir = System.getProperty("user.dir");
	}

	public static String getEnv(String KeyName) throws IOException {
		String filePath = envDir + "/Resources/Environment.properties"; 
		FileReader reader = new FileReader(filePath);
		Properties props = new Properties();
		props.load(reader);
		return props.getProperty(KeyName);
	}

	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException, FileNotFoundException, IOException {
		String filePath = currentWorkingDir + "/Resources/TestData/" + "Naukri.xlsx";
		return DataReader.readData(filePath, "PATHS");
	}
}
