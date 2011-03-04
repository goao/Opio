package softonPack.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;



/**
 * @author mario
 * 
 */
public class PropertieHandle {

	Properties props = new Properties();
	static Properties propsStatic; 

	
	public PropertieHandle(String pathAndFileName) throws Exception {
		try {
			this.props.load (new FileInputStream(new File(pathAndFileName)));
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("arquivo de propiedades: " + pathAndFileName + " nao encontrado.");
		}

	}
	
	public static void setStatic(String pathAndFileName) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(pathAndFileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		propsStatic = prop;		
	}
	
	public static void setStaticStream(InputStream pathAndFileName) {
		Properties prop = new Properties();
		try {
			prop.load(pathAndFileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		propsStatic = prop;		
	}
	
	public static void setStatic(File file) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		propsStatic = prop;		
	}
	
	/**
	 * @autor Mario Caseiro
	 * 
	 * Retorna um java.util.properties carregado com o arquivo informado no par�metro.
	 * O parametro deve ser um path absoluto.
	 * @param fileName
	 * @return Properties carregado pronto para dar getProperty()
	 */
	public static Properties loadPropertyFile(String pathAndFileName) {
//		InputStream file = PropertieHandle.class
//				.getResourceAsStream(pathAndFileName);

		Properties props = new Properties();

		try {
			props.load(new FileInputStream(new File(pathAndFileName)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}
	
	
	public static Properties loadPropertyFileStream(String pathAndFileName) throws Exception {
		Properties props = new Properties();
		InputStream foo = ClassLoader.getSystemResourceAsStream(pathAndFileName);
		//java.net.URL url getSystemResource(pathAndFileName);
		
		//todo arrumar path
		
		
		try {
			if(foo == null){
				throw new IOException();
			}
			//props.load(url.openStream());
			props.load(foo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("PropertieHandle.loadPropertyFromResource() error: " +
					pathAndFileName + " nao encontrado");
		}
		
		return props;
	}
	
	public static void savePropertyFile(String pathAndFileName, String key, String value) {
		Properties props = new Properties();

		try {
			props.load(new FileInputStream(new File(pathAndFileName)));
			props.put(key, value);
			FileOutputStream out = new FileOutputStream(pathAndFileName);
			props.store(out, "Alterado em " + new Date().toString());
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	
	/**
	 * @autor Mario Caseiro
	 * 
	 * Retorna um java.util.properties carregado com o arquivo informado no par�metro.
	 * O par�metro � relativo ao resource da aplica��o
	 * @param fileName
	 * @return Properties carregado pronto para dar getProperty()
	 */	
	public static Properties loadPropertyFromResource(String pathAndFileName) {
		InputStream file = null;
		try {
			file = PropertieHandle.class.getResourceAsStream(pathAndFileName);
			if(file == null)
				throw new IOException("PropertieHandle.loadPropertyFromResource() error: " +
						pathAndFileName + " nao encontrado");
		} catch (IOException e) {
			e.printStackTrace();
		}

		Properties props = new Properties();

		try {
			props.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return props;
	}	
	
	public static String getPropertieStatic(String propName) {
		return propsStatic.getProperty(propName);
	}

	
	public String getPropertie(String propName) {
		String propertie;
		propertie = props.getProperty(propName);

		return propertie;
	}

	public Boolean hasPropertie(String propName) {
		if (this.props.containsKey(propName))
			return true;

		return false;
	}

}
