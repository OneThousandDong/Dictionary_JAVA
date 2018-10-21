package dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;


public class DictionaryCommandline
{
    
    public static final Scanner scanner = new Scanner(System.in);

    public void showAllWords(Map<String, String> map)
    {
        int No = 0;
        System.out.printf("%-12s%-24s%s\n\n", "No", "|English", "|Vietnamese");
        Map<String, String> treeMap = new TreeMap<String, String>(map);

        for (Map.Entry<String, String> entry : treeMap.entrySet())
        {
            String s= String.valueOf(entry.getKey().charAt(0)).toUpperCase() + entry.getKey().substring(1);
            No++;
            System.out.printf("%-12s%-24s%s\n", No, "|" + s, "|" + entry.getValue());
        }
    }
    public void dictionaryAdvanced() {
        DictionaryManagement dictionaryManagement = new DictionaryManagement();
        Dictionary dictionary = new Dictionary();
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        dictionary.saveWord(map);

        int luachon;
        try {
            do {
                System.out.printf("%70s\n", "=================DICTIONARY=============");
                System.out.printf("%60s\n", "== 1.Thêm dữ liệu từ bàn phím.");
                System.out.printf("%70s\n", "== 2.Xem danh sách các từ(ShowAllWords).");
                System.out.printf("%67s\n", "== 3.Tìm kiếm từ(DictionarySearcher).");
                System.out.printf("%64s\n", "== 4.Tra cứu từ(DictionaryLookup).");
                System.out.printf("%42s\n", "== 5.Xóa từ.");
                System.out.printf("%64s\n", "== 6.Xuất dữ liệu từ điển ra file.");
                System.out.printf("%40s\n", "== 7.EXIT.");
                System.out.printf("%70s\n", "===================END==================");
                System.out.println("Nhập lựa chọn các số từ 1 - 7 ");
                luachon = scanner.nextInt();
                switch (luachon) {
                    case 1:
                        System.out.print("Nhập từ Tiếng Anh muốn thêm: ");
                        scanner.nextLine();
                        String addword = scanner.nextLine();
                        System.out.print("Nhập phát âm(nếu có) và nghĩa Tiếng Việt: ");
                        String addexplain = scanner.nextLine();
                        String s1 = addword + " " + addexplain;
                        map.put(addword, addexplain);
                        dictionaryManagement.addWriteFile(s1);
                        break;
                    case 2:
                        dictionaryCommandline.showAllWords(map);
                        break;
//                    case 3:
//                                System.out.println("Nhập từ cần tra cứu ");
//                               String  word = dictionaryManagement.insertFromCommandline();
//                        LinkedHashMap<String, String> mapvalue = dictionaryCommandline.dictionarySearcher(map,word);
//                        
//                        if (mapvalue.isEmpty()) {
//                            System.out.println("Không có từ cần tra");
//                        } else {
//                            Set<String> getkey = mapvalue.keySet();
//                            for (String key : getkey) {
//                                System.out.printf("%-12s%-24s\n", "|" + key, "|" + mapvalue.get(key));
//                            }
//                        }
//                        break;
                    case 4:
                        String s4 = dictionaryManagement.dictionaryLookup(map);
                        System.out.println(s4);
                        break;
                    case 5:
                        dictionaryManagement.removeWord(map);
                        System.out.println("Đã xóa thành công");
                        break;
                    case 6:
                        dictionaryManagement.dictionaryExportToFile(map);
                        System.out.println("Xuất dữ liệu ra file thành công");
                        break;
                    case 7:
                        luachon = 7;
                        break;
                }
                if (luachon == 7)
                    break;
            } while (luachon >= 1 && luachon <= 7);
        }catch (Exception e)
        {
            System.out.println("Có vẻ như bạn không nhập đúng yêu cầu, vui lòng nhập đúng các số từ 1 - 6 , vì đã nhập sai nên chương trình sẽ tự thoát.XIN CẢM ƠN!!");
        }
    }


    //public LinkedHashMap<String,String>dictionarySearcher(LinkedHashMap<String,String>map, String word)
    public int dictionarySearcher(LinkedHashMap<String,String>map, String word)
    {
        //DictionaryManagement dictionaryManagement = new DictionaryManagement();
//        System.out.println("Nhập từ cần tra cứu ");
//         word = dictionaryManagement.insertFromCommandline();
        int location = 0;
        //LinkedHashMap<String,String> mapvalue = new LinkedHashMap<String,String>();
//        Set<String> getkey = map.keySet();
//        for(String key : getkey)
//        {
//            int lent = word.length();
//            if(lent <= key.length())
//            {
//                String s = key.substring(0, lent);
//
//                if (s.equals(word)) 
//                {
//                   // mapvalue.put(key,map.get(key));
//                    location = new ArrayList<String>(map.keySet()).indexOf(s);
//                    return location;
//                }
//            }
//        }
            Set set = map.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext())
            {
                        Map.Entry entry = (Map.Entry)iterator.next();
                        int lent = word.length();
                     String ss = (String)entry.getKey();
                        int ssLeng = ss.length();
                
                    if(lent <= ssLeng)
                    {
                        String s = ss.substring(0, lent);

                        if (s.equals(word)) 
                        {
                           // mapvalue.put(key,map.get(key));
                            location = new ArrayList<String>(map.keySet()).indexOf(ss);
                            return location;
                        }
                    }
             }
   
        //return mapvalue;
             
            return location;     
    }
}

