package com.rmit;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {
    public static String[] readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner inputFileScanner = new Scanner(new File(fileName));
        while (inputFileScanner.hasNextLine()) {
            lines.add(inputFileScanner.nextLine());
        }
        return lines.toArray(new String[lines.size()]);
    }
}
