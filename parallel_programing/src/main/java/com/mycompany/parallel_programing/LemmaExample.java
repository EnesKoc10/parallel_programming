/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parallel_programing;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.List;

/**
 *
 * @author Enes
 */
public class LemmaExample {
    public static void main(String[] args){
        
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "Hey bro trying added has professional going being.";
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        for(CoreLabel coreLabel : coreLabelList){
            String lemma = coreLabel.lemma();
            System.out.println(coreLabel.originalText() + " == "+ lemma);
        }
    }
}
