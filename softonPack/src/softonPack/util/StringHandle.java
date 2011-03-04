package softonPack.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHandle {

	
	/**
	 * Objetivo � receber um texto e formatar no padr�o
	 * da string aceita pelo Oracle 
	 * ex: string 123 e retorna '123'
	 * ou  string 123,225 e retorna '123','225' 
	 * @param texto 
	 * @param token utilizado pelo split
	 * @return
	 */
	public static String getFormatInSql(String texto, String token){

		String stringFormatado="";
		String stringSplit[] = texto.split(token);
		for (int i =0; i < stringSplit.length; i++){
			stringFormatado += "'" + stringSplit[i].trim() + "'";
			if (i < stringSplit.length -1){
				stringFormatado += ",";
			}
		}
		return stringFormatado;
	}

	public static boolean isLong(String valor) {
		if (valor.matches("[0-9]+(,[0-9]+)?"))
			return true;
		return false;
	}


	public static boolean isInteger(String valor) {
		if (valor.equals(null))
			return false;
		
		String expressaoRegex = "[0-9]";
		
		Pattern pattern = Pattern.compile(expressaoRegex); 
		Matcher matcher = pattern.matcher(valor);
		
        boolean found = false;
        while (matcher.find()) {
            found = true;
        }

		
		if(found)
			return true;
			
		return false;
	}


	public static boolean isNull(String valor) {
		boolean retorno = false;
		if (valor.equals(null))
			retorno = true;
	 	if (valor.trim().equals(""))
			retorno = true;
		return retorno;
	}

	public static boolean isTimestamp(String valor) {
		boolean retorno = softonPack.util.DateHandle.isStringComDataValida(valor);
		
		return retorno;
		
	}
	
	public static String getOsNumerosDaString(String texto) {
		String retorno = "";
		
		if(texto == null)
			return retorno;
		
		
		for (int i = 0; i < texto.length(); i++)
			if ( String.valueOf(texto.charAt(i)).matches("[0-9]") )
				retorno = retorno.concat(String.valueOf(texto.charAt(i)));
		
		return retorno;
	}

}
