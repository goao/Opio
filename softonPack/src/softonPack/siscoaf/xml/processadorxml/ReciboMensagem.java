package softonPack.siscoaf.xml.processadorxml;

public class ReciboMensagem {
	
	private Integer numeroOrigem;
	private Integer numeroCoaf;
	private String autenticacao;
	private Integer valor;
		
	public Integer getNumeroOrigem() {
		return numeroOrigem;
	}
	
	public void setNumeroOrigem( Integer numeroOrigem ) {
		this.numeroOrigem = numeroOrigem;
	}
	
	public Integer getNumeroCoaf() {
		return numeroCoaf;
	}
	
	public void setNumeroCoaf( Integer numeroCoaf ) {
		this.numeroCoaf = numeroCoaf;
	}
	
	public String getAutenticacao() {
		return autenticacao;
	}
	
	public void setAutenticacao( String autenticacao ) {
		this.autenticacao = autenticacao;
	}

	public Integer getValor() {
		return valor;
	}

	public void setValor(Integer valor) {
		this.valor = valor;
	}
	
}
