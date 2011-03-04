package softonPack.hibernateTools;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

public class HibernateUtil {

	private static SessionFactory factory;
	private static Session session;
	
	static {
		Configuration conf = new AnnotationConfiguration();
		conf.configure();
		factory = conf.buildSessionFactory();
	}

	private static Session openSession() {
		session = factory.openSession();
		return session;
	}
	
	public static Session getNewSession(){
		return factory.openSession();
	}

	public static Session getCurrentSession() {
		if (session == null) {
			openSession();
		}
		return session;
	}
	
	public static void closeSession() {
		if (session != null) {
			session.flush();
			session.close();
			session = null;
		}
	}
}
