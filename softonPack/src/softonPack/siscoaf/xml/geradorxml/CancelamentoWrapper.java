package softonPack.siscoaf.xml.geradorxml;

import softonPack.util.XMLHandle;

public class CancelamentoWrapper {

	private String xml = "";
	
	private XMLHandle controller = null;

	private LoteCancelamentoMensagem cancelamentoObj = null;

	public CancelamentoWrapper(LoteCancelamentoMensagem loteCancelamento) {
		this.controller = new XMLHandle();
		this.cancelamentoObj = loteCancelamento;
	}

	public LoteCancelamentoMensagem getLoteCancelamento() {
		return cancelamentoObj;
	}

	public void setLoteCancelamento(LoteCancelamentoMensagem loteCancelamento) {
		this.cancelamentoObj = loteCancelamento;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getXml() {
		
		/* 	<LOTECANCELAMENTO> 
		 * 		<OCORRENCIAS ID="SISCOAFCancelamento01012009">
		 * 			<OCORRENCIA>
		 * 				<NUMEROORIGEM>12</NUMEROORIGEM>
		 * 				<NUMEROCOAF>921377</NUMEROCOAF>
		 * 				<AUTENTICACAO>9AFDASODKA90D0A9S0DA09D9A09SD0AS09</AUTENTICACAO>
		 * 			</OCORRENCIA>
		 * 		</OCORRENCIAS>
		 * 	</LOTECANCELAMENTO>
		 */
		
		this.controller.insereAlias("LOTECANCELAMENTO", LoteCancelamentoMensagem.class);
		
		this.controller.insereAliasField("OCORRENCIAS", LoteCancelamentoMensagem.class, "ocorrencias");
		
		this.controller.insereAlias("OCORRENCIA", OcorrenciaCancelamentoMensagem.class);
		
		this.controller.insereAliasField("NUMEROORIGEM", OcorrenciaCancelamentoMensagem.class,
				"numeroOrigem");
		this.controller.insereAliasField("NUMEROCOAF", OcorrenciaCancelamentoMensagem.class,
				"numeroCoaf");
		this.controller.insereAliasField("AUTENTICACAO", OcorrenciaCancelamentoMensagem.class,
				"autenticacao");
		
		this.controller.omitField( LoteCancelamentoMensagem.class, "idOcorrencias");
		
		this.xml = this.controller.toXML(this.cancelamentoObj);
		
		return xml = this.xml.replaceFirst( "<OCORRENCIAS>",
				"<OCORRENCIAS ID=\"" + this.cancelamentoObj.getIdOcorrencias().trim()+ "\">" );
		
	}

	public LoteCancelamentoMensagem getCancelamentoObj() {
		return cancelamentoObj;
	}
	
	
}
