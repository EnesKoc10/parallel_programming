/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parallel_programing;

import java.io.IOException;
import java.util.ArrayList;

public class convertTfidf {
    
    public ArrayList<ArrayList<Double>> CalculateTFIDF(ArrayList<ArrayList<String>> tokenizedWord) {
        String line = null;
        int numLine = 0;
        ArrayList<ArrayList<String>> tfList = new ArrayList<>();
        ArrayList<ArrayList<Double>> tfidfList = new ArrayList<>();
        ArrayList<String> uniqueTerms = new ArrayList<>();
        ArrayList<Integer> dfList = new ArrayList<>();
        for (int i = 0; i < tokenizedWord.size(); i++) {
            //line = tokenizedWord.get(i);
            //String[] splitedLine = line.split(" ");
            ArrayList<String> currentTerms = new ArrayList<>();
            ArrayList<Double> currentTF = new ArrayList<>();

            for (String word : tokenizedWord.get(i)) {
                if (!currentTerms.contains(word)) {
                    currentTerms.add(word);
                    currentTF.add(1.0);

                    // Document Frequency (DF) güncellemesi
                    if (uniqueTerms.contains(word)) {
                        int index = uniqueTerms.indexOf(word);
                        dfList.set(index, dfList.get(index) + 1);
                    } else {
                        uniqueTerms.add(word);
                        dfList.add(1);
                    }
                } else {
                    int index = currentTerms.indexOf(word);
                    currentTF.set(index, currentTF.get(index) + 1);
                }
            }

            tfList.add(currentTerms);
            tfidfList.add(new ArrayList<>(currentTF)); // TFIDF hesaplanırken güncellenecek

            numLine++;
            if (numLine % 499 == 0) {
                System.out.println(numLine);
            }
        }
        // TFIDF Hesaplama
        for (int i = 0; i < tfList.size(); i++) {
            ArrayList<String> terms = tfList.get(i);
            ArrayList<Double> tfidfValues = tfidfList.get(i);

            for (int j = 0; j < terms.size(); j++) {
                String term = terms.get(j);
                double tf = Math.log(1 + tfidfValues.get(j));
                int dfIndex = uniqueTerms.indexOf(term);
                double idf = Math.log((double) numLine / dfList.get(dfIndex));
                tfidfValues.set(j, tf * idf);
            }
        }

        // Sonuçların yazdırılması
        /*for (int i = 0; i < tfList.size(); i++) {
            System.out.println("Line " + (i + 1) + ": " + tfList.get(i));
            System.out.println("TF-IDF: " + tfidfList.get(i));
        }*/
        return tfidfList;
    }
}
