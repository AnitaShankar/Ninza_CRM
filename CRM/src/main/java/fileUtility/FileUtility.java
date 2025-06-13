package fileUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileUtility {

	
	public String ReadDataFromPropertiesFile(String Key) throws IOException
	{
		//Reading data from Properties files
		FileInputStream fis=new FileInputStream("./configAppData/NinzaCRMLoginData.properties");
		Properties prop=new Properties();
		prop.load(fis);
		
		String value = prop.getProperty(Key);
		return value;
		
	}
}
