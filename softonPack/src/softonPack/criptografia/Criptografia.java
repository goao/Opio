package softonPack.criptografia;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.log4j.Logger;


/**
 * -----------------------------------------------------------------------------
 * The following example implements a class for encrypting and decrypting
 * strings using several Cipher algorithms. The class is created with a key and
 * can be used repeatedly to encrypt and decrypt strings using that key.
 * Some of the more popular algorithms are:
 *      Blowfish
 *      DES
 *      DESede
 *      PBEWithMD5AndDES
 *      PBEWithMD5AndTripleDES
 *      TripleDES
 *
 * @version 1.0
 * @author  Jeffrey M. Hunter  (jhunter@idevelopment.info)
 * @author  http://www.idevelopment.info
 * -----------------------------------------------------------------------------
 */

public class Criptografia {
	private static Logger log = Logger.getLogger(Criptografia.class);
    private Cipher encoder;
    private Cipher decoder;

    /**
     * Cria e inicializa o objeto.
     * Tambem inicializa o codificador (encoder) e decodificador (decoder)
     * para o tipo de algoritimo passado no construtor.
     * @param key        Key utilizada para inicializar o encoder e o decoder.
     * @param algorithm  Tipo de algoritimo utilizado.
     */
    public Criptografia(SecretKey key, String algorithm) {
        try {
            encoder = Cipher.getInstance(algorithm);
            decoder = Cipher.getInstance(algorithm);
            encoder.init(Cipher.ENCRYPT_MODE, key);
            decoder.init(Cipher.DECRYPT_MODE, key);
        } catch (NoSuchPaddingException e) {
            log.info("EXCEPTION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            log.info("EXCEPTION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            log.info("EXCEPTION: InvalidKeyException");
        }
    }


    /**
     * Constructor used to create this object.  Responsible for setting
     * and initializing this object's encrypter and decrypter Chipher instances
     * given a Pass Phrase and algorithm.
     * @param contraSenha Pass Phrase used to initialize both the encrypter and
     *                   decrypter instances.
     */
    public Criptografia(String contraSenha) {

        // 8-bytes Salt
        byte[] salt = {
                (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
                (byte)0x56, (byte)0x34, (byte)0xE3, (byte)0x03
        };

        // Iteration count
        int iterationCount = 19;

        try {

            KeySpec keySpec = new PBEKeySpec(contraSenha.toCharArray(), salt, iterationCount);
            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);

            encoder = Cipher.getInstance(key.getAlgorithm());
            decoder = Cipher.getInstance(key.getAlgorithm());

            // Prepare the parameters to the cipthers
            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

            encoder.init(Cipher.ENCRYPT_MODE, key, paramSpec);
            decoder.init(Cipher.DECRYPT_MODE, key, paramSpec);

        } catch (InvalidAlgorithmParameterException e) {
            log.info("EXCEPTION: InvalidAlgorithmParameterException");
        } catch (InvalidKeySpecException e) {
            log.info("EXCEPTION: InvalidKeySpecException");
        } catch (NoSuchPaddingException e) {
            log.info("EXCEPTION: NoSuchPaddingException");
        } catch (NoSuchAlgorithmException e) {
            log.info("EXCEPTION: NoSuchAlgorithmException");
        } catch (InvalidKeyException e) {
            log.info("EXCEPTION: InvalidKeyException");
        }
    }


    /**
     * Recebe uma string e retorna o resultado da string criptografada.
     * @param str String a ser criptofrafada.
     * @return <code>String</code> Resultado da string codificada.
     */
    public String encriptografar(String str) {
    	String texto = null;
        try {
            // Encode the string into bytes using utf-8
            byte[] utf8 = str.getBytes("UTF-8");

            // Codifica
            byte[] enc = encoder.doFinal(utf8);
            // Encode bytes to base64 to get a string
            texto = new sun.misc.BASE64Encoder().encode(enc) ;
        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
                }
        return texto;
    }

    /**
     * Recebe uma String criptografada como entrada, decodifica e retorna
     * a String decifrada.
     * @param str String croptografada.
     * @return <code>String</code> Vers�o decifrada da String.
     */
    public String descriptografar(String str) {
    	String texto = null;
        try {
            // Decode base64 to get bytes
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
            // Decrypt
            byte[] utf8 = decoder.doFinal(dec);
            // Decode using utf-8
            texto =new String(utf8, "UTF8"); 
        } catch (BadPaddingException e) {
        } catch (IllegalBlockSizeException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        }
        return texto;
    }
}
