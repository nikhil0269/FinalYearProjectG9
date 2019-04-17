//package algorithms;
import java.io.*;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Random;

public class AES {
        public static String initVector = "dec07f6e-3b47-42";
//        public static String key = "passwordpassword";
        public static String getKey(String sessionId){
            return sessionId.substring(0, 16);
        }

        public static String encrypt(String key, String initVector, String value) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

                byte[] encrypted = cipher.doFinal(value.getBytes());
                System.out.println("encrypted string: "+Base64.getEncoder().encodeToString(encrypted));

                return Base64.getEncoder().encodeToString(encrypted);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static byte[] encrypt(String key, String initVector, byte[] value) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

                byte[] encrypted = cipher.doFinal(value);

                return encrypted;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static String decrypt(String key, String initVector, String encrypted) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
                byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

                return new String(original);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static byte[] decrypt(String key, String initVector, byte[] encrypted) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
                byte[] original = cipher.doFinal(encrypted);

                return original;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        public static String realContent(String content){
            return content.substring(content.indexOf(" ") + 1, content.length());
        }

        public static String getRandomKey() {
            return getRandomStr(16);
        }

        public static String getRandomStr(int strLen){
            char[] chars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxwz!@#$%^&*()=-".toCharArray();
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < strLen; i++) {
                char c = chars[random.nextInt(chars.length)];
                sb.append(c);
            }
            return  sb.toString();
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

//write to the file

public String writeFile(byte[] encrypted) throws IOException
{



 String FILEPATH = "encryptedFile"; 
 File file = new File(FILEPATH); 

	try {

            // Initialize a pointer
            // in file using OutputStream
            OutputStream os = new FileOutputStream(file);

            // Starts writing the bytes in it
            os.write(encrypted);
            //System.out.println("Successfully"
              //                 + " byte inserted");

            // Close the file
            os.close();
        }

        catch (Exception e) {
            System.out.println("Exception: " + e);
        }

return null;
}

	public static void main(String args[]) throws Exception
	{
		//------ AES
        String input = "AES is strongest sync encryption algo";
        //initialVector.length = key.length
	AES a=new AES();
	String file="50kb.txt";
	input=a.readFile(file);
	

        String key = "1231231231231231";
        String initialVector = "dect7f6e-3b47ge2";

        byte[] encrypted = AES.encrypt(key, initialVector, input.getBytes());
	a.writeFile(encrypted);
        //byte[] decrypted = AES.decrypt(key, initialVector, encrypted);
        //System.out.println("AES test result: " + new String(decrypted));
	}
    }
