package softonPack.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLHandle extends XStream {
	
	public XMLHandle() {
		super(new DomDriver());
	}

	public void insereAliasField( String tag, Class clazz, String field ){
		super.aliasField(tag, clazz, field);		
	}
	
	public void insereAlias( String tag, Class clazz ){
		super.alias( tag, clazz );		
	}

	public void insereAliasType( String tag, Class clazz ){
		super.aliasType( tag, clazz );		
	}
	
}
