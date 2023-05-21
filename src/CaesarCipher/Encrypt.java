package CaesarCipher;

import Constants.CaesarOperation;

public class Encrypt extends Cipher{
    public Encrypt(String path, int key){
        super(path, CaesarOperation.ENCRYPT, key);
    }


}
