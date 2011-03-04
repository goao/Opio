package softonPack.drools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Set;

import org.apache.log4j.Logger;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseConfiguration;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.DroolsParserException;
import org.drools.conf.MaxThreadsOption;
import org.drools.conf.MultithreadEvaluationOption;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Rule;
import org.drools.io.Resource;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;



public class DroolsHandle {
	private static Logger log = Logger.getLogger(DroolsHandle.class);
	private KnowledgeBase ruleBase;
		
	public DroolsHandle(KnowledgeBaseConfiguration conf){
		ruleBase = KnowledgeBaseFactory.newKnowledgeBase(conf);
	}
		
	public DroolsHandle(){
		ruleBase = KnowledgeBaseFactory.newKnowledgeBase();
	}
	
	

	public StatefulKnowledgeSession getSession() throws FileNotFoundException,
			DroolsParserException, IOException, Exception {


		// DELETO FL + DESATIVADA
		// INSIRO NA TB_REGRA_ANLSE
		// dumpRuleBase();

		StatefulKnowledgeSession session = ruleBase.newStatefulKnowledgeSession();
		return session;
	}






	public void newPackage(ArrayList<String> files)
			throws FileNotFoundException, DroolsParserException, IOException,
			Exception {
    	// Instanciamos o construtor de conhecimento...
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		for(String fileName : files){
            builder.add( ResourceFactory.newFileResource(new File( fileName ) ),
                    ResourceType.DRL);
		}
		
		// Check the builder for errors
		if ( builder.hasErrors() ) {
		    log.info( builder.getErrors().toString() );
		    throw new Exception( "DroolsHandle Error: Nao foi possivel compilar arquivos .drl");
		}
		//get the compiled package (which is serializable)
		ruleBase.addKnowledgePackages( builder.getKnowledgePackages() );
	}

	public void newPackageChangeSet(String changeFile)
			throws FileNotFoundException, DroolsParserException, IOException,
			Exception {
    	// Instanciamos o construtor de conhecimento...
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		builder.add(  ResourceFactory.newClassPathResource( changeFile, getClass() ), ResourceType.CHANGE_SET);

		
		// Check the builder for errors
		if ( builder.hasErrors() ) {
		    log.info( builder.getErrors().toString() );
		    throw new Exception( "DroolsHandle Error: Nao foi possivel compilar arquivos .drl\nMotivo:\n\n" + builder.getErrors().toString());
		}
		//get the compiled package (which is serializable)
		ruleBase.addKnowledgePackages( builder.getKnowledgePackages() );
	}


	public void newPackageStream(ArrayList<InputStream> files)
			throws FileNotFoundException, DroolsParserException, IOException,
			Exception {

    	// Instanciamos o construtor de conhecimento...
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		for(InputStream fileName : files){
	        // Adicionamos as regras a este construtor...
	        try {
	        	// A princ�pio adicionaremos apenas uma regra, "terminal.drl", mais a frente 
	        	// provavelmente receberemos um package vindo do DROOLS GUVNOR
	            builder.add( ResourceFactory.newInputStreamResource( fileName ), ResourceType.DRL);
	        } catch ( Exception e ) {
	            e.printStackTrace();
	        }
		}
		
		// Check the builder for errors
		if ( builder.hasErrors() ) {
		    log.info( builder.getErrors().toString() );
		    throw new Exception( "DroolsHandle Error: Nao foi possivel compilar arquivos .drl");
		}
		//get the compiled package (which is serializable)
		ruleBase.addKnowledgePackages( builder.getKnowledgePackages() );
	}


	public void newPackageResource(ArrayList<Resource> files)
			throws FileNotFoundException, DroolsParserException, IOException,
			Exception {

    	// Instanciamos o construtor de conhecimento...
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

		for(Resource fileName : files){
	        // Adicionamos as regras a este construtor...
	        try {
	        	// A princ�pio adicionaremos apenas uma regra, "terminal.drl", mais a frente 
	        	// provavelmente receberemos um package vindo do DROOLS GUVNOR
	            builder.add( fileName, ResourceType.DRL);
	        } catch ( Exception e ) {
	            e.printStackTrace();
	        }
		}
		
		// Check the builder for errors
		if ( builder.hasErrors() ) {
		    log.info( builder.getErrors().toString() );
		    throw new Exception( "DroolsHandle Error: Nao foi possivel compilar arquivos .drl");
		}
		//get the compiled package (which is serializable)
		ruleBase.addKnowledgePackages( builder.getKnowledgePackages() );
	}
	

