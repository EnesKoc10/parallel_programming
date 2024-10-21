package com.mycompany.parallel_programing;

import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.CoreSentence;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.List;

/**
 *
 * @author Enes
 */
public class SentenceRecognizer {
    
    public static void main(String[] args){
        
        StanfordCoreNLP stanfordCoreNLP = Pipeline.getPipeline();
        String text = "Hey! I am daniel micheal. I am software";
        CoreDocument coreDocument = new CoreDocument(text);
        stanfordCoreNLP.annotate(coreDocument);
        List<CoreSentence> sentences = coreDocument.sentences();
        
        for(CoreSentence sentence : sentences){
            System.out.println(sentence.toString());
        }
    }
}
