package softonPack.util;



public class NumeroInscricaoHandle {

	public static String obterNumInscricaoFormatado(String radical, String filial, 
			String digito) throws Exception {
		return obterNumInscricaoFormatado(radical, filial, digito, true);
	}

	
	@SuppressWarnings("null")
	public static String obterNumInscricaoFormatado(String radical, String filial, 
			String digito, boolean conferir) throws Exception {
		String numeroInscricao = "";
		
		if ((radical == null) || (digito == null)) 
			throw new Exception("NumeroInscricaoHandle.erro: radical e digito n�o podem ser nulos!");
			
		digito = "00" + digito;
		radical = "000000000" + radical;
		filial = "0000" + filial;
		
		if (filial != null && Integer.valueOf(filial.trim()) > 0 ){
			// CNPJ			
			numeroInscricao = numeroInscricao.concat(radical.substring(radical.length() - 9)); 
			numeroInscricao = numeroInscricao.concat("/"); 
			numeroInscricao = numeroInscricao.concat(filial.substring(filial.length() - 4));
			numeroInscricao = numeroInscricao.concat("-"); 
			numeroInscricao = numeroInscricao.concat(digito.substring(digito.length() - 2));
			
			if (conferir && !isCNPJValido(numeroInscricao))
				throw new Exception("NumeroInscricaoHandle.erro: dados de CNPJ inv�lidos!");
		} else {
			// CPF
			numeroInscricao = numeroInscricao.concat(radical.substring(radical.length() - 9)); 
			numeroInscricao = numeroInscricao.concat("-"); 
			numeroInscricao = numeroInscricao.concat(digito.substring(digito.length() - 2));

			if (conferir && !isCPFValido(numeroInscricao))
				throw new Exception("NumeroInscricaoHandle.erro: dados de CPF inv�lidos!");;
		}
		return numeroInscricao;
	}


	public static boolean isCPFValido(String numeroInscricao) {
		return isCpfCnpjValido(StringHandle.getOsNumerosDaString(numeroInscricao), "F");
		
	}

	public static boolean isCNPJValido(String numeroInscricao) {
		return isCpfCnpjValido(StringHandle.getOsNumerosDaString(numeroInscricao), "J");		
	}
	
    private static boolean isCpfCnpjValido(String numero, String tipoPessoa) {
    	boolean retorno = false;
        String radical;
        String digito;
        int tam = numero.length();
        
        if (tam > 2) {
	        digito = numero.substring((tam-2), tam);
	    	radical = numero.substring(0, (tam-2));

	        // Verifica se � um CPF v�lido
	    	if (tipoPessoa.equals("F") && digito.equals(calcularDigitoMod11(radical, 2, 11)))
	        	retorno = true;

	        // Verifica se � um CNPJ v�lido
	    	if (tipoPessoa.equals("J") && digito.equals(calcularDigitoMod11(radical, 2, 9)))
	        	retorno = true;
        }
        
        return retorno;
    }

    public static String calcularDigitoMod11(String numero, int tamDigito, int limite) {
        int dx;
        int ax;
        int resto;
        
        String digito = "";
        
        for (int z = 0; z < tamDigito; z++) {
     	   dx = 0;
     	   ax = 2;
 	       for (int i = numero.length(); i > 0; i--) {
 	           dx += ax * Integer.parseInt(numero.substring(i - 1, i));
 	           
 	           if (ax == limite) {
 	        	   ax = 2;
 	           } else {
 	        	   ax += 1;
 	           }
 	       }
 	    
 	       resto = dx % 11;
 	       if (resto < 2) {
 	           dx = 0;
 	       } else {
 	           dx = 11 - resto;
 	       }

 	       numero = numero + dx;
 	       digito = digito + dx;
        }
    
        return digito;
     }
    
    
    /**
     * Obt�m o valor do radical do n�mero de inscri��o. Desde que existam d�gito verificador de 2 d�gitos
     * e filial nula ou com 4 d�gitos. Esse m�todo ignora/limpa toda formata��o sem dificuldades.
     * 
     * OBS: o m�todo discerne se � CPF ou CNPJ atrav�s de compara��o com o d�gito verificador
     * 
     * @param numero n�mero de inscri��o.
     * @param tipo tipo de pessoa.
     * @return radical do n�mero da inscri��o.
     */
    public static String getValorRadical(String numeroInscricao) {
    	String retorno = null;
               
        numeroInscricao = StringHandle.getOsNumerosDaString(numeroInscricao);
        int tam = numeroInscricao.length();
        
        // Verifica se � um CNPJ
        if (isCNPJValido(numeroInscricao) && tam > 6) {
            retorno = numeroInscricao.substring(0, (tam-6));
            
            if(retorno.length() < 8){
            	int dif = 8 - retorno.length();
            	String cnpj = "";
            	for(int i = 0; i<dif;i++){
            		cnpj  = cnpj + "0";
            	}
            	retorno = cnpj + retorno;
            }
        
        // sen�o, � um CPF 
        } else if (isCPFValido(numeroInscricao) && tam > 2) {
        	retorno = numeroInscricao.substring(0, (tam-2));
        } else {
        	return null;
        }
        
        return retorno;
    }
    
    /**
     * Obt�m o valor da filial do n�mero de inscri��o. Desde que existam d�gito verificador de 2 d�gitos
     * e filial nula ou com 4 d�gitos. Esse m�todo ignora/limpa toda formata��o sem dificuldades.
     * 
     * OBS: o m�todo discerne se � CPF ou CNPJ atrav�s de compara��o com o d�gito verificador
     * 
     * @param numero n�mero de inscri��o.
     * @param tipo tipo de pessoa.
     * @return filial do n�mero da inscri��o.
     */
    public static String getValorFilial(String numeroInscricao) {
    	String retorno = null;
              
        numeroInscricao = StringHandle.getOsNumerosDaString(numeroInscricao);
        int tam = numeroInscricao.length();
        
        // Verifica se � um CNPJ
        if (isCNPJValido(numeroInscricao) && tam > 6) {
        	retorno = numeroInscricao.substring((tam-6), (tam-2));
        } else if (!isCPFValido(numeroInscricao)){
        	return null;
        }
        // sen�o, � um CPF, filial = 0 
        
        return retorno;
    }

	public static String calcularDigito(String radicalCpfCnpj, String filialCnpj) {
		String retorno = null;
		
		if (filialCnpj == null){
			retorno = calcularDigitoMod11(radicalCpfCnpj, 2, 11);
		} else {
			String filial = "00000" + filialCnpj;
			filial = filial.substring(filial.length()-4);
			radicalCpfCnpj = radicalCpfCnpj.concat(filial);
			retorno = calcularDigitoMod11(radicalCpfCnpj, 2, 9);
		}
		
		return retorno;
	}
    
}
