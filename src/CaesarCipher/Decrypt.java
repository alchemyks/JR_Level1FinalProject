package CaesarCipher;

import Constants.CaesarOperation ;

public class Decrypt extends Cipher{
    public Decrypt(String path, int key){
        super(path, CaesarOperation.DECRYPT, key * -1);
    }
}
