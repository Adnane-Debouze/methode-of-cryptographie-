import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DESEncryptionExample {
    // Clé secrète doit être  8 octets pour DES
    private static final String SECRET_KEY = "MySecret"; 

  
    public static String encrypt(String strToEncrypt) throws Exception {
        // Préparer la clé
        SecretKey secretKey = new SecretKeySpec(
            SECRET_KEY.getBytes(StandardCharsets.UTF_8), 
            "DES"
        );

        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        
        byte[] encryptedBytes = cipher.doFinal(
            strToEncrypt.getBytes(StandardCharsets.UTF_8)
        );

        // Encoder en Base64 pour 
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    
    public static String decrypt(String strToDecrypt) throws Exception {
        // Préparer la clé
        SecretKey secretKey = new SecretKeySpec(
            SECRET_KEY.getBytes(StandardCharsets.UTF_8), 
            "DES"
        );

        
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        
        byte[] decryptedBytes = cipher.doFinal(
            Base64.getDecoder().decode(strToDecrypt)
        );

        
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

   
    public static void main(String[] args) {
        try {
            String originalString = "Bonjour, ceci est un test de chiffrement DES !";
            
            // Chiffrer
            String encrypted = encrypt(originalString);
            System.out.println("Chaîne chiffrée : " + encrypted);

            // Déchiffrer
            String decrypted = decrypt(encrypted);
            System.out.println("Chaîne déchiffrée : " + decrypted);
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}