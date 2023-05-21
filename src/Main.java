import CaesarCipher.BruteForce;
import CaesarCipher.Cipher;
import CaesarCipher.Decrypt;
import CaesarCipher.Encrypt;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        int key = 0;
        if(args.length < 2){
            System.out.println("Use <java -jar myApp.jar command filePath key>, where command ENCRYPT, DECRYPT or BRUTE_FORCE without key");
            return;
        }
        List<String> arr = new ArrayList<String>(Arrays.asList("ENCRYPT", "DECRYPT", "BRUTE_FORCE"));
        if(!arr.contains(args[0])){
            System.out.println("Use command ENCRYPT, DECRYPT or BRUTE_FORCE");
        } else if (Files.notExists(Path.of(args[1]))) {
            System.out.println("File" + args[1] + " is not exist!");
            return;
        } else if (args.length == 3) {
            try {
                key = Integer.parseInt(args[2]);
            }catch (NumberFormatException e){
                System.out.println("Invalid key format, must be a number");
            }
        }
        Cipher cipher;
        switch (args[0]){
            case "ENCRYPT":
                cipher = new Encrypt(args[1], key);
                cipher.run();
                break;
            case "DECRYPT":
                cipher = new Decrypt(args[1], key);
                cipher.run();
                break;
            case "BRUTE_FORCE":
                cipher = new BruteForce(args[1]);
                cipher.run();
                break;


        }
    }
}