package softonPack.travaSistema;

import org.apache.log4j.Logger;

public class TesteTravaSistema {
	
	private static Logger log = Logger.getLogger(TesteTravaSistema.class);
	public static void main(String[] args)  {
		
		
		try {
			// S� processa uma inst�ncia do sistema
			TravaSistema.travarArquivoSistema("../CNTL/","aa");
			if (!TravaSistema.isLocked()){
				log.info("Sistema j� est� em execu��o.");
				System.exit(0);
			}else
				log.info("Travado");
			
			TravaSistema.travarArquivoSistema("../CNTL/","aa");
			if (!TravaSistema.isLocked()){
				log.info("Sistema j� est� em execu��o.");
				System.exit(0);
			}
			
			TravaSistema.liberarArquivoSistema();
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}
