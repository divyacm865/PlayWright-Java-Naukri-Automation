//package co.Naukri.DataReader;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import org.yaml.snakeyaml.Yaml;
//
//import co.Naukri.DataProvider.YAMLConfigData;
//
//public class YamlReader {
//
//	/**
//	 * Option 1: If your folder is a regular directory on your hard drive
//	 * (Recommended Fix) If Resources sits in your project root folder, drop the
//	 * ClassLoader entirely and use a standard file system stream (FileInputStream):
//	 **/
//	public static YAMLConfigData loadConfig() {
//		Yaml yaml = new Yaml();
//		String filePath = System.getProperty("user.dir") + "/Resources/Config.yml";
//		File file = new File(filePath);
//
//		try (InputStream inputStream = new FileInputStream(file)) {
//			return yaml.loadAs(inputStream, YAMLConfigData.class);
//		} catch (Exception e) {
//			throw new RuntimeException("Failed to load YAML configuration", e);
//		}
//	}

package co.Naukri.DataReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.yaml.snakeyaml.Yaml;

import co.Naukri.DataProvider.YAMLConfigData;

public class YamlReader {

    public static YAMLConfigData loadConfig() {

        Yaml yaml = new Yaml();

        String filePath = System.getProperty("user.dir")
                + "/Resources/Config.yml";

        File file = new File(filePath);

        try (InputStream inputStream =
                     new FileInputStream(file)) {

            return yaml.loadAs(
                inputStream, YAMLConfigData.class);

        } catch (Exception e) {
            throw new RuntimeException(
                "Failed to load YAML configuration", e);
        }
    }
}

	/**
	 * Option 2: If you are using standard Maven / Gradle structure
	 * 
	 * If your file is actually located in standard Maven folders like
	 * src/test/resources/Config.yml, you should remove user.dir completely and pass
	 * just the relative path from the resource root:
	 **/
//	public static YAMLConfigData loadConfig() {
//	    Yaml yaml = new Yaml();
//	    // ClassLoader looks directly inside the resource root folder automatically
//	    String fileName = "Config.yml"; 
//	    
//	    try (InputStream inputStream = YamlReader.class.getClassLoader().getResourceAsStream(fileName)) {
//	        if (inputStream == null) {
//	            throw new RuntimeException("Config.yml not found in target/classes or resources.");
//	        }
//	        return yaml.loadAs(inputStream, YAMLConfigData.class);
//	    } catch (Exception e) {
//	        throw new RuntimeException("Failed to load YAML configuration", e);
//	    }
//	}
