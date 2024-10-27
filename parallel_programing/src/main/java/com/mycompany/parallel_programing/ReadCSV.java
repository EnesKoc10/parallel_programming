/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parallel_programing;

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Enes
 */
public class ReadCSV {
    private static Scanner s;
    private static int numFields = 0;
    private static BufferedReader in = null;
    private static String[] fieldnames;
    private static int[] accumulators;
    private static String header;
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: Calculate <filename>");
            System.exit(1);
        }
        try {
            in = new BufferedReader(new
                    FileReader(args[0]));
            header = in.readLine(); // The first line
            numFields = calculateNumberOfFields();
            initializeArrays();
            String line;
            while ((line = in.readLine()) != null) {
                accumulateLine(line);
            }
            printResult();
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
    private static int calculateNumberOfFields() {
        int count = 0;
        Scanner s = new Scanner(header);
        s.useDelimiter(",");
        while (s.hasNext()) {
            count++;
            s.next();
        }
        return count;
    }

    private static void initializeArrays() {
        fieldnames = new String[numFields];
        accumulators = new int[numFields];
        s = new Scanner(header);
        s.useDelimiter(",");
        for (int i = 0; i < numFields; i++) {
            fieldnames[i] = s.next();
            accumulators[i] = 0;
        }
    }
    private static void accumulateLine(String line) {
        s = new Scanner(line);
        s.useDelimiter(",");
        for (int i = 0; i < numFields; i++) {
            if (s.hasNextInt()) {
                accumulators[i] += s.nextInt();
            }
        }
    }
    private static void printResult() {
        for (int i = 0; i < numFields; i++) {
            System.out.println(fieldnames[i] + ": " + accumulators[i]);
        }
    }
}
