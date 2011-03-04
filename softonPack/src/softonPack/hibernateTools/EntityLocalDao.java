package softonPack.hibernateTools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;



public class EntityLocalDao<T>  extends EntityDao<T>{

	protected Session session;

	public EntityLocalDao(Class<T> classe, Session session) {
		super(classe);
		this.session = session;
	}

	@Override
	public Session getSession() {
		return this.session;
	}



}
