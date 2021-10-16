package com.mayur.gummynotes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class GummyNote {
    String getGummy(Context context){
        File file = new File(context.getFilesDir().getPath()+"/mayur.txt");
        StringBuilder text = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) !=  null){
                text.append(line);
                text.append("\n");
            }
            br.close();
        }catch (Exception e){
          e.printStackTrace();
        }
        return text.toString();
    }
    void setGummy(String txt, Context context){
        String data = txt;
        FileOutputStream fos = null;
        try{
            fos = context.getApplicationContext().openFileOutput("gfg.txt", Context.MODE_PRIVATE);
            fos.write(data.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(fos != null){
                try{
                    fos.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
