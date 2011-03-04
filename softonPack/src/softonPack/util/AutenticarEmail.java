package softonPack.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class AutenticarEmail extends Authenticator {
	
	private String usuario;
	
	private String senha;
	
	public AutenticarEmail(String usuario, String senha) {
		this.usuario = usuario;
		this.senha = senha;
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(usuario, senha);
	}

}
