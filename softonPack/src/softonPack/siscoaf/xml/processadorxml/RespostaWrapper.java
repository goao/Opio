package softonPack.siscoaf.xml.processadorxml;

import java.util.ArrayList;
import java.util.List;

import softonPack.siscoaf.xml.geradorxml.IntConverter;
import softonPack.util.XMLHandle;

public class RespostaWrapper {
	
	private String xml;
	private List< RespostaMensagem > listaMensagens = new ArrayList< RespostaMensagem >();
	private Integer status;
	
	public RespostaWrapper( String xml ) {
		this.xml = xml;
		this.processarXML(this.xml);
	}
	
	public List<RespostaMensagem> getListaMensagens() {
		return listaMensagens;
	}

	public void setListaMensagens(List<RespostaMensagem> listaMensagens) {
		this.listaMensagens = listaMensagens;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private void processarXML(String xml){
	 	
	 	XMLHandle controller = new XMLHandle();
	 	
	 	controller.registerConverter(new IntConverter());

	 	controller.insereAlias( "RESPOSTA", RespostaWrapper.class );
		controller.insereAlias( "MENSAGEM", RespostaMensagem.class );
					
		controller.insereAliasField( "COMUNICACAOORIGEM", RespostaMensagem.class, "comunicacaoOrigem" );
		controller.insereAliasField( "ERRO", RespostaMensagem.class, "erro" );
		controller.insereAliasField( "MENSAGENS", RespostaWrapper.class, "listaMensagens" );
		controller.insereAliasField( "STATUS", RespostaWrapper.class, "status" );
			
		RespostaWrapper respostaWrapper = ( RespostaWrapper ) controller.fromXML( xml );
	 	this.status = respostaWrapper.getStatus();
	 	this.listaMensagens = respostaWrapper.getListaMensagens();

	}
}
