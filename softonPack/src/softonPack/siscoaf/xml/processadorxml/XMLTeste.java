package softonPack.siscoaf.xml.processadorxml;

import org.apache.log4j.Logger;

import softonPack.util.DateHandle;

public class XMLTeste {
	private static Logger log = Logger.getLogger(XMLTeste.class);
	public static StringBuilder sb = new StringBuilder();
	
	public static DateHandle dateHandle = new DateHandle();

	/**
	 * @param args
	 */
	public static void main( String[] args ) {
		//testarRecebimento();
		//testarRespostaCancelamento();
		//testarEnvio();
	}

	public static void testarEnvio(){
		
		softonPack.siscoaf.xml.geradorxml.XMLTeste xmlEnvio = new softonPack.siscoaf.xml.geradorxml.XMLTeste();
		String xml = xmlEnvio.geraXMLEnvio();
	
		Resposta3098Wrapper respostaEnvioWrapper = new Resposta3098Wrapper(xml);
		
		for(Resposta3098OcorrenciaMensagem ocorrencias: respostaEnvioWrapper.getRespostaEnvioLoteMensagem().getOcorrencias()){
			log.info(ocorrencias);	
		}
	}
	
	public static void testarRecebimento(){
		sb.append(" <RESPOSTA>" );
		sb.append(" <MENSAGENS>" );
		sb.append(" <MENSAGEM>" );
		sb.append(" <NUMEROORIGEM>12</NUMEROORIGEM>" );
		sb.append(" <NUMEROCOAF>921377</NUMEROCOAF>" );
		sb.append(" <AUTENTICACAO>9cd040bf2f63565e10d343c08fb03007</AUTENTICACAO>" );
		sb.append(" </MENSAGEM>" );
		sb.append(" <MENSAGEM>" );
		sb.append(" <NUMEROORIGEM>13</NUMEROORIGEM>" );
		sb.append(" <NUMEROCOAF>921378</NUMEROCOAF>" );
		sb.append(" <AUTENTICACAO>828dc3a09af24249ca5e0c3392c68ab4</AUTENTICACAO>" );
		sb.append(" </MENSAGEM>" );
		sb.append(" </MENSAGENS>" );
		sb.append(" </RESPOSTA>" );
		
		ReciboWrapper wrapper = new ReciboWrapper(sb.toString());
		
		for (int i = 0; i < wrapper.getListaMensagens().size(); i++) {
			log.info(wrapper.getListaMensagens().get(i).getAutenticacao());
		}
	}
	
	/**
	 * Metodo de teste para o xml de resposta envio
	 */
	public static void testarRespostaCancelamento(){
		StringBuffer sb2 = new StringBuffer();
		sb2.append( " <?xml version='1.0' encoding=\"iso-8859-1\"?> " );
		sb.append( " <RESPOSTA> " );
		sb.append( "    <STATUS>2</STATUS> " );
		sb.append( " <MENSAGENS> " );
		sb.append( " <MENSAGEM> " );
		sb.append( "    <COMUNICACAOORIGEM>2</COMUNICACAOORIGEM> " );
		sb.append( "    <EVENTO>11</EVENTO> " );
		sb.append( " </MENSAGEM> " );
		sb.append( " <MENSAGEM> " );
		sb.append( "    <COMUNICACAOORIGEM>12</COMUNICACAOORIGEM> " );
		sb.append( "    <EVENTO>3</EVENTO> " );
		sb.append( " </MENSAGEM> " );
		sb.append( " </MENSAGENS> " );
		sb.append( " </RESPOSTA> " );
		
		RespostaCancelamentoWrapper respostaWrapper = new RespostaCancelamentoWrapper(sb.toString());

		int j = 1;
		for (int i = 0; i < respostaWrapper.getListaRespostaCancelamentoMensagem().size(); i++) {
			log.info("####################################");
			log.info("Status: " + respostaWrapper.getStatus());
			log.info("Comunica��o origem: " + respostaWrapper.getListaRespostaCancelamentoMensagem().get(i).getComunicacaoOrigem());
			log.info("Erro: " + respostaWrapper.getListaRespostaCancelamentoMensagem().get(i).getEvento());
			j++;
		}
	}

}