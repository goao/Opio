package softonPack.hibernateTools;


import org.hibernate.Session;
import org.hibernate.Transaction;

import softonPack.util.ReflectionHandle;



public class DaoFactory {

	//private final Session session;
	
	private static DaoFactory instance;
	private Session session;

	public static Session getSession() {
		return instance.session;
	}
	
	public static DaoFactory getInstance() {
		if(instance == null)
			instance = new DaoFactory();
		return instance;
	}

	private Transaction transaction;

	public DaoFactory() {
		session = HibernateUtil.getCurrentSession();
	}

	public void beginTransaction() {
		this.transaction = this.session.beginTransaction();
	}

	public void commit() {
		this.transaction.commit();
		this.transaction = null;
	}

	public boolean hasTransaction() {
		return this.transaction != null;
	}

	public void rollback() {
		this.transaction.rollback();
		this.transaction = null;
	}

	public void close() {
		this.session.close();
	}

	/*public static EntityDao getEntityDao(Class clazz) {
			return new EntityDao(clazz);
	}*
	
	
	/*public Object getDao(String classNameDao, Class clazz ) throws Exception {
		EntityDao dao = (EntityDao) ReflectionHandle.getInstanceof(classNameDao);
		dao.setBeanClass(clazz);
				
		return (Object) dao; 
	}*/
	
	public Object getModuloDao(String daoClass) throws Exception {
		Object dao = ReflectionHandle.getInstanceof(daoClass);
		return dao;
	}
}