	public void addRegrasDinamicas(String stringRules) throws Exception {
		log.debug("iniciando construcao de pacote de regras dinamicas.");

    	// Instanciamos o construtor de conhecimento...
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		

        builder.add( ResourceFactory.newInputStreamResource( new FileInputStream(new File( stringRules )) ),
                     ResourceType.DRL);
		
		
		// Check the builder for errors
		if ( builder.hasErrors() ) {
			log.info("\n\n" + stringRules);
			
		    log.info( builder.getErrors().toString() );
		    throw new Exception( "DroolsHandle Error: Nao foi possivel compilar regras dinamicas.");
		}
		//get the compiled package (which is serializable)
		ruleBase.addKnowledgePackages( builder.getKnowledgePackages() );
		
		// REMOVE DEBUG
		log.debug("\n\n" + stringRules);
		
		log.debug("pacote de regras dinamicas inserido com sucesso na wm.");
	}

	
	public void addRegrasDinamicasFromString(String stringDRL) throws Exception {
		log.info("iniciando construcao de pacote de regras dinamicas.");

    	// Instanciamos o construtor de conhecimento...
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        
        
        builder.add( ResourceFactory.newReaderResource( new StringReader(stringDRL)), ResourceType.DRL );

        
		
		
		// Check the builder for errors
		if ( builder.hasErrors() ) {
			//log.info("\n\n" + stringRules);
			
		    log.info( builder.getErrors().toString() );
		    throw new Exception( "DroolsHandle Error: Nao foi possivel compilar regras dinamicas.");
		}
		//get the compiled package (which is serializable)
		ruleBase.addKnowledgePackages( builder.getKnowledgePackages() );
		
		// REMOVE DEBUG
		//log.info("\n\n" + stringRules);
		
		log.debug("pacote de regras dinamicas inserido com sucesso na wm.");
	}
	
	
	
	

	public void addRegrasDinamicasFromPackage(String pathPackage) throws Exception {
		log.debug("iniciando construcao de pacote de regras dinamicas (byPackage).");

    	// Instanciamos o construtor de conhecimento...
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add( ResourceFactory.newInputStreamResource( new FileInputStream(new File( "/root/cef.pkg" )) ), ResourceType.PKG );


        // Check the builder for errors
		if ( builder.hasErrors() ) {
			//log.info("\n\n" + stringRules);
			
		    log.info( builder.getErrors().toString() );
		    throw new Exception( "DroolsHandle Error: Nao foi possivel compilar regras dinamicas.");
		}
		//get the compiled package (which is serializable)
		ruleBase.addKnowledgePackages(  builder.getKnowledgePackages() );
		
		log.debug("pacote de regras dinamicas inserido com sucesso na wm.");
	}

	
	
	public void removeRules(Set<Short> setRegras) {

		for (KnowledgePackage pack: ruleBase.getKnowledgePackages()){
			for(Rule rule : pack.getRules()){
				
				Short cod = getRuleCode(rule.getName());
				System.out.println("Código da Regra na KB >> " + cod);
				if(cod == null)
					continue;
						
//				System.out.println("Verificando se a regra esta no set de regras >> " + setRegras.contains(cod));
//				System.out.println("Regras no set de regras a desconsiderar >> " + setRegras);
				if(setRegras.contains(cod)) {
					//log.info("dtecBatch: Removendo regra: "+ pack.getName() + "." + rule.getName() );
					ruleBase.removeRule(pack.getName(), rule.getName());
					System.out.println("Pacote da regra removida >> " + pack.getName());
					System.out.println("Nome da regra removida >> " + rule.getName());
				}
			}	
		}
		dumpRuleBase();
		
	}
	
	private Short getRuleCode(String name) {
		int index = name.indexOf('-');
		if(index <= 0)
			return null;
		
		return new Short(name.substring(0,index));
	}


	public void dumpRuleBase() {
		for (KnowledgePackage pack: ruleBase.getKnowledgePackages()){
			log.debug("==== " + pack.getName() + " ====");
			for(Rule rule : pack.getRules()){
				log.debug(rule.getName());
			}
		}
	}







}
