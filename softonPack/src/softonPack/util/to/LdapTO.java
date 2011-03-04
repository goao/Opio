package softonPack.util.to;

public class LdapTO {
	
	private String tipoAutenticacao;
	private String hostURL;
	private String login;
	private String senha;
	private String filter;
	private String dominio;

	public LdapTO() {
		super();
	}

	public LdapTO(String dominio, String filter, String hostURL, String login,
										String senha, String tipoAutenticacao) {
		super();
		this.dominio = dominio;
		this.filter = filter;
		this.hostURL = hostURL;
		this.login = login;
		this.senha = senha;
		this.tipoAutenticacao = tipoAutenticacao;
	}
	
	public String getTipoAutenticacao() {
		return tipoAutenticacao;
	}
	public void setTipoAutenticacao(String tipoAutenticacao) {
		this.tipoAutenticacao = tipoAutenticacao;
	}
	public String getHostURL() {
		return hostURL;
	}
	public void setHostURL(String hostURL) {
		this.hostURL = hostURL;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
	}
	public String getDominio() {
		return dominio;
	}
	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
}
