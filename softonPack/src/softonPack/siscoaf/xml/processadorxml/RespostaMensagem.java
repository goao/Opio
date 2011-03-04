package softonPack.siscoaf.xml.processadorxml;

public class RespostaMensagem {
	private Integer comunicacaoOrigem;
	private Integer erro;
	private Integer status; 
		
	public Integer getComunicacaoOrigem() {
		return comunicacaoOrigem;
	}
	public void setComunicacaoOrigem(Integer comunicacaoOrigem) {
		this.comunicacaoOrigem = comunicacaoOrigem;
	}
	public Integer getErro() {
		return erro;
	}
	public void setErro(Integer erro) {
		this.erro = erro;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
