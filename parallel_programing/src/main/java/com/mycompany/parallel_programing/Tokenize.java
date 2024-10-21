package com.mycompany.parallel_programing;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.List;

public class Tokenize {
    
    public static void main(String[] args)  {
        
        StanfordCoreNLP stanforCoreNLP = Pipeline.getPipeline();
        
        String text = "merhaba adam";
        CoreDocument coreDocument = new CoreDocument(text);
        stanforCoreNLP.annotate(coreDocument);
        List<CoreLabel> coreLabelList = coreDocument.tokens();
        for(CoreLabel coreLabel : coreLabelList){
           System.out.println(coreLabel.originalText());
        }
        
    }
}
