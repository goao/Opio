package softonPack.hibernateTools;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;

public class JPAHandle {
	private static Logger log = Logger.getLogger(JPAHandle.class);
	
	private static EntityManagerFactory emf;
	
	public static EntityManager createNewConnection(Properties p) throws Exception {
		Map<String, String> properties = new HashMap<String, String>();
		
		
		String username = p.getProperty("username");
		String password = p.getProperty("password");
		String db_url = p.getProperty("db_url");
		String jpa_unit_name = p.getProperty("jpa_unit_name");
		
        properties.put("hibernate.connection.username", username);
        properties.put("hibernate.connection.password", password);
        properties.put("hibernate.connection.url", db_url); 
        log.debug("conectando ao database: " + db_url);
		
		
		if(emf == null){
//			throw new Exception("EntityManagerFactory não inicializado. Arquivo properties nao encontrado ou " +
//					" persistence-unit-name 'safdes' nao encontrado em persistence.xml ");
			
			emf = Persistence.createEntityManagerFactory(jpa_unit_name, properties);
		}
		EntityManager em = emf.createEntityManager();
			
		return em;
	}

}
