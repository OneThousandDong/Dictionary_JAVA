package testfx;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.Scanner;
import java.util.Collections;

/**
 *
 * @author Admin
 */
public class DictionaryManagement {

    public Dictionary ver1 = new Dictionary();
    public int sizedict = 0;
    private Scanner input = new Scanner(System.in);

    public void insertFromCommandline() {

        System.out.println("Số từ muốn thêm là : ");
        int wordnumber = Integer.parseInt(input.nextLine());
        for (int i = 0; i < wordnumber; i++) {
            String EnglishWord = input.next();
            String VNmean = input.nextLine();
            Word tmp = new Word(EnglishWord, VNmean);
            ver1.addlist(tmp);
            sizedict++;
        }

    }

    public void insertFromFile() {

        final String filepath = "dictionaries.txt";
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(filepath);
            br = new BufferedReader(fr);
            String currentLine = null;
            while ((currentLine = br.readLine()) != null) {
                String[] read_word = currentLine.split("\t", 2);
                String word_target = "";
                String word_explain = "";
                if (read_word.length == 1) {
                    word_target = read_word[0];
                } else {
                    word_target = read_word[0];
                    word_explain = read_word[1];
                }
                ver1.addlist(new Word(word_target,word_explain));
                sizedict++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public int dictionaryLookup(String word) {
        for(int i=0;i<sizedict;i++)
        {
            if(word.equals(ver1.getList().get(i).getWord_Target())==true)
                return i;
        }
        return -1;
    }

    public void dictionaryExportToFile() throws IOException {
        File file = new File("fileout.txt");
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);) {
            for (int i = 0; i < sizedict; i++) {
                fileOutputStream.write(ver1.getList().get(i).getWord_Target().getBytes());
                String str1 = " ";
                String str2 = "/r/n";
                fileOutputStream.write(str1.getBytes());
                fileOutputStream.write(ver1.getList().get(i).getWord_Explain().getBytes());
                fileOutputStream.write(str2.getBytes());

            }
            fileOutputStream.flush();
        } catch (Exception e) {
        }

    }

    public void addtodictionary(String add_target,String add_explain) {
////        String add_explain;
////        String add_target;
////        System.out.println("English word : ");
////        add_target = input.next();
////        input.nextLine();
////        System.out.println("Vietnamese mean : ");
//        add_explain = input.nextLine();

        Word addword = new Word(add_target, add_explain);

//        if (this.dictionaryLookup(add_target) == -1) {
            for (int i = 0; i < sizedict; i++) {

                if (add_target.compareTo(ver1.getList().get(i).getWord_Target()) > 0 && add_target.compareTo(ver1.getList().get(i + 1).getWord_Target()) < 0) {
                    ver1.getList().add(i + 1, addword);
                    break;
                }

            }
 //           System.out.println("Success!");
            sizedict++;
//        } else {
//            System.out.println("This word has been in dictionary before!");
        }
    

    public void deletefromDictionary(String deleteword) {

        ver1.getList().remove(this.dictionaryLookup(deleteword));
        sizedict--;
    }

    public void repairfromDictionary(String word_target, String repairword) {
//           System.out.println(this.dictionaryLookup(word_target));
        ver1.getList().get(this.dictionaryLookup(word_target)).setWord_Explain(repairword);
    }
    public void dictionarySearcher(String word) {

        for (int i = 0; i < sizedict; i++) {
            if (ver1.getList().get(i).getWord_Target().indexOf(word) == 0) {
                System.out.println(ver1.getList().get(i).getWord_Target());

            }
        }
    }
}
