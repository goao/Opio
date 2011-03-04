package softonPack.siscoaf.xml.geradorxml;

import java.util.ArrayList;
import java.util.List;



public class LoteCancelamentoMensagem {

	private String idOcorrencias;

	private List< OcorrenciaCancelamentoMensagem > ocorrencias = new ArrayList< OcorrenciaCancelamentoMensagem >();
	
	public String getIdOcorrencias() {
		return idOcorrencias;
	}

	public void setIdOcorrencias(String idOcorrencias) {
		this.idOcorrencias = idOcorrencias;
	}

	public LoteCancelamentoMensagem(List< OcorrenciaCancelamentoMensagem > ocorrencias){
		this.ocorrencias = ocorrencias;
	}

	public List<OcorrenciaCancelamentoMensagem> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<OcorrenciaCancelamentoMensagem> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}	
}
