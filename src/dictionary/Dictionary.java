package dictionary;

import java.io.BufferedReader;
import java.io.FileReader;

import java.util.*;
public class Dictionary {
    public void saveWord(LinkedHashMap<String,String> map)
    {
        Word w = new Word();
        FileReader fr = null;
        BufferedReader br = null;
        try{
             fr = new FileReader("dictionaries.txt");
             br = new BufferedReader(fr);

            String readWord;
            while ((readWord = br.readLine()) != null) {
                int j=0;
                for(int i=0; i<readWord.length(); i++) {
                    j++;
                    if(Character.isSpaceChar(readWord.charAt(i))) {
                        break;
                    }
                }
                String tmpWord = readWord;
                w.word_target = tmpWord.substring(0,j-1);
                w.word_explain= readWord.substring(j);
                map.put(w.word_target, w.word_explain);
            }
            br.close();
            fr.close();
        }catch (Exception e){
            System.out.println("Chưa có file dictionaries.txt để thực hiện tra cứu từ");
        }

    }
}
