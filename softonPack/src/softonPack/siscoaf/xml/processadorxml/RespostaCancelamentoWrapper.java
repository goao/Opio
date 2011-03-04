package softonPack.siscoaf.xml.processadorxml;

import java.util.ArrayList;
import java.util.List;

import softonPack.util.XMLHandle;

public class RespostaCancelamentoWrapper{
	
	private String xml;
	private Integer status;
	private List<RespostaCancelamentoMensagem> listaRespostaCancelamentoMensagem = new ArrayList<RespostaCancelamentoMensagem>();
	
	public RespostaCancelamentoWrapper( String xml ){
		this.xml = xml;
		this.processarXML(this.xml);
	}

	public List<RespostaCancelamentoMensagem> getListaRespostaCancelamentoMensagem() {
		return listaRespostaCancelamentoMensagem;
	}

	public void setListaRespostaCancelamentoMensagem(
			List<RespostaCancelamentoMensagem> listaRespostaCancelamentoMensagem) {
		this.listaRespostaCancelamentoMensagem = listaRespostaCancelamentoMensagem;
	}
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void processarXML( String xml ){
	 	
	 	XMLHandle controller = new XMLHandle();

	 	controller.insereAlias( "RESPOSTA", RespostaCancelamentoWrapper.class );
	 	controller.insereAlias( "MENSAGEM", RespostaCancelamentoMensagem.class );
	 	
	 	controller.insereAliasField( "STATUS", RespostaCancelamentoWrapper.class, "status" );
	 	controller.insereAliasField( "MENSAGENS", RespostaCancelamentoWrapper.class, "listaRespostaCancelamentoMensagem" );
	
		controller.insereAliasField( "COMUNICACAOORIGEM", RespostaCancelamentoMensagem.class, "comunicacaoOrigem" );
		controller.insereAliasField( "EVENTO", RespostaCancelamentoMensagem.class, "evento" );
	
		RespostaCancelamentoWrapper respostaWrapper = ( RespostaCancelamentoWrapper ) controller.fromXML( xml );
		
		this.listaRespostaCancelamentoMensagem = respostaWrapper.listaRespostaCancelamentoMensagem;
		this.status = respostaWrapper.status;
	 }	
}
