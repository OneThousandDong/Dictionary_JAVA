package dictionary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class DictionaryManagement {

    public static final Scanner scanner = new Scanner(System.in);

    public String insertFromCommandline()
    {
        String word_target;
        word_target = scanner.nextLine();
        word_target = word_target.trim();
        word_target = word_target.toLowerCase();
        return word_target;
    }

    public String dictionaryLookup(LinkedHashMap<String,String>map)
    {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        System.out.println("Nhập từ cần dịch(Anh-Việt) ");
        String enterWord = dictionaryManagement.insertFromCommandline();
        String s;
        if(map.containsKey(enterWord))
        {
            s = enterWord + " : " + map.get(enterWord);
        }
        else
        {
            s = "Không có từ cần tra";
        }
        return s;
    }

    public void dictionaryExportToFile(Map<String,String>map)
    {

        File file = new File("1.txt");
        FileWriter fr = null;
        BufferedWriter br = null;
        try{
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);

            for(Map.Entry<String,String>entry: map.entrySet()) {
                String dataWithNewLine = entry.getKey()+" "+entry.getValue()+System.getProperty("line.separator");
                br.write(dataWithNewLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void removeWord(Map<String,String> map)
    {
        System.out.print("Nhập từ Tiếng Anh muốn xóa: ");
        String s = scanner.nextLine();
        if(map.containsKey(s))
        {
            map.remove(s);
        }
        else
        {
            System.out.println("Từ "+ s + " Không có trong từ điển để xóa");
        }
    }

    public void addWriteFile(String s)
    {
        try {
            File file = new File("dictionaries.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getName(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            String data = s + System.getProperty("line.separator");
            bw.write(data);
            bw.close();
            //System.out.println("Đã Thêm thành công");
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

}

