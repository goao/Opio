package softonPack.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * 
 * @author hugosilva
 * 
 * Classe respons�vel pelo envio de e-mails...
 *
 */
public class EmailHandle {
	private static Logger log = Logger.getLogger(EmailHandle.class);
	private static Properties props = new Properties();

	// Passamos no momento da cria��o da inst�ncia da classe o caminho do arquivo de propriedades que cont�m os dados para envio
	public EmailHandle(String propertiesFile) {
		File file = new File(propertiesFile); 
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			//l� os dados que est�o no arquivo
			props.load(fis);  
			fis.close();
		}
		catch (IOException ex) {
			log.info(ex.getMessage());
			ex.printStackTrace();
		} 
	}

	// Passamos no momento da cria��o da inst�ncia da classe o properties j� populado
	public EmailHandle(Properties propertiesFile) {
		props = propertiesFile;  
	}
	
	/**
	 * 
	 * @return Instancia da classe Properties 
	 */
	public static Properties getProps() {
		return props;
	}

	/**
	 * M�todo respons�vel pelo envio do(s) email(s)
	 * @param para - Lista de e-mails dos Destinat�rios
	 * @param assunto - Subject do e-mail a ser enviado
	 * @param mensagem - Corpo da mensagem
	 * @throws MessagingException
	 * @throws InterruptedException 
	 */
	public void postMail(List<String> para, String assunto, String mensagem) throws MessagingException, InterruptedException
	{
		final boolean debug = false;

		// Pegando a Sess�o Default e passando as propriedades para ela...
		Session session = Session.getInstance(props, null);

		// Caso tenhamos autentica��o no servidor de e-mails, setando os atributos corretos no properties, criamos
		// uma nova Sess�o com a classe "AutenticarEmail" passando o usu�rio e senha...
		if(props.getProperty("mail.user") != null || !props.getProperty("mail.user").equals("")) {
			session = Session.getInstance(props, 
					new AutenticarEmail(props.getProperty("mail.user"), props.getProperty("mail.password")));
		}

		session.setDebug(debug);

		// Criando a mensagem
		Message msg = new MimeMessage(session);

		// Setando o Remetente do(s) email(s)...
		InternetAddress addressFrom = new InternetAddress(props.getProperty("mail.from"));	    
		msg.setFrom(addressFrom);

		// Setando o assunto do email
		msg.setSubject(assunto);

		// Aqui temos uma implementa��o para verificar se a lista de Destinat�rios cont�m apenas um ou mais
		// enviando de acordo com o resultado...

		// Setando o corpo da mensagem.
		msg.setText(mensagem);

		int count = 0;
		Address[] addrs = new InternetAddress[para.size()];

		// Le cada endereco de email e seta no array de Address instanciado anteriormente...
		for(String email : para) {
			addrs[count] = new InternetAddress(email);
			count++;
		}
		msg.setRecipients(Message.RecipientType.TO, addrs);

		Transport t = session.getTransport("smtp");   
		t.connect(props.getProperty("mail.smtp.host"), props.getProperty("mail.user"), props.getProperty("mail.password"));   

		t.sendMessage(msg, addrs); 
		Thread.sleep(10);
		log.info("Emails enviados com sucesso");

	}
	
	/**
	 * M�todo respons�vel pelo envio do(s) email(s) no Lotus Notes
	 * @param para - Lista de e-mails dos Destinat�rios
	 * @param assunto - Subject do e-mail a ser enviado
	 * @param mensagem - Corpo da mensagem
	 * @throws MessagingException
	 * @throws InterruptedException 
	 */
	public void postLotusNotesMail(List<String> para, String assunto, String mensagem) throws MessagingException, InterruptedException
	{
		final boolean debug = false;

		// Pegando a Sess�o Default e passando as propriedades para ela...
		Session session = Session.getInstance(props, null);
		session.setDebug(debug);

		// Criando a mensagem
		Message msg = new MimeMessage(session);

		// Setando o Remetente do(s) email(s)...
		InternetAddress addressFrom = new InternetAddress(props.getProperty("mail.from"));	    
		msg.setFrom(addressFrom);

		// Setando o assunto do email
		msg.setSubject(assunto);

		// Aqui temos uma implementa��o para verificar se a lista de Destinat�rios cont�m apenas um ou mais
		// enviando de acordo com o resultado...

		// Setando o corpo da mensagem.
		msg.setText(mensagem);

		int count = 0;
		Address[] addrs = new InternetAddress[para.size()];

		// Le cada endereco de email e seta no array de Address instanciado anteriormente...
		for(String email : para) {
			addrs[count] = new InternetAddress(email);
			count++;
		}
		msg.setRecipients(Message.RecipientType.TO, addrs);

		Transport.send(msg);   
		log.info("Emails enviados com sucesso");

	}

}
