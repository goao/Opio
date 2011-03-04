package softonPack.criptografia;

import org.apache.log4j.Logger;



public class TesteCriptografia {
	private static Logger log = Logger.getLogger(TesteCriptografia.class);
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Chave secreta utilizada pela criptografia como parte da senha
		
		String chaveSecreta = "SISPLD - Softon Sistemas Inteligentes";
		
		
		String senha = "sispld";
		
		Criptografia cript = new Criptografia(chaveSecreta);
		
		String senhaCriptografada =cript.encriptografar(senha);
		
		log.info("Senha original: " + senha);
		log.info("Senha criptografada: " + senhaCriptografada);
		
		log.info("");
		
		log.info("senha descriptografada: " + cript.descriptografar(senhaCriptografada));
		

	}

}
