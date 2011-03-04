/**
 * 
 */
package softonPack.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;


/**
 * @author mario
 *
 */
public class ReflectionHandle {
	
	private String className;
	private String methodName;
	private Class classObject;
	private Object classInstance;
	private Object[] argument;
	private Class[] argumentTypes;
	private Method methodInstance;
	
	
	public ReflectionHandle(String className) throws Exception{
		this.className = className;
		this.classObject = this.classFactory();
		this.classInstance = this.doClassInstance();
	}
	
	public ReflectionHandle(String className, Object object) throws Exception{
		this.className = className;
		this.classObject = this.classFactory();
		this.classInstance = object;
	}
	
	
	public static Object executeMethodFrom(String className, String methodName) throws Exception{
		ReflectionHandle rh = new ReflectionHandle(className);
		rh.setMethodName(methodName);


        rh.argument = new Object [] { };
        rh.argumentTypes = new Class[] {};
		rh.methodInstance = rh.doMethodInstance();
        
        return rh.invoke();
        
	}
	
	public static Object executeMethodFromInstance(String className, String methodName, Object object) throws Exception{
		ReflectionHandle rh = new ReflectionHandle(className, object);
		rh.setMethodName(methodName);


        rh.argument = new Object [] { };
        rh.argumentTypes = new Class[] {};
		rh.methodInstance = rh.doMethodInstance();
        
        return rh.invoke();
        
	}
//	Inclusao do m�todo para buscar o tipo de retorno do m�todo.

	public static Class getReturnTypeMethod(String className, String methodName) throws Exception{
		ReflectionHandle rh = new ReflectionHandle(className);
		rh.setMethodName(methodName);


        rh.argument = new Object [] { };
        rh.argumentTypes = new Class[] {};
		rh.methodInstance = rh.doMethodInstance();
        
        return rh.getMethodInstance().getReturnType();
        
	}
	

	
	
	private Class classFactory() throws Exception{
		Class classObject = null;
	    try {
	    	classObject = Class.forName(this.className);
	    } catch (ClassNotFoundException ex) {
	        ex.printStackTrace();
	        throw new Exception("Classe '"+this.className+"' n�o encontrada."+
	                ex.getMessage());
	    }
	    
	    return classObject;
	}
	
	private Object invoke() throws Exception{
		Object objReturn = null;

    	try {
			objReturn = this.methodInstance.invoke(this.classInstance, this.argument);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
	        throw new Exception("Argumentos inv�lido para m�todo "+this.className+"."+
	        		this.methodName+"()");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
	        throw new Exception("Acesso n�o permitido para m�todo "+this.className+"."+
	        		this.methodName+"()");			
		} catch (InvocationTargetException e) {
			e.printStackTrace();
	        throw new Exception("M�todo n�o existente: "+this.className+"."+
	        		this.methodName+"()");			
		}


		return objReturn;
	}	
	
	private Object doClassInstance(){
        Object classInstance = null;
        try {
        	classInstance = this.classObject.newInstance();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        }

		return classInstance;
	}
	
	private Method doMethodInstance() throws Exception{
		Method method = null;
		
	    try {
			method = this.classObject.getMethod(this.methodName, this.argumentTypes);
		} catch (SecurityException e) {
			e.printStackTrace();
	        throw new Exception("Acesso n�o permitido para m�todo "+this.className+"."+
	        		this.methodName+"()");				
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
	        throw new Exception("M�todo n�o existente: "+this.className+"."+
	        		this.methodName+"()");				
		}
	    
		
		return method;
	}


	/**
	 * @return the methodName
	 */
	protected String getMethodName() {
		return this.methodName;
	}


	/**
	 * @param methodName the methodName to set
	 */
	private void setMethodName(String methodName) {
		this.methodName = methodName;
	}


	/**
	 * @return the argument
	 */
	public Object[] getArgument() {
		return this.argument;
	}


	/**
	 * @param argument the argument to set
	 */
	public void setArgument(Object[] argument) {
		this.argument = argument;
	}


	/**
	 * @return the argumentTypes
	 */
	public Class[] getArgumentTypes() {
		return this.argumentTypes;
	}


	/**
	 * @param argumentTypes the argumentTypes to set
	 */
	public void setArgumentTypes(Class[] argumentTypes) {
		this.argumentTypes = argumentTypes;
	}


	public Method getMethodInstance() {
		return methodInstance;
	}


	public void setMethodInstance(Method methodInstance) {
		this.methodInstance = methodInstance;
	}
	
	public static Object getInstanceof(String className) throws Exception{
		ReflectionHandle rh = new ReflectionHandle(className);
		return rh.classInstance;
	}
}
