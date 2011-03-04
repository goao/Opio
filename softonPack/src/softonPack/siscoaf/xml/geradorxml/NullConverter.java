package softonPack.siscoaf.xml.geradorxml;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class NullConverter implements Converter {

	public boolean canConvert(Class type) {

		return type.equals(int.class) || type.equals(Integer.class) ||
		type.equals(String.class);

	}

	
	public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
		
//		if(value.getClass().equals(int.class) || value.getClass().equals(Integer.class) )
		
		if(value == null)
			writer.setValue("");
	}


	public Object unmarshal(HierarchicalStreamReader arg0,
			UnmarshallingContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	


}
