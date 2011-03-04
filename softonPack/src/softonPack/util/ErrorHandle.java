package softonPack.util;

import java.io.IOException;
import java.util.Properties;


public class ErrorHandle {

	private static String errorFlagFile;
	private static String errorFlagDir;
	
	private static final String RUNNING_FLAG = "0";
	private static final String ERROR_FLAG = "1";
	private static final String  NOT_RUNNING_FLAG = " ";

	

	private static void  loadDefaultProperties (){
		Properties prop = PropertieHandle.loadPropertyFile("../conf/error.conf");
		errorFlagDir = prop.getProperty("errorFlagDir");
		errorFlagFile = errorFlagDir +"/"+ prop.getProperty("errorFlagFile");
	}

	private static void loadProperties(String confFile){
		Properties prop = PropertieHandle.loadPropertyFile(confFile);
		errorFlagDir = prop.getProperty("errorFlagDir");
		errorFlagFile = errorFlagDir +"/"+ prop.getProperty("errorFlagFile");		
	}

	
	public static void writeStartFlag(String confFile) throws IOException {
		loadProperties(confFile);
		FileHandle.writeOnFile(RUNNING_FLAG, errorFlagFile);
	}
	
	public static void writeStartFlag() throws IOException {
		loadDefaultProperties();
		FileHandle.writeOnFile(RUNNING_FLAG, errorFlagFile);
	}

	
	public static void writeEndFlag(String confFile) throws IOException {
		loadProperties(confFile);
		FileHandle.writeOnFile(NOT_RUNNING_FLAG, errorFlagFile);
	}
	public static void writeEndFlag() throws IOException  {
		loadDefaultProperties();
		FileHandle.writeOnFile(NOT_RUNNING_FLAG, errorFlagFile);
	}

	public static void writeErrorFlag(String confFile) throws IOException {
		loadProperties(confFile);
		FileHandle.writeOnFile(ERROR_FLAG, errorFlagFile);
	}
	public static void writeErrorFlag() throws IOException  {
		loadDefaultProperties();
		FileHandle.writeOnFile(ERROR_FLAG, errorFlagFile);
	}

}
