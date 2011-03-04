package softonPack.siscoaf.xml.processadorxml;

import java.util.ArrayList;
import java.util.List;

public class Resposta3098OcorrenciaMensagem {
	
	private Integer numeroOcorrencia;
	private String radicalCnpjCpf;
	private String dataInicialEvento;
	private String dataFinalEvento;
	private Integer numeroAgencia;
	private String nomeAgencia;
	private String cidadeAgencia;
	private String estadoAgencia;
	private String detalhamento;
	private Double valorCredito;
	private Double valorDebito;
	private Double valorProvisionamento;
	private Double valorProposto;
 	
	private List<Resposta3098EnquadramentoMensagem> enquadramentos;
 	private List<RespostaEnvolvidoMensagem> envolvidos = new ArrayList<RespostaEnvolvidoMensagem>();
 	
 	public Integer getNumeroOcorrencia() {
		return numeroOcorrencia;
	}
	public void setNumeroOcorrencia(Integer numeroOcorrencia) {
		this.numeroOcorrencia = numeroOcorrencia;
	}
	public String getRadicalCnpjCpf() {
		return radicalCnpjCpf;
	}
	public void setRadicalCnpjCpf(String radicalCnpjCpf) {
		this.radicalCnpjCpf = radicalCnpjCpf;
	}
	public String getDataInicialEvento() {
		return dataInicialEvento;
	}
	public void setDataInicialEvento(String dataInicialEvento) {
		this.dataInicialEvento = dataInicialEvento;
	}
	public String getDataFinalEvento() {
		return dataFinalEvento;
	}
	public void setDataFinalEvento(String dataFinalEvento) {
		this.dataFinalEvento = dataFinalEvento;
	}
	public Integer getNumeroAgencia() {
		return numeroAgencia;
	}
	public void setNumeroAgencia(Integer numeroAgencia) {
		this.numeroAgencia = numeroAgencia;
	}
	public String getNomeAgencia() {
		return nomeAgencia;
	}
	public void setNomeAgencia(String nomeAgencia) {
		this.nomeAgencia = nomeAgencia;
	}
	public String getCidadeAgencia() {
		return cidadeAgencia;
	}
	public void setCidadeAgencia(String cidadeAgencia) {
		this.cidadeAgencia = cidadeAgencia;
	}
	public String getEstadoAgencia() {
		return estadoAgencia;
	}
	public void setEstadoAgencia(String estadoAgencia) {
		this.estadoAgencia = estadoAgencia;
	}
	public String getDetalhamento() {
		return detalhamento;
	}
	public void setDetalhamento(String detalhamento) {
		this.detalhamento = detalhamento;
	}
	public Double getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(Double valorCredito) {
		this.valorCredito = valorCredito;
	}
	public Double getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(Double valorDebito) {
		this.valorDebito = valorDebito;
	}
	public Double getValorProvisionamento() {
		return valorProvisionamento;
	}
	public void setValorProvisionamento(Double valorProvisionamento) {
		this.valorProvisionamento = valorProvisionamento;
	}
	public Double getValorProposto() {
		return valorProposto;
	}
	public void setValorProposto(Double valorProposto) {
		this.valorProposto = valorProposto;
	}
	public List<Resposta3098EnquadramentoMensagem> getEnquadramentos() {
		return enquadramentos;
	}
	public void setEnquadramentos(
			List<Resposta3098EnquadramentoMensagem> enquadramentos) {
		this.enquadramentos = enquadramentos;
	}
	public List<RespostaEnvolvidoMensagem> getEnvolvidos() {
		return envolvidos;
	}
	public void setEnvolvidos(List<RespostaEnvolvidoMensagem> envolvidos) {
		this.envolvidos = envolvidos;
	}
}
