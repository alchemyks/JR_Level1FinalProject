package Constants;

import java.util.*;
import java.util.stream.Collectors;

public class Alphabets {
    private Map <AlphabetsEnum, ArrayList<Character>>  alphabets;
    private ArrayList<Character> symbols;
    private AlphabetsEnum  currentAlphabets;


    private int currentAlphabetsLen;
    public Alphabets(){
        currentAlphabetsLen = -1;
        symbols = new ArrayList(Arrays.asList('.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '));
        alphabets = new HashMap<>();
        ArrayList<Character> uk = new ArrayList<Character>();
        ArrayList<Character> en = new ArrayList<Character>();
        for(char c: "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя".toCharArray()){
            uk.add(c);
        }
        for(char c: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray()){
            en.add(c);
        }
        alphabets.put(AlphabetsEnum.UK, uk);
        alphabets.put(AlphabetsEnum.EN, en);
    }

    public void setCurrentAlphabets(AlphabetsEnum currentAlphabets) {
        this.currentAlphabets = currentAlphabets;
    }



    public char getCharForPosition(Character character, int shift){
        if(symbols.contains(character)){
            int position = shift < 0? symbols.indexOf(character) + symbols.size():symbols.indexOf(character);
            position = (position + shift % symbols.size()) % symbols.size();
            return symbols.get(position);
        }

        if(currentAlphabets == null){
            for(Map.Entry<AlphabetsEnum, ArrayList<Character>> entry: alphabets.entrySet()){
                if(entry.getValue().contains(character)){
                    currentAlphabets = entry.getKey();
                    currentAlphabetsLen = entry.getValue().size();
                }
            }
        }

        Character out = character;
        if(alphabets.get(currentAlphabets).contains(character)){
            int position = alphabets.get(currentAlphabets).indexOf(character);
            position = ((position + shift % alphabets.get(currentAlphabets).size()) + alphabets.get(currentAlphabets).size()) % alphabets.get(currentAlphabets).size();
            out = alphabets.get(currentAlphabets).get(position);
        }
        return out;
    }

    public int getCurrentAlphabetsLen(){
        return this.currentAlphabetsLen;
    }

}
