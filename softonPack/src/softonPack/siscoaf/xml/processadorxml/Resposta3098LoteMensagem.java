package softonPack.siscoaf.xml.processadorxml;

import java.util.ArrayList;
import java.util.List;

public class Resposta3098LoteMensagem {
	
	private String idOcorrencias;
	
	public List<Resposta3098OcorrenciaMensagem> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Resposta3098OcorrenciaMensagem> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	private List<Resposta3098OcorrenciaMensagem> ocorrencias = new ArrayList<Resposta3098OcorrenciaMensagem>();

	public void add( Resposta3098OcorrenciaMensagem ocorrencias ){
		this.ocorrencias.add(ocorrencias);
	}

	public String getIdOcorrencias() {
		return idOcorrencias;
	}

	public void setIdOcorrencias(String idOcorrencias) {
		this.idOcorrencias = idOcorrencias;
	}
	
	
	
}
