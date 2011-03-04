import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;


public class MainRandomGenerator {
	
	private static Logger log = Logger.getLogger(MainRandomGenerator.class);

	public static void main(String[] args) throws Exception {
		
		
		while (true) {
			ExecutorService threadPool = Executors.newFixedThreadPool(1);
			

			log.info("iniciando coleta de random numbers. ");
			MainRandomGeneratorThread mainThread = new MainRandomGeneratorThread();
			threadPool.execute(mainThread);

			threadPool.shutdown();
			while (!threadPool.isTerminated()) {
				 //DORMIR POR 30 minutos | 180000 miliSegundos
				log.info("sleeping for 1800000 mSec" );
				Thread.currentThread().sleep(1800000);// sleep
				
			}
			;
			
		}
		
		
		
//		String[] randNumbers = connectRandom();
//		for(int i=0;i<randNumbers.length;i++){
//			System.out.println(randNumbers[i]);
//		}
		
		
	}


}
