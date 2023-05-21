package CaesarCipher;

import Constants.Alphabets;
import Constants.CaesarOperation;
import Utils.FileService;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Cipher {
    private CaesarOperation action;
    private Alphabets alphabet;
    private FileService fileService;
    private int key;

    public Cipher(){

    }
    public Cipher(String path){
        this.action = CaesarOperation.BRUTE_FORCE;
        this.key = -1;
        alphabet = new Alphabets();
        fileService = new FileService(path, this.action);
    }
    public Cipher(String path, CaesarOperation action, int key) {
        this.action = action;
        this.key = key;
        alphabet = new Alphabets();
        fileService = new FileService(path, this.action);

    }

    public  Alphabets getAlphabet(){
        return this.alphabet;
    }

    public int getKey(){
        return this.key;
    }

    protected void setKey(int key){
        this.key = key;
    }

    protected FileService getFileService(){
        return this.fileService;
    }

    public void run(){
        try {

            int currentChar = fileService.readChar();
            while (currentChar != -1) {
                char shiftedChar = alphabet.getCharForPosition((char) currentChar, key);
                fileService.writeChar(shiftedChar);
                currentChar = fileService.readChar();
            }
            fileService.flush();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }


}
