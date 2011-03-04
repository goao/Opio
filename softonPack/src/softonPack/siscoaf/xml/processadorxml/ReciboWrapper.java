package softonPack.siscoaf.xml.processadorxml;

import java.util.ArrayList;
import java.util.List;

import softonPack.siscoaf.xml.geradorxml.IntConverter;
import softonPack.util.XMLHandle;

public class ReciboWrapper {
	
	private String xml;
	private List< ReciboMensagem > listaMensagens = new ArrayList< ReciboMensagem >();
	private Integer status;
	
	public ReciboWrapper( String xml ){
		this.xml = xml;
		this.processarXML(this.xml);
	}

	public List< ReciboMensagem > getListaMensagens() {
		return listaMensagens;
	}

	public void setListaMensagens( List< ReciboMensagem > listaMensagens ) {
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

	 	controller.insereAlias( "RESPOSTA", ReciboWrapper.class );
		controller.insereAlias( "MENSAGEM", ReciboMensagem.class );
			
		controller.insereAliasField( "NUMEROORIGEM", ReciboMensagem.class, "numeroOrigem" );
		controller.insereAliasField( "NUMEROCOAF", ReciboMensagem.class, "numeroCoaf" );
		controller.insereAliasField( "AUTENTICACAO", ReciboMensagem.class, "autenticacao" );
		controller.insereAliasField( "VALOR", ReciboMensagem.class, "valor" );
		controller.insereAliasField( "MENSAGENS", ReciboWrapper.class, "listaMensagens" );
			
			
		ReciboWrapper reciboWrapper = ( ReciboWrapper ) controller.fromXML( xml );
		this.listaMensagens = reciboWrapper.listaMensagens;
		this.status = reciboWrapper.status;

	 }
}
