package testfx;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Admin
 */
public class Word {
    private String word_target;
    private String word_explain;
    public Word(String a,String b)
    {
        word_target=a;
        word_explain=b;
    }
    public String getWord_Target()
    {
        return word_target;
    }
    public void setWord_Target(String s)
    {
        word_target=s;
    }
    public String getWord_Explain()
    {
        return word_explain;
    }
    public void setWord_Explain(String s)
    {
        word_explain=s;
    }
}
