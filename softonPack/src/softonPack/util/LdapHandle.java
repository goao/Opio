package softonPack.util;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import softonPack.util.to.LdapTO;

import com.sun.jndi.ldap.LdapCtxFactory;

public class LdapHandle {

	private LdapTO ldapTO;

    public LdapHandle(LdapTO ldapTO) {
		super();
		this.ldapTO = ldapTO;
	}
	
	public NamingEnumeration<SearchResult> executarQuery() throws NamingException  {    

        DirContext ctx = new InitialDirContext(this.createEnv());
        SearchControls ctls = new SearchControls();
        //ctls.setSearchScope(SearchControls.OBJECT_SCOPE);
        ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        ctls.setReturningAttributes(new String[] { "*", "+" });
                       
        NamingEnumeration<SearchResult> result = ctx.search( this.ldapTO.getDominio(), this.ldapTO.getFilter(), ctls);
        
        ctx.close();
        return result;
	} 
	
	protected Hashtable<Object, Object> createEnv() {

    	Hashtable<Object, Object> env = new Hashtable<Object, Object>();

    	env.put(Context.INITIAL_CONTEXT_FACTORY, LdapCtxFactory.class.getCanonicalName());
        env.put(Context.PROVIDER_URL, this.ldapTO.getHostURL());
        env.put(Context.SECURITY_PRINCIPAL, this.ldapTO.getLogin());
        env.put(Context.SECURITY_CREDENTIALS, this.ldapTO.getSenha());
        env.put(Context.SECURITY_AUTHENTICATION, this.ldapTO.getTipoAutenticacao());

        return env;
    }
	
}
