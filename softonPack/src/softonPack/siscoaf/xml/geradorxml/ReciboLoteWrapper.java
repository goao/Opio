package softonPack.siscoaf.xml.geradorxml;

import softonPack.util.XMLHandle;

public class ReciboLoteWrapper {
	private ReciboLoteMensagem reciboLoteObj;
	private String xml;
	private XMLHandle controller;
	
	public ReciboLoteWrapper( ReciboLoteMensagem recibo ) {
		this.controller = new XMLHandle();
		this.reciboLoteObj = recibo;
		this.xml = retornaXmlRetorno();
	}
	
	public String getXml() {
		return xml;
	}

	private String retornaXmlRetorno(){
		
		controller.alias("LOTERECIBO", ReciboLoteMensagem.class);
		controller.alias("OCORRENCIAS", OcorrenciaReciboMensagem.class);
		controller.aliasField("CPFCNPJCom", OcorrenciaReciboMensagem.class, "numeroInscricaoComunicante");
		controller.aliasField("DATA", OcorrenciaReciboMensagem.class, "dataComunicado");
		controller.aliasAttribute(OcorrenciaReciboMensagem.class, "nomeComunicado", "ID");
		controller.useAttributeFor(OcorrenciaReciboMensagem.class , "nomeComunicado");
		controller.addImplicitCollection(ReciboLoteMensagem.class, "listaOcorrenciaRecibo");
		
		this.xml = this.controller.toXML( reciboLoteObj );
		
		return xml;
	}

	public ReciboLoteMensagem getReciboLoteObj() {
		return reciboLoteObj;
	}
	
//	public static void main (String[] args){
//		
//		String data = "24/10/2009";
//		
//		List<OcorrenciaReciboMensagem> listaOcorrencia = new ArrayList<OcorrenciaReciboMensagem>();
//		listaOcorrencia.add(new OcorrenciaReciboMensagem("12364548645",data));
//		listaOcorrencia.add(new OcorrenciaReciboMensagem("454864587000165",data));
//		listaOcorrencia.add(new OcorrenciaReciboMensagem("98452165487",data));
//
//		ReciboLoteMensagem recibo = new ReciboLoteMensagem(listaOcorrencia);
//		
//		XStream xs = new XStream(new DomDriver()); 
//		
//		
//		xs.alias("LOTERECIBO", ReciboLoteMensagem.class);
//		xs.alias("OCORRENCIAS", OcorrenciaReciboMensagem.class);
//		xs.aliasField("CPFCNPJCom", OcorrenciaReciboMensagem.class, "numeroInscricaoComunicante");
//		xs.aliasField("DATA", OcorrenciaReciboMensagem.class, "dataComunicado");
//		xs.aliasAttribute(OcorrenciaReciboMensagem.class, "nomeComunicado", "ID");
//		xs.useAttributeFor(OcorrenciaReciboMensagem.class , "nomeComunicado");
//		xs.addImplicitCollection(ReciboLoteMensagem.class, "listaOcorrenciaRecibo");
//		
//    	/**
//  	  <?xml version='1.0' encoding�iso-8859-1�?>
//			<LOTERECIBO>
//				<OCORRENCIAS ID="SISCOAFRecibo07062009">
//					<CPFCNPJCom>12345678000101</CPFCNPJCom>
//					<DATA>06/06/2009</DATA>
//				</OCORRENCIAS>
//			</LOTERECIBO>
//  	 */
//		
//		String xml = xs.toXML(recibo);
//		
//		log.info(xml);
//	}
}
