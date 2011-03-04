package softonPack.siscoaf.xml.geradorxml;

import softonPack.util.XMLHandle;

public class EnvioWrapper {
	private EnvioLoteMensagem envioObj;
	private String xml;
	private XMLHandle controller;
	
	public EnvioWrapper( EnvioLoteMensagem lote ) {
		this.controller = new XMLHandle();
//		controller.registerConverter(new NullConverter());

		this.envioObj = lote;
		this.xml = retornaXmlRetorno();
	}
	
	public String getXml() {
		return xml;
	}

	private String retornaXmlRetorno(){
		
		controller.insereAlias( "LOTE", EnvioLoteMensagem.class );
		controller.insereAlias( "OCORRENCIA", EnvioOcorrenciaMensagem.class );
		controller.insereAlias( "ENVOLVIDO", EnvioEnvolvidoMensagem.class );
		
		controller.insereAliasField( "ENQUADRAMENTOS", EnvioOcorrenciaMensagem.class, "enquadramentos" );
		
		controller.omitField(EnvioLoteMensagem.class,"idOcorrencias");
		controller.insereAliasField( "OCORRENCIAS", EnvioLoteMensagem.class, "ocorrencias" );
		controller.insereAliasField( "ENVOLVIDO", EnvioOcorrenciaMensagem.class, "envolvido" );
		controller.insereAliasField( "ENVOLVIDOS", EnvioOcorrenciaMensagem.class, "envolvidos" );
		
		controller.insereAliasField( "NumOcorrencia", EnvioOcorrenciaMensagem.class, "numeroOcorrencia" );
		controller.insereAliasField( "CPFCNPJCom", EnvioOcorrenciaMensagem.class, "radicalCnpjCpf" );
		controller.insereAliasField( "DtInicio", EnvioOcorrenciaMensagem.class, "dataInicialEvento" );
		controller.insereAliasField( "DtFim", EnvioOcorrenciaMensagem.class, "dataFinalEvento" );
		controller.insereAliasField( "AgNum", EnvioOcorrenciaMensagem.class, "numeroAgencia" );
		controller.insereAliasField( "AgNome", EnvioOcorrenciaMensagem.class, "nomeAgencia" );
		controller.insereAliasField( "AgMun", EnvioOcorrenciaMensagem.class, "cidadeAgencia" );
		controller.insereAliasField( "AgUF", EnvioOcorrenciaMensagem.class, "estadoAgencia" );
		controller.insereAliasField( "Det", EnvioOcorrenciaMensagem.class, "detalhamento" );
		controller.insereAliasField( "VlCred", EnvioOcorrenciaMensagem.class, "valorCredito" );
		controller.insereAliasField( "VlDeb", EnvioOcorrenciaMensagem.class, "valorDebito" );
		controller.insereAliasField( "VlProv", EnvioOcorrenciaMensagem.class, "valorProvisionamento" );
		controller.insereAliasField( "VlProp", EnvioOcorrenciaMensagem.class, "valorProposto" );
		
		

		controller.insereAliasField( "CPFCNPJEnv", EnvioEnvolvidoMensagem.class, "radicalCpfCnpj" );
		controller.insereAliasField( "NmEnv", EnvioEnvolvidoMensagem.class, "nomeEnvolvido" );
		controller.insereAliasField( "TpEnv", EnvioEnvolvidoMensagem.class, "tipoEnvolvimento" );
		controller.insereAliasField( "AgNumEnv", EnvioEnvolvidoMensagem.class, "codigoAgencia" );
		controller.insereAliasField( "AgNomeEnv", EnvioEnvolvidoMensagem.class, "nomeAgencia" );
		controller.insereAliasField( "NumConta", EnvioEnvolvidoMensagem.class, "codigoConta" );
		controller.insereAliasField( "DtAbConta", EnvioEnvolvidoMensagem.class, "dataAberturaConta" );
		controller.insereAliasField( "DtAtuaCad", EnvioEnvolvidoMensagem.class, "dataAtualizacaoCadastro" );
		controller.insereAliasField( "PObrigada", EnvioEnvolvidoMensagem.class, "flagPessoaObrigatoria" );
		controller.insereAliasField( "PEP", EnvioEnvolvidoMensagem.class, "flagPPE" );
		controller.insereAliasField( "ServPub", EnvioEnvolvidoMensagem.class, "tipoServidorPublico" );
		
		
		controller.insereAliasType( "OCORRENCIA", EnvioOcorrenciaMensagem.class );
		controller.insereAliasField( "listaEnvolvidos", EnvioLoteMensagem.class, "ENVOLVIDOS" );
		controller.insereAliasField( "listaEnquadramentos", EnvioLoteMensagem.class, "" );
		
		this.xml = this.controller.toXML( envioObj );
		
		xml = this.xml.replaceFirst( "<OCORRENCIAS>",
				"<OCORRENCIAS ID=\"" + this.envioObj.getIdOcorrencias()+ "\">" );
		
		xml = this.xml.replaceAll("<string>", "<CodEnq>");
		xml = this.xml.replaceAll("</string>", "</CodEnq>");
		
		return xml;
	}

	public EnvioLoteMensagem getEnvioObj() {
		return envioObj;
	}
	
	
}
