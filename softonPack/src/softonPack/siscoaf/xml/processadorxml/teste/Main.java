package softonPack.siscoaf.xml.processadorxml.teste;

import java.io.UnsupportedEncodingException;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class Main {
	private static Logger log = Logger.getLogger(Main.class);
	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
			String teste = "<TESTE><EVENTOS><COD>1</COD><COD>2</COD></EVENTOS></TESTE>";
			
			String certo = teste.substring(teste.indexOf("<EVENTOS>"),teste.indexOf("</EVENTOS>"));
			
			certo = certo.replaceAll("<EVENTOS>", "");
			certo = certo.replaceAll("<COD>", "|");
			certo = certo.replaceAll("</COD>", "");
			
			log.info(certo);
			
			StringTokenizer toke = new StringTokenizer(certo,"|");
			
			
			while(toke.hasMoreTokens()){
				log.info(toke.nextToken());	
			}
			
			

	}

}
