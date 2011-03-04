package softonPack.siscoaf.xml.processadorxml;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class EnquadramentoConverter implements Converter{
	
	public boolean canConvert(Class clazz) {
        return clazz.equals(Resposta3098LoteMensagem.class);
	}
	
	public void marshal(Object value, HierarchicalStreamWriter writer,
	                MarshallingContext context) {
		Resposta3098LoteMensagem enq = (Resposta3098LoteMensagem) value;
     	writer.startNode("EnqCod");
     	
     	for(Resposta3098OcorrenciaMensagem resp : enq.getOcorrencias()){
     		for(Resposta3098EnquadramentoMensagem enqMensagem : resp.getEnquadramentos()){
     			writer.setValue(enqMensagem.getCodigoEnquadramento().toString());
     			writer.endNode();
     		}
     	}
	}
	
	public Object unmarshal(HierarchicalStreamReader reader,
	                UnmarshallingContext context) {
		
		Resposta3098LoteMensagem enq = new Resposta3098LoteMensagem();
		Resposta3098OcorrenciaMensagem resp = new Resposta3098OcorrenciaMensagem();
		Resposta3098EnquadramentoMensagem enqMensagem = new Resposta3098EnquadramentoMensagem();
        enqMensagem.setCodigoEnquadramento(new Long(reader.getValue()));
        List<Resposta3098EnquadramentoMensagem> listEnqMensagem = new ArrayList<Resposta3098EnquadramentoMensagem>();
        listEnqMensagem.add(enqMensagem);
        resp.setEnquadramentos(listEnqMensagem);
        List<Resposta3098OcorrenciaMensagem> listResp = new ArrayList<Resposta3098OcorrenciaMensagem>();
        listResp.add(resp);
        enq.setOcorrencias(listResp);
        reader.moveUp();
        return enq;
	}


}
