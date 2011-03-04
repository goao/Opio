package softonPack.siscoaf.xml.geradorxml;

import com.thoughtworks.xstream.converters.basic.AbstractBasicConverter;

public class IntConverter extends AbstractBasicConverter {

	public boolean canConvert(Class type) {

		return type.equals(int.class) || type.equals(Integer.class);

	}

	protected Object fromString(String str) {

		/* If empty tag. */

		if (str.compareTo("") == 0) {

			/* Default to zero. */

			str = "0";

		}

		return Integer.decode(str);

	}

}
