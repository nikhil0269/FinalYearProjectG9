//package algorithms;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class ThripleDESBase64 {
//        private static String key;
        public static String encrypt(String base64Key, String plainText) throws Exception {
            final MessageDigest md = MessageDigest.getInstance("md5");
//            final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
//                    .getBytes("utf-8"));

            final SecretKey key = new SecretKeySpec(
                    Base64.getDecoder().decode(base64Key), "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

            final byte[] plainTextBytes = plainText.getBytes("utf-8");
            final byte[] cipherText = cipher.doFinal(plainTextBytes);
            String base64EncodedCipherText = Base64.getEncoder().encodeToString(cipherText);
            return base64EncodedCipherText;
        }

        public static String decrypt(String base64Key, String base64CipherText) throws Exception {
            byte[] cipherbytes = Base64.getDecoder().decode(base64CipherText);
            final MessageDigest md = MessageDigest.getInstance("md5");
//            ede
            final SecretKey key = new SecretKeySpec(
                    Base64.getDecoder().decode(base64Key),
                    "DESede"
            );
//            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher decipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
//            decipher.init(Cipher.DECRYPT_MODE, key, iv);
            decipher.init(Cipher.DECRYPT_MODE, key);
            final byte[] plainText = decipher.doFinal(cipherbytes);

            return new String(plainText, "UTF-8");
        }




    public String readFile(String file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader (file));
    String         line = null;
    StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    try {
        while((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        return stringBuilder.toString();
    } finally {
        reader.close();
    }
}



	public static void main(String args[]) throws Exception
	{
	ThripleDESBase64 a=new ThripleDESBase64();	
	String input = "3DES is another strong sync encryption algo";
        String DESbase64Key = "6lRqh2yaYyc1NV2oCcqltC0hYp/9Lnn5";
	input=a.readFile("20mb.txt");
        String encryptedBase63_3DES = ThripleDESBase64.encrypt(DESbase64Key, input);
        //String decrypted3DES = ThripleDESBase64.decrypt(DESbase64Key, encryptedBase63_3DES);
        //System.out.println("3DES test result:" + decrypted3DES);
	}
    }
