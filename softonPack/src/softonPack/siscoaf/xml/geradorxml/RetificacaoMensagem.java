package softonPack.siscoaf.xml.geradorxml;

import java.util.ArrayList;
import java.util.List;

public class RetificacaoMensagem {
	
	private String idOcorrencias;
	
	private List<OcorrenciaRetificacao> listaOcorrenciaRetificacao = new ArrayList<OcorrenciaRetificacao>();

	public List<OcorrenciaRetificacao> getListaOcorrenciaRetificacao() {
		return listaOcorrenciaRetificacao;
	}

	public void setListaOcorrenciaRetificacao(List<OcorrenciaRetificacao> listaOcorrenciaRetificacao) {
		this.listaOcorrenciaRetificacao = listaOcorrenciaRetificacao;
	}
	
	public void add(OcorrenciaRetificacao ocorrenciaRetificacao){
		this.listaOcorrenciaRetificacao.add(ocorrenciaRetificacao);
	}

	public String getIdOcorrencias() {
		return idOcorrencias;
	}

	public void setIdOcorrencias(String idOcorrencias) {
		this.idOcorrencias = idOcorrencias;
	}
	
}
