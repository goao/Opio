package softonPack.siscoaf.xml.processadorxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import softonPack.siscoaf.xml.geradorxml.IntConverter;
import softonPack.util.XMLHandle;

public class Resposta3098Wrapper {
	
	private String xml = "";
	
	Resposta3098LoteMensagem resposta3098LoteMensagem;
	
	public Resposta3098Wrapper( String xml ) {
		this.xml = xml;
		this.processarXML(this.xml);
		
		
//		List<Resposta3098OcorrenciaMensagem> list = resposta3098LoteMensagem.getOcorrencias();
//		
//		for(Resposta3098OcorrenciaMensagem foo : list){
//			List<Resposta3098EnquadramentoMensagem> _list = foo.getEnquadramentos();
//			
//			if(_list == null)
//				System.out.println("===========ocorrencia:" + foo.getNumeroOcorrencia() +
//						" NULL ENQUADRAMENTOS");
//			else {
//				System.out.println("==========ocorrencia:" + foo.getNumeroOcorrencia() +
//						" total enq: " + _list.size() );
//				
//				for(Resposta3098EnquadramentoMensagem x : _list){
//					System.out.println("enq: " + x.getCodigoEnquadramento());
//					
//				}
//			}
//		}

	}
	
	private void processarXML(String xml){
	 	
	 	XMLHandle controller = new XMLHandle();
	 	
	 	controller.registerConverter(new IntConverter());

	 	controller.insereAlias( "LOTE", Resposta3098LoteMensagem.class );
	 	controller.insereAliasField( "OCORRENCIAS", Resposta3098LoteMensagem.class, "ocorrencias" );
	 	
	 	controller.insereAlias( "OCORRENCIA", Resposta3098OcorrenciaMensagem.class );
		controller.insereAliasField( "NumOcorrencia", Resposta3098OcorrenciaMensagem.class, "numeroOcorrencia" );
		controller.insereAliasField( "CPFCNPJCom", Resposta3098OcorrenciaMensagem.class, "radicalCnpjCpf" );
		controller.insereAliasField( "DtInicio", Resposta3098OcorrenciaMensagem.class, "dataInicialEvento" );
		controller.insereAliasField( "DtFim", Resposta3098OcorrenciaMensagem.class, "dataFinalEvento" );
		controller.insereAliasField( "AgNum", Resposta3098OcorrenciaMensagem.class, "numeroAgencia" );
		controller.insereAliasField( "AgNome", Resposta3098OcorrenciaMensagem.class, "nomeAgencia" );
		controller.insereAliasField( "AgMun", Resposta3098OcorrenciaMensagem.class, "cidadeAgencia" );
		controller.insereAliasField( "AgUF", Resposta3098OcorrenciaMensagem.class, "estadoAgencia" );
		controller.insereAliasField( "Det", Resposta3098OcorrenciaMensagem.class, "detalhamento" );
		controller.insereAliasField( "VlCred", Resposta3098OcorrenciaMensagem.class, "valorCredito" );
		controller.insereAliasField( "VlDeb", Resposta3098OcorrenciaMensagem.class, "valorDebito" );
		controller.insereAliasField( "VlProv", Resposta3098OcorrenciaMensagem.class, "valorProvisionamento" );
		controller.insereAliasField( "VlProp", Resposta3098OcorrenciaMensagem.class, "valorProposto" );
		
		controller.insereAliasField( "ENQUADRAMENTOS", Resposta3098OcorrenciaMensagem.class, "enquadramentos" );
//		controller.insereAlias( "ENQUADRAMENTO", Resposta3098EnquadramentoMensagem.class );
//		controller.insereAliasField( "CodEnq", Resposta3098EnquadramentoMensagem.class, "codigoEnquadramento" );
		controller.insereAlias( "CodEnq", Resposta3098EnquadramentoMensagem.class);
		
		controller.aliasAttribute(Resposta3098EnquadramentoMensagem.class, "codigoEnquadramento", "CodEnq");
		
		controller.insereAliasField( "ENVOLVIDOS", Resposta3098OcorrenciaMensagem.class, "envolvidos" );
		controller.insereAlias( "ENVOLVIDO", RespostaEnvolvidoMensagem.class );
		controller.insereAliasField( "CPFCNPJEnv", RespostaEnvolvidoMensagem.class, "radicalCpfCnpj" );
		controller.insereAliasField( "NmEnv", RespostaEnvolvidoMensagem.class, "nomeEnvolvido" );
		controller.insereAliasField( "TpEnv", RespostaEnvolvidoMensagem.class, "tipoEnvolvimento" );
		controller.insereAliasField( "AgNumEnv", RespostaEnvolvidoMensagem.class, "codigoAgencia" );
		controller.insereAliasField( "AgNomeEnv", RespostaEnvolvidoMensagem.class, "nomeAgencia" );
		controller.insereAliasField( "NumConta", RespostaEnvolvidoMensagem.class, "codigoConta" );
		controller.insereAliasField( "DtAbConta", RespostaEnvolvidoMensagem.class, "dataAberturaConta" );
		controller.insereAliasField( "DtAtuaCad", RespostaEnvolvidoMensagem.class, "dataAtualizacaoCadastro" );
		controller.insereAliasField( "PObrigada", RespostaEnvolvidoMensagem.class, "flagPessoaObrigatoria" );
		controller.insereAliasField( "PEP", RespostaEnvolvidoMensagem.class, "flagPPE" );
		controller.insereAliasField( "ServPub", RespostaEnvolvidoMensagem.class, "tipoServidorPublico" );
	 	
		Resposta3098LoteMensagem respostaWrapper = (Resposta3098LoteMensagem) controller.fromXML( xml );
		
		
		
		this.tratarEnquadramentosXPTO(respostaWrapper, xml);

	 	this.resposta3098LoteMensagem = respostaWrapper;
	}
	
	
	
