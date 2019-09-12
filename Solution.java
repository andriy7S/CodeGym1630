package com.codegym.task.task16.task1630;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //write your code here
    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContents());
    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContents();

        void join() throws InterruptedException;

        void start();
    }

    //write your code here
    public static class ReadFileThread extends Thread implements ReadFileInterface{
        private String fileName;
        private String fileContents = "";
        private boolean isDownloaded = false;

        public void setFileName(String fullFileName) {
            this.fileName = fullFileName;
        }

        public String getFileContents(){
            if (!isDownloaded) {
                return "";
            } else {
                return fileContents;
            }
        }

        public void run(){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(this.fileName));

                while (reader.ready()) {
                    String line = reader.readLine();
                    fileContents += line + " ";
                }
                reader.close();

            } catch (IOException e){ e.getMessage(); }
            this.isDownloaded = true;
        }
    }
}
