package testfx;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Dictionary {

    private ArrayList<Word> list;
    
    public Dictionary() {
        this.list = new ArrayList<>();
    }

    public void addlist(Word a) {
        list.add(a);
    }

    public ArrayList<Word> getList() {
        return list;
    }

    public void setList(ArrayList a) {
        list = a;
    }
}
