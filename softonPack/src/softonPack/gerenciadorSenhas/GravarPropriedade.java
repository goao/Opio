package softonPack.gerenciadorSenhas;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import softonPack.criptografia.Criptografia;
import softonPack.util.PropertieHandle;

public class GravarPropriedade {
	private static Logger log = Logger.getLogger(GravarPropriedade.class);
	/**
	 * @param args
	 * @throws DTECBaseException 
	 * @throws DTECBaseException 
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		gravarSenha(args[0]);

	}


	public static void gravarSenha(String pathAndFileName ){
		String usuario = "";
		String senha = "";

		try {
			BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Digite o sistema:");
			String sistema = userIn.readLine().toLowerCase();

			PropertieHandle.savePropertyFile(pathAndFileName, "sistema", sistema);

			System.out.print("Digite o usu�rio do Banco de Dados:");
			usuario = userIn.readLine().toLowerCase();

			PropertieHandle.savePropertyFile(pathAndFileName, "usuario", usuario);


			String password = PasswordField.readPassword("Digite a senha do Banco de Dados: ");
			senha = password.toLowerCase();

			Criptografia crypt = new Criptografia(sistema);

			PropertieHandle.savePropertyFile(pathAndFileName, "usuario.senha", crypt.encriptografar(senha));

			log.info("Senha gerada com sucesso!!!");

		} catch (Exception e) {
			log.info("N�o foi possivel ler o arquivo.");
			System.exit(0);
		}	

	}
}
