package com.rmit.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * File manager that handles file I/O.
 */
public class FileManager {
    /**
     * Read a file.
     * @param fileName  The name of the file to be read.
     * @return  A string array of all the lines in the file.
     * @throws FileNotFoundException    when the file does not exists.
     */
    public static String[] readFile(String fileName) throws FileNotFoundException {
        ArrayList<String> lines = new ArrayList<>();
        Scanner inputFileScanner = new Scanner(new File(fileName));
        while (inputFileScanner.hasNextLine()) {
            lines.add(inputFileScanner.nextLine());
        }
        return lines.toArray(new String[lines.size()]);
    }

    /**
     * Write data to a file.
     * @param content   The content to write to the file.
     */
    public static void writeFile(String fileName, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.append(content).append(" \n");
            writer.close();
        } catch (IOException ignored) { }
    }
}
