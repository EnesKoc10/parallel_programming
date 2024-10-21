package com.mycompany.parallel_programing;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.util.Properties;

/**
 *
 * @author Enes
 */
public class Pipeline {
	private static Properties prop;
	private static String propName = "tokenize,ssplit,pos,lemma";

	private static StanfordCoreNLP stanford;

	private Pipeline() {

	}

	static {
		prop = new Properties();
		prop.setProperty("annotators", propName);
	}

	public static StanfordCoreNLP getPipeline() {
		if (stanford == null) {
			stanford = new StanfordCoreNLP(prop);
		}
		return stanford;
	}
}