package com.alarmsystem.grand_000.ev3alarmsystem;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileHandler {
    private File fileName;

    public FileHandler(String fileName){
        this.fileName = new File(fileName);
    }

    public void writeToFile(String data,Context context) {
        FileOutputStream outputStream;
        try {
            outputStream = context.openFileOutput(this.fileName.getName(), context.MODE_PRIVATE);
            outputStream.write(data.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String readFromFile(Context context) {
        FileInputStream fin;

        String temp="";
        try {
            fin = context.openFileInput(this.fileName.getName());
            int c;
            try {
                while( (c = fin.read()) != -1){
                    temp = temp + Character.toString((char)c);
                }
                fin.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        return temp;
    }
}
