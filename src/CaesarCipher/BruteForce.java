package CaesarCipher;

import Constants.Alphabets;
import Constants.CaesarOperation;
import Utils.FileService;

import java.io.IOException;

public class BruteForce extends Cipher{
    public BruteForce(String path){
        super(path);
    }
    @Override
    public void run(){
        FileService fileService = super.getFileService();
        Alphabets alphabet = super.getAlphabet();
        int key = super.getKey();
        int maxKey = -100;
        while (key >= maxKey) {
            if(alphabet.getCurrentAlphabetsLen() !=-1){
                maxKey = alphabet.getCurrentAlphabetsLen() * -1;
            }
            try {
                fileService.writeString("\n----------------BRUTE_FORCE key = " + key + "----------------\n");
                int currentChar = fileService.readChar();
                while (currentChar != -1) {
                    char shiftedChar = alphabet.getCharForPosition((char) currentChar, key);
                    fileService.writeChar(shiftedChar);
                    currentChar = fileService.readChar();
                }
                fileService.flush();
                fileService.resetFileReader();
                key--;
            } catch (IOException exception) {
                exception.printStackTrace();
            }

        }
    }




}
