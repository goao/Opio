package softonPack.siscoaf.xml.geradorxml;

public class OcorrenciaCancelamentoMensagem {
	
	private Integer numeroOrigem;
	private Integer numeroCoaf;
	private String autenticacao;
	
	public OcorrenciaCancelamentoMensagem(){}
	
	public OcorrenciaCancelamentoMensagem(Integer numeroOrigem, Integer numeroCoaf,
			String autenticacao) {
		this.numeroOrigem = numeroOrigem;
		this.numeroCoaf = numeroCoaf;
		this.autenticacao = autenticacao;
	}
	
	public Integer getNumeroOrigem() {
		return numeroOrigem;
	}
	public void setNumeroOrigem(Integer numeroOrigem) {
		this.numeroOrigem = numeroOrigem;
	}
	public Integer getNumeroCoaf() {
		return numeroCoaf;
	}
	public void setNumeroCoaf(Integer numeroCoaf) {
		this.numeroCoaf = numeroCoaf;
	}
	public String getAutenticacao() {
		return autenticacao;
	}
	public void setAutenticacao(String autenticacao) {
		this.autenticacao = autenticacao;
	}
	
}
