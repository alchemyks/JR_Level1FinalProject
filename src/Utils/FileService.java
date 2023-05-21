package Utils;

import Constants.CaesarOperation;

import java.io.*;
import java.nio.file.Paths;

public class FileService {
    private CaesarOperation caesarOperation;
    private String filePath;
    private BufferedReader fileReader;
    private BufferedWriter fileWriter;
    public FileService(String filePath, CaesarOperation caesarOperation){
        try {
            this.filePath = filePath;
            fileReader = new BufferedReader(new FileReader(filePath));
            fileReader.mark(0);
        }catch (FileNotFoundException fileNotFoundException){
            System.out.println("File <" + filePath + "> is not found.\n" );
            fileNotFoundException.printStackTrace();
        }catch (IOException ioException){
            ioException.printStackTrace();
        }


        String fileOutName;
        if(caesarOperation == CaesarOperation.DECRYPT){
            fileOutName = filePath.replace(CaesarOperation.ENCRYPT + "", CaesarOperation.DECRYPT + "");
        }else {
            int indexLastPoint = filePath.lastIndexOf('.');
            fileOutName = filePath.substring(0, indexLastPoint) + "[" + caesarOperation + "]" + filePath.substring(indexLastPoint);
        }
        try {
            fileWriter = new BufferedWriter(new FileWriter(fileOutName));
        }catch (IOException ioException){
            System.out.println("File <" + fileOutName + "> ");
            ioException.printStackTrace();
        }
    }
    public int readChar() throws IOException{
        return fileReader.read();
    }
    public void writeChar(char symbol) throws IOException{
        fileWriter.write((int)symbol);
    }

    public void writeString(String str) throws IOException{
        fileWriter.write(str);
    }

    public void resetFileReader(){
        try{
        fileReader.close();
        fileReader = new BufferedReader(new FileReader(this.filePath));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void closeFiles() throws IOException{
        if(fileWriter != null){
            flush();
            fileWriter.close();
        }
        if(fileReader != null){
            fileReader.close();
        }
    }
    public void flush() throws IOException{
        if(fileWriter != null){
            fileWriter.flush();
        }
    }

}
