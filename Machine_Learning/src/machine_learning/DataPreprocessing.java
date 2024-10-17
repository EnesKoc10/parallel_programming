package machine_learning;
/**
 *
 * @author Enes
 */
import java.util.ArrayList;
import java.util.List;

public class DataPreprocessing {
    
    public static void main(String[] args) {
        int[] data = new int[100];
        for (int i = 0; i < data.length; i++) {
            data[i] = i + 1; // Örnek veri seti
        }

        int numberOfThreads = 4; // Kullanmak istediğimiz thread sayısı
        List<Thread> threads = new ArrayList<>();
        int chunkSize = data.length / numberOfThreads;

        for (int i = 0; i < numberOfThreads; i++) {
            int start = i * chunkSize;
            int end = (i == numberOfThreads - 1) ? data.length : start + chunkSize;

            Thread thread = new Thread(new DataProcessor(data, start, end));
            threads.add(thread);
            thread.start();
        }

        // Tüm thread'lerin bitmesini bekleyin
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Veri ön işleme tamamlandı.");
    }
}

class DataProcessor implements Runnable {
    private int[] data;
    private int start;
    private int end;

    public DataProcessor(int[] data, int start, int end) {
        this.data = data;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = start; i < end; i++) {
            // Veri ön işleme işlemleri burada yapılır (örneğin normalizasyon)
            data[i] = data[i] * 2; // Örnek işlem: her elemanı 2 ile çarpma
            System.out.println("Thread " + Thread.currentThread().getName() + " veri[" + i + "] = " + data[i]);
        }
    }
}