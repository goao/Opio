package softonPack.siscoaf.xml.geradorxml;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import softonPack.util.DateHandle;

public class XMLTeste {
	private static Logger log = Logger.getLogger(XMLTeste.class);
	public static DateHandle dateHandle = new DateHandle();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		XMLTeste xmlTeste = new XMLTeste();
		xmlTeste.geraXMLEnvio();
		xmlTeste.geraXMLCancelamento();
		testarRetificacao();
	}
	
	public static void testarRetificacao(){
		
		OcorrenciaRetificacao ocorrenciaRetificacao = new OcorrenciaRetificacao();
		
		ocorrenciaRetificacao.setNumeroOcorrencia(1);
		ocorrenciaRetificacao.setNumeroCoaf(new Integer(1452));
		ocorrenciaRetificacao.setRadicalCnpjCpf("51236579000156");
		
		ocorrenciaRetificacao.setDataFinalEvento(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		ocorrenciaRetificacao.setDataInicialEvento(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		
		ocorrenciaRetificacao.setNumeroAgencia(222);
		ocorrenciaRetificacao.setNomeAgencia("BRASILIA-PB");
		ocorrenciaRetificacao.setCidadeAgencia("BRASILIA");
		
		ocorrenciaRetificacao.setEstadoAgencia("DF");
		ocorrenciaRetificacao.setDetalhamento("Opera��es muito suspeita de acordo com a lei");
		
		ocorrenciaRetificacao.setValorCredito(new Integer(150000));
		ocorrenciaRetificacao.setValorDebito(new Integer(0));
		ocorrenciaRetificacao.setValorProvisionamento(new Integer(0));
	
		EnvolvidoRetificacao envolvidoRetificacao = new EnvolvidoRetificacao();
		envolvidoRetificacao.setRadicalCpfCnpj("56283456321");
		envolvidoRetificacao.setNomeEnvolvido("MANOEL VALDOIS");
		envolvidoRetificacao.setTipoEnvolvimento("1");
		envolvidoRetificacao.setCodigoAgencia(7856);
		envolvidoRetificacao.setNomeAgencia("Agencia principal");
		envolvidoRetificacao.setCodigoConta(45631);
		envolvidoRetificacao.setDataAberturaConta(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		envolvidoRetificacao.setDataAtualizacaoCadastro(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		envolvidoRetificacao.setPessoaObrigatoria(0);
		envolvidoRetificacao.setPep(0);
		envolvidoRetificacao.setTipoServidorPublico(0);
		
		EnvolvidoRetificacao envolvidoRetificacao1 = new EnvolvidoRetificacao();
		envolvidoRetificacao1.setRadicalCpfCnpj("56283456321");
		envolvidoRetificacao1.setNomeEnvolvido("MANOEL VALDOIS");
		envolvidoRetificacao1.setTipoEnvolvimento("1");
		envolvidoRetificacao1.setCodigoAgencia(7856);
		envolvidoRetificacao1.setNomeAgencia("Agencia principal");
		envolvidoRetificacao1.setCodigoConta(45631);
		envolvidoRetificacao1.setDataAberturaConta(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		envolvidoRetificacao1.setDataAtualizacaoCadastro(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		envolvidoRetificacao1.setPessoaObrigatoria(0);
		envolvidoRetificacao1.setPep(0);
		envolvidoRetificacao1.setTipoServidorPublico(0);
		
		EnquadramentoRetificacao enquadramentoRetificacao = new EnquadramentoRetificacao();
		enquadramentoRetificacao.setCodigoEnquadramento(237);
		
		List<String> codigos = new ArrayList<String>();
		codigos.add("1");
		codigos.add("2");
		codigos.add("3");
		ocorrenciaRetificacao.setEnquadramentos(codigos);
		

		RetificacaoMensagem retificacaoMensagem = new RetificacaoMensagem();
		retificacaoMensagem.add(ocorrenciaRetificacao);
		retificacaoMensagem.setIdOcorrencias("SISCOAF"+dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
	
		RetificacaoWrapper retificacaoWrapper = new RetificacaoWrapper(retificacaoMensagem);
		log.info("[ Test Ratifica��o ] " + retificacaoWrapper.getXml());
	}
	
	private static void geraXMLCancelamento(){
		List<OcorrenciaCancelamentoMensagem> listOcorrencias = new ArrayList<OcorrenciaCancelamentoMensagem>();
		for(int i=0;i<2;i++){
			listOcorrencias.add(new OcorrenciaCancelamentoMensagem(12,new Integer(9213),"999fddfo9d8f9jdofd8f98"));
			listOcorrencias.add(new OcorrenciaCancelamentoMensagem(15,new Integer(4554),"5454sd4545sddsd45445445"));	
		}
		LoteCancelamentoMensagem lote = new LoteCancelamentoMensagem(listOcorrencias);	
		lote.setIdOcorrencias("SISCOAFCancelamento"+dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		CancelamentoWrapper cancelamento = new CancelamentoWrapper(lote);
		System.out.print(cancelamento.getXml());
	}

	public String geraXMLEnvio(){
		
		EnvioLoteMensagem lote = new EnvioLoteMensagem();
		lote.setIdOcorrencias("SISCOAF"+dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		
		EnvioOcorrenciaMensagem ocorrencia = new EnvioOcorrenciaMensagem();

		ocorrencia.setNumeroOcorrencia(1);
		ocorrencia.setRadicalCnpjCpf("22211122288");
		ocorrencia.setDataInicialEvento(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		ocorrencia.setDataFinalEvento(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		ocorrencia.setNumeroAgencia(3258);
		ocorrencia.setNomeAgencia("Agencia Moema");
		ocorrencia.setCidadeAgencia("S�o Paulo");
		ocorrencia.setEstadoAgencia("SP");
		ocorrencia.setDetalhamento("Detalhamento envio");
		ocorrencia.setValorCredito(6);
		ocorrencia.setValorDebito(9);
		ocorrencia.setValorProvisionamento(11);
		ocorrencia.setValorProposto(11);
		
		List<String> codigos = new ArrayList<String>();
		codigos.add("1");
		codigos.add("2");
		codigos.add("3");
		ocorrencia.setEnquadramentos(codigos);
		
		EnvioEnvolvidoMensagem envolvido = new EnvioEnvolvidoMensagem();
		envolvido.setRadicalCpfCnpj("99338729");
		envolvido.setNomeEnvolvido("Softon sistemas inteligentes");
		envolvido.setTipoEnvolvimento("1");
		envolvido.setCodigoAgencia(3245);
		envolvido.setNomeAgencia("Agencia de algum lugar");
		envolvido.setCodigoConta(88877);
		envolvido.setDataAberturaConta(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		envolvido.setDataAtualizacaoCadastro(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		envolvido.setFlagPessoaObrigatoria(1);
		envolvido.setFlagPPE(1);
		envolvido.setTipoServidorPublico("0");
		
		EnvioEnvolvidoMensagem envolvido2 = new EnvioEnvolvidoMensagem();
		envolvido2.setRadicalCpfCnpj("99338729");
		envolvido2.setNomeEnvolvido("Softon sistemas inteligentes");
		envolvido2.setTipoEnvolvimento("1");
		envolvido2.setCodigoAgencia(3245);
		envolvido2.setNomeAgencia("Agencia de algum lugar");
		envolvido2.setCodigoConta(88877);
		envolvido2.setDataAberturaConta(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		envolvido2.setDataAtualizacaoCadastro(dateHandle.formatDate(new Date(), "dd/MM/yyyy"));
		envolvido2.setFlagPessoaObrigatoria(1);
		envolvido2.setFlagPPE(1);
		envolvido2.setTipoServidorPublico("0");
		
		ocorrencia.add(envolvido);
		ocorrencia.add(envolvido2);

		lote.add(ocorrencia);
		
		EnvioWrapper wrapper = new EnvioWrapper(lote);
		
		log.info(wrapper.getXml());
		
		return wrapper.getXml();
	}
}
