package com.mycompany.parallel_programing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



public class main{
    
    private static final List<ArrayList<String>> islenmis = Collections.synchronizedList(new ArrayList<>());
    
     public static void main(String[] args) throws IOException, InterruptedException  { 
        String[][] cumleler0 = {
            {"yapıyorum ediyorum yolluyorum etmiyorum."},
            {"Bugün hava çok güzel."},
            {"Bilgisayar mühendisliği okumak istiyorum."}
        };
        String[][] cumleler1 = {
            {"yapıyorum ediyorum yolluyorum etmiyorum."},
            {"Bugün hava çok güzel."},
            {"Bilgisayar mühendisliği okumak istiyorum."}
        };
        String[][] cumleler2 = {
            {"yapıyorum ediyorum yolluyorum etmiyorum."},
            {"Bugün hava çok güzel."},
            {"Bilgisayar mühendisliği okumak istiyorum."}
        };
        String[][] cumleler3 = {
            {"yapıyorum ediyorum yolluyorum etmiyorum."},
            {"Bugün hava çok güzel."},
            {"Bilgisayar mühendisliği okumak istiyorum."}
        };       

        Tokenize tokenize = new Tokenize();
        Thread t1 = new Thread(() -> process(cumleler0, tokenize));
        Thread t2 = new Thread(() -> process(cumleler1, tokenize));
        Thread t3 = new Thread(() -> process(cumleler2, tokenize));
        Thread t4 = new Thread(() -> process(cumleler3, tokenize));
        
        // Thread'leri başlatma
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // Ana thread diğer thread'lerin tamamlanmasını bekler
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        
        System.out.println("İşlenmiş veriler:");
        for (ArrayList<String> list : islenmis) {
            System.out.println(list);
        }
        
        //Word2vec vec = new Word2vec();
        //vec.getVec(islenmis);
        
        //for (int i = 0; i < islenmis.size(); i++) {
        //    System.out.println("Cümle " + (i + 1) + ": " + islenmis.get(i));
        //}
     }
     
      private static void process(String[][] cumleler, Tokenize tokenize) {
        ArrayList<ArrayList<String>> sonuc = tokenize.preprocessing(cumleler);
        synchronized (islenmis) {
            islenmis.addAll(sonuc);  // Senkronize ekleme işlemi
        }
    }
}
