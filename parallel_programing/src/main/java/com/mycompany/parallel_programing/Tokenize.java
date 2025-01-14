package com.mycompany.parallel_programing;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import zemberek.morphology.TurkishMorphology;
import zemberek.morphology.analysis.SingleAnalysis;
import zemberek.normalization.TurkishSentenceNormalizer;

public class Tokenize{
    
    private TurkishMorphology morphology;
    private TurkishSentenceNormalizer normalizer;
    
    public Tokenize() throws IOException{
        morphology = TurkishMorphology.createWithDefaults();
        
        Path lookupRoot = Paths.get("/Users/seros/Desktop/parallel_programing/parallel_programing/src/main/java/com/mycompany/parallel_programing/normalization");
        Path lmFile = Paths.get("/Users/seros/Desktop/parallel_programing/parallel_programing/src/main/java/com/mycompany/parallel_programing/lm/lm.2gram.slm/");
        normalizer = new TurkishSentenceNormalizer(morphology, lookupRoot, lmFile);
    }
    
    public ArrayList<ArrayList<String>> preprocessing(ArrayList<String> cumleler){
        ArrayList<ArrayList<String>> islenmisCumleler = new ArrayList<>();
        
        try{
            for (int i = 0; i < cumleler.size(); i++) {
            String cumle = cumleler.get(i);
            //System.out.println("Cümle " + (i + 1) + ": " + cumle);
            cumle = cumleKucult(cumle);
            String normalizeKelimeler = normalization(cumle);
            //System.out.println("Normalize cümle " + (i + 1) + ": " + cumle);
            String[] kelimeler = kelimelereAyir(normalizeKelimeler);           
            ArrayList<String> islenmisKelimeler = new ArrayList<>();
            try{

                for (String kelime : kelimeler) {                 
                    String temizKelime = delNoktalama(kelime);
                    String kok = delEk(temizKelime);
                    islenmisKelimeler.add(kok);
                    //System.out.println("kok: "+ islenmisKelimeler);
                }           
            }catch(Exception e){
                
            }
            islenmisCumleler.add(islenmisKelimeler);
            //System.out.println("islenmis: " + islenmisCumleler);
            }
        }catch(Exception ex){
          
        }
        
        System.out.print("bitttti");
        return islenmisCumleler;
    }
        
    public String cumleKucult(String cumle){
        String islenmisCumle = cumle.toLowerCase();
        return islenmisCumle;
    }
    
    public String[] kelimelereAyir(String cumle) {
        // Boşluk ve birden fazla beyaz alan karakterine göre bölme
        return cumle.trim().split("\\s+");
    }    
        
    public String delNoktalama(String kelime){
        String temizKelime = kelime.replaceAll("[^a-zA-ZçğıöşüÇĞİÖŞÜ]", "");
        return temizKelime;
    }
    
    public String normalization(String cumle){
        String normalizeCumle = normalizer.normalize(cumle);
        return normalizeCumle;
    }
    
    public String delEk(String kelime){
        List<SingleAnalysis> analizSonuclari = morphology.analyze(kelime).getAnalysisResults();
        String kok = analizSonuclari.get(0).getStem();
        return kok;
    }
    
}
