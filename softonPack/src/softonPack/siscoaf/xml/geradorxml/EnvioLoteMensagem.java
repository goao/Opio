package softonPack.siscoaf.xml.geradorxml;

import java.util.ArrayList;
import java.util.List;

public class EnvioLoteMensagem {
	
	private String idOcorrencias;
	
	private List< EnvioOcorrenciaMensagem > ocorrencias = new ArrayList< EnvioOcorrenciaMensagem >();
	
	public String getIdOcorrencias() {
		return idOcorrencias;
	}

	public void setIdOcorrencias(String idOcorrencias) {
		this.idOcorrencias = idOcorrencias;
	}

	public void add( EnvioOcorrenciaMensagem ocorrencias ){
		this.ocorrencias.add(ocorrencias);
	}

	public List<EnvioOcorrenciaMensagem> getOcorrencias() {
		return ocorrencias;
	}
	
	
}
