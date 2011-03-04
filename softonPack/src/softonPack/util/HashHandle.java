package softonPack.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashHandle {

	
	/**
	 * Recebe uma String e retorna o hash MD5 dessa String 
	 * @param frase 
	 * @return String
	 */
	public static String getHashMD5(String frase){

		String retorno = null;
			
		try {
			
		    MessageDigest md = MessageDigest.getInstance("MD5");   
		    BigInteger hash = new BigInteger(1, md.digest(frase.getBytes()));   
		    retorno = hash.toString(16);   
		    if (retorno.length() %2 != 0)   
		    	retorno = "0".concat(retorno);   

		} catch (NoSuchAlgorithmException e) {
			retorno = frase;
		}
		return retorno;
	}
	
}
