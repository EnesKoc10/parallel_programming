package com.mycompany.parallel_programing;



import java.io.File;
import java.io.IOException;
import java.net.URI;
import org.datavec.api.split.FileSplit;

public class ReadCSV {

    public static void main(String[] args) throws IOException {
        c dataLocalPath = DownloaderUtility.INPUTSPLIT.Download();
        File directoryToLook = new File(dataLocalPath, "files");

        //=====================================================================
        //                 Example 1: Loading everything within
        //=====================================================================

        /*
          This will gather all the loadable files within the specified directory. By default it will load all the files
          regardless of the extensions they have. Also, it will search for the inner directories recursively for
          further loadable files.
         */
        FileSplit fileSplit1 = new FileSplit(directoryToLook);

        /*
          We can view the files in the file split by using the FileSplit#locations function
         */

        System.out.println("--------------- Example 1: Loading every file ---------------");
        URI[] fileSplit1Uris = fileSplit1.locations();
        for (URI uri : fileSplit1Uris) {
            System.out.println(uri);
        }
        System.out.println("------------------------------------------------------------\n\n\n");
        }
    }
