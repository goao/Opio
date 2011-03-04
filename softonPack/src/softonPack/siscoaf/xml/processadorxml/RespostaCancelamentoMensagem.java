package softonPack.siscoaf.xml.processadorxml;

import java.util.ArrayList;
import java.util.List;

public class RespostaCancelamentoMensagem {
	private String comunicacaoOrigem;
	private String evento;
 	private List<RespostaEnvolvidoMensagem> envolvidos = new ArrayList<RespostaEnvolvidoMensagem>();

	public String getComunicacaoOrigem() {
		return comunicacaoOrigem;
	}
	public void setComunicacaoOrigem(String comunicacaoOrigem) {
		this.comunicacaoOrigem = comunicacaoOrigem;
	}
	public String getEvento() {
		return evento;
	}
	public void setEvento(String evento) {
		this.evento = evento;
	}
	public List<RespostaEnvolvidoMensagem> getEnvolvidos() {
		return envolvidos;
	}
	public void setEnvolvidos(List<RespostaEnvolvidoMensagem> envolvidos) {
		this.envolvidos = envolvidos;
	}
}