	private void tratarEnquadramentosXPTO(Resposta3098LoteMensagem respostaWrapper, String xml){
		xml = xml.toUpperCase();
		
		boolean continua = true;

		
		Map<String, String> mapa = new HashMap<String,String>();
		
		String nuXML = "";
		String rawXML = xml;
		String replaceXML = "";
		int i = 0; 
		while(continua){ 
	
			if(rawXML.indexOf("<OCORRENCIA>")  == -1 || rawXML.indexOf("</OCORRENCIA>") == -1 )
				break;
			
			replaceXML =rawXML.substring(rawXML.indexOf("<OCORRENCIA>"), (rawXML.indexOf("</OCORRENCIA>")));
			replaceXML = replaceXML.concat("</OCORRENCIA>");

			String ocorrencia = rawXML.substring(rawXML.indexOf("<NUMOCORRENCIA>"),rawXML.indexOf("</NUMOCORRENCIA>"));
			ocorrencia = ocorrencia.replaceAll("<NUMOCORRENCIA>", "");

			String enquadramentos = rawXML.substring(rawXML.indexOf("<ENQUADRAMENTOS>"),rawXML.indexOf("</ENQUADRAMENTOS>"));
//			System.out.println("enquadramento: " + enquadramentos);
			enquadramentos = enquadramentos.replaceAll("<ENQUADRAMENTOS>", "");
			enquadramentos = enquadramentos.replaceAll("</ENQUADRAMENTOS>", "");
			enquadramentos = enquadramentos.replaceAll("<CODENQ>", "|");
			enquadramentos = enquadramentos.replaceAll("</CODENQ>", "");

			
			mapa.put(ocorrencia, enquadramentos);
			
			rawXML = rawXML.replaceFirst(replaceXML, "");

		}
		
		for(String oc : mapa.keySet()){
			String enquadramentos = mapa.get(oc);
			Long codOcorrencia = new Long(oc);
			
			
			StringTokenizer toke = new StringTokenizer(enquadramentos,"|");

			for(Resposta3098OcorrenciaMensagem respostaOcorrencia : respostaWrapper.getOcorrencias()){
				
				if(respostaOcorrencia.getNumeroOcorrencia() != codOcorrencia.intValue())
					continue;
				
				List<Resposta3098EnquadramentoMensagem> listEnquadramentos = new ArrayList<Resposta3098EnquadramentoMensagem>();
				while(toke.hasMoreTokens()){
					Resposta3098EnquadramentoMensagem mensagem = new Resposta3098EnquadramentoMensagem();
					String t = toke.nextToken().trim();
					if(! t.equals("")){
//						System.out.println("nextToken: " + t );
						mensagem.setCodigoEnquadramento(new Long(t));
						listEnquadramentos.add(mensagem);
					} 
				}
				respostaOcorrencia.setEnquadramentos(listEnquadramentos);
			}
		}

	}
	
	
	
	private void tratarEnquadramentos(Resposta3098LoteMensagem respostaWrapper, String xml){
		xml = xml.toUpperCase();
		
		String enquadramentos = xml.substring(xml.indexOf("<ENQUADRAMENTOS>"),xml.indexOf("</ENQUADRAMENTOS>"));
		enquadramentos = enquadramentos.replaceAll("<ENQUADRAMENTOS>", "");
		enquadramentos = enquadramentos.replaceAll("<CODENQ>", "|");
		enquadramentos = enquadramentos.replaceAll("</CODENQ>", "");
	
//		System.out.println("xml : " + xml);
//		System.out.println("xml enquadramento: " + enquadramentos);
		StringTokenizer toke = new StringTokenizer(enquadramentos,"|");

		for(Resposta3098OcorrenciaMensagem respostaOcorrencia : respostaWrapper.getOcorrencias()){
			List<Resposta3098EnquadramentoMensagem> listEnquadramentos = new ArrayList<Resposta3098EnquadramentoMensagem>();
			while(toke.hasMoreTokens()){
				Resposta3098EnquadramentoMensagem mensagem = new Resposta3098EnquadramentoMensagem();
				String t = toke.nextToken().trim();
				if(! t.equals("")){
//					System.out.println("nextToken: " + t );
					mensagem.setCodigoEnquadramento(new Long(t));
					listEnquadramentos.add(mensagem);
				} 
			}
			respostaOcorrencia.setEnquadramentos(listEnquadramentos);
		}
	}
	
	public Resposta3098LoteMensagem getRespostaEnvioLoteMensagem() {
		return resposta3098LoteMensagem;
	}

	public void setRespostaEnvioLoteMensagem(
			Resposta3098LoteMensagem resposta3098LoteMensagem) {
		this.resposta3098LoteMensagem = resposta3098LoteMensagem;
	}
}