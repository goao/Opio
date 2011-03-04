package softonPack.util;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileHandle {
	private static Logger log = Logger.getLogger(FileHandle.class);
	private static String filtro = "";

	public static List<String> listaArquivos(String filtroArquivo, String diretorio){
		filtro = filtroArquivo;
		List<String> arquivos = new ArrayList<String>();

		FileFilter filter = new FileFilter() {  
			public boolean accept(File file) {  
				return file.getName().endsWith(filtro);  
			}  
		};  
		log.info(diretorio);
		File dir = new File(diretorio);  
		File[] files = dir.listFiles(filter);

		if(files == null) {
			return new ArrayList();
		}
		
		if(files.length > 0){

			for (File f :files){
				arquivos.add(f.getName());
			}
		}

		return arquivos;

	}

	public static String obterArquivo(String filtroArquivo, String diretorio){
		filtro = filtroArquivo;
		String arquivo = null;

		FileFilter filter = new FileFilter() {  
			public boolean accept(File file) {  
				return file.getName().endsWith(filtro);  
			}  
		};  

		log.info(diretorio);
		File dir = new File(diretorio);  
		File[] arquivos = dir.listFiles(filter);

		if(arquivos.length >= 1){
			for (File arquivoUnico : arquivos) {
				arquivo = arquivoUnico.getName();
			}
		}
		else{
			//("N�o foi poss�vel identificar este arquivo no diret�rio: " + diretorio);
			log.info("N�o foi poss�vel identificar este arquivo no diret�rio: " + diretorio);
		}

		return arquivo;

	}


	//TODO: Remover este m�todo
/*	public static void moverArquivos(String diretorioAtual,String diretorioBKP, String nomeArquivo){

		
		log.info("SoftonPack.FileHandle.moverArquivos(): This method is deprecated." +
				" Please checkit TODO in javadoc");
		
		return;
//		log.info(diretorioAtual);
//		log.info(diretorioBKP);
//		log.info(nomeArquivo);
//		File arquivo = new File(diretorioAtual + "/" + nomeArquivo);
//
//		arquivo.renameTo(new File(diretorioBKP, nomeArquivo + ".OK"));
//
//		log.info("N�o foi poss�vel fazer backup deste arquivo de dados: " + nomeArquivo + "\n" +
//		"Verifique se seu nome est� no formato correto ou se existe um arquivo de lote correspondente. \n");
	}
*/
	
	public static FileInputStream openCreateFile(String filePath) throws FileNotFoundException{
		FileInputStream _file = new FileInputStream(filePath);

		return _file;
	}

	public static void writeOnFileStream(String msg, FileOutputStream fos) throws IOException {
		DataOutputStream dos = new DataOutputStream(fos);
		dos.writeChars(msg);
	}

	public static void writeIntOnFile(int i, String errorFlagFile) throws IOException {
		FileOutputStream fos; 
		DataOutputStream dos;

		File file= new File(errorFlagFile);
		fos = new FileOutputStream(file);
		dos=new DataOutputStream(fos);
		dos.writeInt(i);
		dos.close();
	}

	public static void writeStringOnFile(String msg, String errorFlagFile) throws IOException {
		FileOutputStream fos; 
		DataOutputStream dos;

		File file= new File(errorFlagFile);
		fos = new FileOutputStream(file);
		dos=new DataOutputStream(fos);
		dos.writeChars(msg);
		dos.close();
	}
	
	public static void writeOnFile(String msg, String errorFlagFile) throws IOException {
		FileOutputStream fos; 
		DataOutputStream dos;

		FileWriter file= new FileWriter(errorFlagFile);
		BufferedWriter out = new BufferedWriter(file);
		out.write(msg);
		out.close();
	}
	
	public static void deletFile(String file){
		File arquivo = new File(file);
		arquivo.delete();
		
	}
}