package softonPack.siscoaf.xml.geradorxml;

import softonPack.util.XMLHandle;

public class RetificacaoWrapper {
	
	private RetificacaoMensagem retificacaoObj;
	private XMLHandle controller;
	private String xml = "";

	public RetificacaoWrapper( RetificacaoMensagem retificacaoMensagem ){
		this.retificacaoObj = retificacaoMensagem;
		this.controller = new XMLHandle();
		this.xml = gerarXMLRetificacao();
	}
	
	public String getXml() {
		return this.xml;
	}
	
	private String gerarXMLRetificacao(){
	 
	 	this.controller.insereAlias( "LOTERETIFICACAO", RetificacaoMensagem.class );
	 	this.controller.insereAlias( "OCORRENCIA", OcorrenciaRetificacao.class );
	 	this.controller.insereAlias( "ENVOLVIDO", EnvolvidoRetificacao.class);
	 	
	 	this.controller.omitField(RetificacaoMensagem.class,"idOcorrencias");
	 	this.controller.insereAliasField( "OCORRENCIAS", RetificacaoMensagem.class, "listaOcorrenciaRetificacao" );
	 	this.controller.insereAliasField( "ENQUADRAMENTOS", OcorrenciaRetificacao.class, "enquadramentos" );
	 	this.controller.insereAliasField( "ENVOLVIDOS", OcorrenciaRetificacao.class,"listaOcorrenciaRetificacao");
	 	
	 	this.controller.insereAliasField( "NumOcorrencia", OcorrenciaRetificacao.class, "numeroOcorrencia" );
	 	this.controller.insereAliasField( "NumeroCOAF", OcorrenciaRetificacao.class, "numeroCoaf" );
	 	this.controller.insereAliasField( "CPFCNPJCom", OcorrenciaRetificacao.class, "radicalCnpjCpf" );
	 	this.controller.insereAliasField( "DtInicio", OcorrenciaRetificacao.class, "dataInicialEvento" );
	 	this.controller.insereAliasField( "DtFim", OcorrenciaRetificacao.class, "dataFinalEvento" );
	 	this.controller.insereAliasField( "AgNum", OcorrenciaRetificacao.class, "numeroAgencia" );
	 	this.controller.insereAliasField( "AgNome", OcorrenciaRetificacao.class, "nomeAgencia" );
	 	this.controller.insereAliasField( "AgMun", OcorrenciaRetificacao.class, "cidadeAgencia" );
	 	this.controller.insereAliasField( "AgUF", OcorrenciaRetificacao.class, "estadoAgencia" );
	 	this.controller.insereAliasField( "Det", OcorrenciaRetificacao.class, "detalhamento" );
	 	this.controller.insereAliasField( "VlCred", OcorrenciaRetificacao.class, "valorCredito" );
	 	this.controller.insereAliasField( "VlDeb", OcorrenciaRetificacao.class, "valorDebito" );
	 	this.controller.insereAliasField( "VlProv", OcorrenciaRetificacao.class, "valorProvisionamento" );
	 	this.controller.insereAliasField( "VlProp", OcorrenciaRetificacao.class, "valorProposto" );
	
	 	this.controller.insereAliasField( "CPFCNPJEnv", EnvolvidoRetificacao.class, "radicalCpfCnpj" );
	 	this.controller.insereAliasField( "NmEnv", EnvolvidoRetificacao.class, "nomeEnvolvido" );
	 	this.controller.insereAliasField( "TpEnv", EnvolvidoRetificacao.class, "tipoEnvolvimento" );
	 	this.controller.insereAliasField( "AgNumEnv", EnvolvidoRetificacao.class, "codigoAgencia" );
	 	this.controller.insereAliasField( "AgNomeEnv", EnvolvidoRetificacao.class, "nomeAgencia" );
	 	this.controller.insereAliasField( "NumConta", EnvolvidoRetificacao.class, "codigoConta" );
	 	this.controller.insereAliasField( "DtAbConta", EnvolvidoRetificacao.class, "dataAberturaConta" );
	 	this.controller.insereAliasField( "DtAtuaCad", EnvolvidoRetificacao.class, "dataAtualizacaoCadastro" );
	 	this.controller.insereAliasField( "PObrigada", EnvolvidoRetificacao.class, "pessoaObrigatoria" );
	 	this.controller.insereAliasField( "PEP", EnvolvidoRetificacao.class, "pep" );
	 	this.controller.insereAliasField( "ServPub", EnvolvidoRetificacao.class, "tipoServidorPublico" );
		
	 	this.xml = this.controller.toXML(this.retificacaoObj);
		
		this.xml = this.xml.replaceFirst( "<OCORRENCIAS>",
				"<OCORRENCIAS ID=\"" + this.retificacaoObj.getIdOcorrencias()+ "\">" );

		xml = this.xml.replaceAll("<string>", "<CodEnq>");
		xml = this.xml.replaceAll("</string>", "</CodEnq>");
		xml = this.xml.replaceAll("<listaEnvolvidoRetificacao>", "<ENVOLVIDOS>");
		xml = this.xml.replaceAll("</listaEnvolvidoRetificacao>", "</ENVOLVIDOS>");

		return this.xml;
	 }

	public RetificacaoMensagem getRetificacaoObj() {
		return retificacaoObj;
	}
	
	
}