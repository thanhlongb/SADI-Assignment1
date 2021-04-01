package com.rmit;

import java.io.*;
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


    public static void writeFile(String content) {
        // ref: https://www.baeldung.com/java-write-to-file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(SchoolManager.DEFAULT_ENROLMENTS_FILENAME, true));
            writer.append(content);
            writer.close();
        } catch (IOException e) {
            // handle
        }
    }
}
