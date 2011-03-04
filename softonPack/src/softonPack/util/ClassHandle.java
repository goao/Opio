package softonPack.util;

public class ClassHandle {
	
	public static String getPackagePath(Class<?> clazz){
		return clazz.getPackage().getName().replaceAll("\\.", "/");
	}

}
