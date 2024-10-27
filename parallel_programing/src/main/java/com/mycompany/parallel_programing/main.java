package com.mycompany.parallel_programing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



public class main{
    
    
     public static void main(String[] args) throws IOException  { 
        String[][] cumleler = {
            {"yapıyorum ediyorum yolluyorum etmiyorum."},
            {"Bugün hava çok güzel."},
            {"Bilgisayar mühendisliği okumak istiyorum."}
        };
        
        ArrayList<ArrayList<String>> islenmis = new ArrayList<ArrayList<String>>();

        Tokenize tokenize = new Tokenize();
        islenmis = tokenize.preprocessing(cumleler);
        
        for (int i = 0; i < islenmis.size(); i++) {
            System.out.println("Cümle " + (i + 1) + ": " + islenmis.get(i));
        }
     }
}
