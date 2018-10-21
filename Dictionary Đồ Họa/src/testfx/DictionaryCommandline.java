package testfx;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class DictionaryCommandline {

    public DictionaryManagement test = new DictionaryManagement();
    public Scanner input = new Scanner(System.in);

    public void showAllWords() {
        System.out.println("No      English     Vietnamese");

        for (int i = 0; i < test.ver1.getList().size(); i++) {
            int j=i+1;
            System.out.println(j + "      " + test.ver1.getList().get(i).getWord_Target() + "        " + test.ver1.getList().get(i).getWord_Explain());
        }
    }

    public void dictionarySearcher(String word) {

        for (int i = 0; i < test.sizedict; i++) {
            if (test.ver1.getList().get(i).getWord_Target().indexOf(word) == 0) {
                System.out.println(test.ver1.getList().get(i).getWord_Target());

            }
        }
    }

    public void dictionaryBasic() {
        test.insertFromCommandline();
        this.showAllWords();
    }

    public void dictionaryAdvanced() throws IOException {
        test.insertFromFile();
//       this.showAllWords();
//        test.addtodictionary();
//        this.showAllWords();
System.out.println("==========Dictionary==========");
        while (true) {
            System.out.println("    1.Add Word.");
            System.out.println("    2.Show all word.");
            System.out.println("    3.Search word.");
            System.out.println("    4.Look up word.");
            System.out.println("    5.Delete word.");
            System.out.println("    6.Repair word.");
            System.out.println("    7.Exit.");
            int n;
            n = input.nextInt();
            switch (n) {
                case 1: {
                    System.out.println("How many word do you want to add : ");
                    int num;
                    num = input.nextInt();
                    for (int i = 0; i < num; i++) {
//                        test.addtodictionary();
                    }
                } ;break;
                case 2: {
                    this.showAllWords();break;
                }
                case 3: {
                    System.out.println("Word want to search : ");
                    String word;
                    word = input.next();
                    this.dictionarySearcher(word);
                } ;break;
                case 4: {
                    System.out.println("Word want to lookup : ");
                    String wordsearch;
                    wordsearch = input.next();
                    if (test.dictionaryLookup(wordsearch) == -1) {
                        System.out.println("Not found!");
                    } else {
                        System.out.println(test.ver1.getList().get(test.dictionaryLookup(wordsearch)).getWord_Explain());
                    }

                } ;break;
                case 5: {
                    System.out.println("Word want to delete : ");
                    String word = input.next();
                    if (test.dictionaryLookup(word) == -1) {
                        System.out.println("Not found!");
                    } else {
                        test.deletefromDictionary(word);
                        System.out.println("Success!");
                    }
                } ;break;
                case 6 : {
                    System.out.println("Word want to repair : ");
                    String word = input.next();
                    if(test.dictionaryLookup(word) == -1)
                    {
                        System.out.println("Not found!");
                    }
                    else{
                        
                        System.out.println("It mean : ");
                        input.nextLine();
                        String wordexplain = input.nextLine();
                        test.repairfromDictionary(word,wordexplain);
                        System.out.println("Success!");
                    } ; break;
                        
                }
                case 7 : n=7; break;
            }
         
            if(n==7) break;
            test.dictionaryExportToFile();
        }
    }
}
