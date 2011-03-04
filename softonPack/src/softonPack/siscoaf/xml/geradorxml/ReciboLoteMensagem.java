package softonPack.siscoaf.xml.geradorxml;

import java.util.ArrayList;
import java.util.List;

public class ReciboLoteMensagem {
	
	private List<OcorrenciaReciboMensagem> listaOcorrenciaRecibo = new ArrayList<OcorrenciaReciboMensagem>();	
	
	public ReciboLoteMensagem() {	}

	public ReciboLoteMensagem(List<OcorrenciaReciboMensagem> listaOcorrenciaRecibo) {
		this.listaOcorrenciaRecibo = listaOcorrenciaRecibo;
	}
	
	public List<OcorrenciaReciboMensagem> getListaOcorrenciaRecibo() {
		return listaOcorrenciaRecibo;
	}

	public void setListaOcorrenciaRecibo(
			List<OcorrenciaReciboMensagem> listaOcorrenciaRecibo) {
		this.listaOcorrenciaRecibo = listaOcorrenciaRecibo;
	}

}
