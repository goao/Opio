package softonPack.hibernateTools;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;



public class EntityDao<T> {
	protected Class<T> classe;
	protected static final Session session;
	static {
		session = DaoFactory.getInstance().getSession();
	}
	
	public EntityDao(Class<T> classe) {
		this.classe = classe;
	}
	
	public EntityDao() {
	}	
	
	public void setBeanClass(Class clazz){
		this.classe = clazz;
	}
	

	public Session getSession() {
		return session;
	}

	public void adiciona(T u) {
		getSession().save(u);
	}

	public boolean contem(T u) {
		return getSession().contains(u);
	}

	public void remove(T u) {
		getSession().delete(u);
	}

	public void atualiza(T u) {
		getSession().merge(u);
	}


	@SuppressWarnings("unchecked")
	public List<T> listaTudo() {
		return getSession().createCriteria(this.classe).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> listaLike(String propriedade, String valor) {
		Criteria cri = session.createCriteria(this.classe);
		cri.add(Restrictions.ilike(propriedade, valor));
		
		List<T> lista = cri.list();
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	public T procura(Serializable id) {
		return (T) session.load(this.classe, id);
	}

	@SuppressWarnings("unchecked")
	public List<T> procuraLista(Long id, String strCode) {
		return getSession().createCriteria(this.classe).add(Expression.eq(strCode, id)).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> procuraLista(Map map) {
		return getSession().createCriteria(this.classe).add(Expression.allEq(map)).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> procuraLista(String propertyName, Object low, Object high) {
		return getSession().createCriteria(this.classe).add(Expression.between(propertyName, low, high)).list();
	}
}
