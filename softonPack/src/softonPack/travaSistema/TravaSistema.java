package softonPack.travaSistema;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.apache.log4j.Logger;

/**
 * Permitir a execus�o de 
 * apenas uma inst�ncia do programa
 * @author reginaldo
 *
 */
public class TravaSistema {
	private static Logger log = Logger.getLogger(TravaSistema.class);
	private static File file;
	private static FileChannel channel;
	private static FileLock lock;
	/**
	 * 
	 * @param caminho - local onde ser� gerado um arquivo de controle
	 * @param programa - nome programa  
	 * @throws IOException 
	 */
	public static void travarArquivoSistema(String caminho, String programa) throws IOException  {

		String arquivo =  caminho + programa + ".system";

		file = new File(arquivo);

		channel = new RandomAccessFile(file, "rw").getChannel();
		lock = channel.tryLock();
	}

	public static void liberarArquivoSistema()  {
		try {
			if (lock != null) lock.release();
			if (channel != null) channel.close();
		} catch (IOException e) {
			log.info("Arquivo de travamento n�o encontrado");
		}

	}


	/**
	 * Abre arquivo do sistema "/CNTL/dtec.system" para n�o 
	 * permitir que seja executada mais de uma inst�ncia de 
	 * processamento.
	 */
	public static boolean isLocked() {
		return lock == null;
	}


}
