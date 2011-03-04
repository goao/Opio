package softonPack.siscoaf.xml.geradorxml;

import softonPack.util.DateHandle;
import softonPack.util.StringHandle;

public class OcorrenciaReciboMensagem {
	
	private String numeroInscricaoComunicante;
	private String dataComunicado;
	private String nomeComunicado;

	
	
	public OcorrenciaReciboMensagem() {	}
	
	public OcorrenciaReciboMensagem(String numeroInscricaoComunicante,
			String dataComunicado) {
		this.numeroInscricaoComunicante = numeroInscricaoComunicante;
		this.dataComunicado = dataComunicado;
		this.nomeComunicado = StringHandle.getOsNumerosDaString(dataComunicado);
	}
	
	
	public String getNumeroInscricaoComunicante() {
		return numeroInscricaoComunicante;
	}
	public void setNumeroInscricaoComunicante(String numeroInscricaoComunicante) {
		this.numeroInscricaoComunicante = numeroInscricaoComunicante;
	}
	public String getDataComunicado() {
		return dataComunicado;
	}
	public void setDataComunicado(String dataComunicado) {
		this.dataComunicado = dataComunicado;
	}

	public String getNomeComunicado() {
		return nomeComunicado;
	}

	public void setNomeComunicado(String nomeComunicado) {
		this.nomeComunicado = nomeComunicado;
	}

}
