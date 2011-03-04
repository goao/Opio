package softonPack.siscoaf.xml.geradorxml;

import java.util.ArrayList;
import java.util.List;

public class OcorrenciaRetificacao {
	
	private Integer numeroOcorrencia;
	private Integer  numeroCoaf;
	private String radicalCnpjCpf;
	private String dataInicialEvento;
	private String dataFinalEvento;
	private Integer numeroAgencia;
	private String nomeAgencia;
	private String cidadeAgencia;
	private String estadoAgencia;
	private String detalhamento;
	private Integer  valorCredito;
	private Integer  valorDebito;
	private Integer  valorProvisionamento;
	private Integer  valorProposto;
	
	private List<String> enquadramentos;
	
	private EnquadramentoRetificacao enquadramentoRetificacao;
	
	private List<EnvolvidoRetificacao> listaEnvolvidoRetificacao = new ArrayList<EnvolvidoRetificacao>();
	
	public Integer getNumeroOcorrencia() {
		return numeroOcorrencia;
	}
	public void setNumeroOcorrencia(Integer numeroOcorrencia) {
		this.numeroOcorrencia = numeroOcorrencia;
	}
	public Integer  getNumeroCoaf() {
		return numeroCoaf;
	}
	public void setNumeroCoaf(Integer  numeroCoaf) {
		this.numeroCoaf = numeroCoaf;
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
	public Integer  getValorCredito() {
		return valorCredito;
	}
	public void setValorCredito(Integer  valorCredito) {
		this.valorCredito = valorCredito;
	}
	public Integer  getValorDebito() {
		return valorDebito;
	}
	public void setValorDebito(Integer  valorDebito) {
		this.valorDebito = valorDebito;
	}
	public Integer  getValorProvisionamento() {
		return valorProvisionamento;
	}
	public void setValorProvisionamento(Integer  valorProvisionamento) {
		this.valorProvisionamento = valorProvisionamento;
	}
	public Integer  getValorProposto() {
		return valorProposto;
	}
	public void setValorProposto(Integer  valorProposto) {
		this.valorProposto = valorProposto;
	}
		
	public List<EnvolvidoRetificacao> getListaEnvolvidoRetificacao() {
		return listaEnvolvidoRetificacao;
	}
	public void setListaEnvolvidoRetificacao(
			List<EnvolvidoRetificacao> listaEnvolvidoRetificacao) {
		this.listaEnvolvidoRetificacao = listaEnvolvidoRetificacao;
	}
	public void add(EnvolvidoRetificacao envolvidoRetificacao){
		this.listaEnvolvidoRetificacao.add(envolvidoRetificacao);
	}
	
	public EnquadramentoRetificacao getEnquadramentoRetificacao() {
		return enquadramentoRetificacao;
	}

	public void setEnquadramentoRetificacao(
			EnquadramentoRetificacao enquadramentoRetificacao) {
		this.enquadramentoRetificacao = enquadramentoRetificacao;
	}
	public List<String> getEnquadramentos() {
		return enquadramentos;
	}
	public void setEnquadramentos(List<String> enquadramentos) {
		this.enquadramentos = enquadramentos;
	}

}