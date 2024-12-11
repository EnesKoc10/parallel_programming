package com.mycompany.parallel_programing;


import java.io.File;
import java.util.ArrayList;
import org.deeplearning4j.datasets.iterator.utilty.ListDataSetIterator;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.evaluation.classification.Evaluation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.SplitTestAndTrain;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;


public class NeuralNetwork {

    public double createNeuralNetwork( ArrayList<ArrayList<Double>> inputArray, ArrayList<Integer> label) throws Exception {
        final String SIMPLE_MODEL = new File("C:\\Users\\seros\\Desktop\\parallel_programing\\parallel_programing\\src\\main\\java\\com\\mycompany\\parallel_programing\\simple_model.h5").getAbsolutePath();

        // Keras Sequential models correspond to DL4J MultiLayerNetworks. We enforce loading the training configuration
        // of the model as well. If you're only interested in inference, you can safely set this to 'false'.
        MultiLayerNetwork model = KerasModelImport.importKerasSequentialModelAndWeights(SIMPLE_MODEL, true);
        System.out.println("hay dedeaasda");
        System.out.println("arrray: " + inputArray.size());
        // Alt listeler arasında maksimum boyutu bul
        // Sabit giriş boyutu
        int fixedInputSize = 200;

        // Sabit boyutlu bir double[][] oluştur ve eksik değerleri doldur
        double[][] data = new double[inputArray.size()][fixedInputSize];

        for (int i = 0; i < inputArray.size(); i++) {
            ArrayList<Double> row = inputArray.get(i);
            for (int j = 0; j < fixedInputSize; j++) {
                if (j < row.size()) {
                    data[i][j] = row.get(j); // Orijinal değeri al
                } else {
                 data[i][j] = 0.0; // Eksik değerleri sıfırla doldur
                }
            }
        }
             
        System.out.println("size kac: "+ data.length);
        
        int numClasses = 3; // Toplam sınıf sayısı

        // One-hot encoding için 2D array oluştur
        int[][] label_array = new int[label.size()][numClasses];

        // Her bir etiket için one-hot encoding işlemi
        for (int i = 0; i < label.size(); i++) {
            int numericLabel = label.get(i); // label'deki etiketi al
            label_array[i][numericLabel] = 1; // İlgili sütunu 1 yap
        }
        
        // ND4J'nin INDArray nesnesi oluşturuluyor
        INDArray input = Nd4j.create(data);
        INDArray labels = Nd4j.create(label_array);
        
        // Dataset oluşturma
        DataSet dataSet = new DataSet(input, labels);
        
        // Eğitim ve doğrulama oranları
        double trainRatio = 0.8;
        
        // Veri setini böl
        SplitTestAndTrain split = dataSet.splitTestAndTrain(trainRatio);
        DataSet trainData = split.getTrain();
        DataSet validationData = split.getTest();
        
        // Eğitim ve doğrulama iteratörleri
        DataSetIterator trainIter = new ListDataSetIterator<>(trainData.asList(), 2); // Mini-batch size: 2
        DataSetIterator validationIter = new ListDataSetIterator<>(validationData.asList(), 2);
        
        // Test basic model training.
        model.fit(trainIter);
        System.out.println("Oluyooooooooooooo");
        
        // Doğrulama sonuçlarını değerlendirme
        Evaluation eval = new Evaluation();
        while (validationIter.hasNext()) {
            DataSet batch = validationIter.next();
            INDArray predicted = model.output(batch.getFeatures());
            eval.eval(batch.getLabels(), predicted);
        }  
        
        return eval.accuracy();
    }
}