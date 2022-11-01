package com.example.dokterhalo;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class penyimpanDataDokter {
    protected Context context;
    protected List<String> list;

    public penyimpanDataDokter(Context context){
        this.context = context;
        this.list = new ArrayList();
    }

    public void storeInternal(String param, String filename, int opt){
        File file = null;
        if(opt == 1){
            file = new File(context.getFilesDir(), filename);
        }else{
            file = new File(context.getCacheDir(), filename);
        }
        this.writeFile(file, param);
    }

    public List<String> loadInternal(String filename, int opt){
        File file=null;
        if(opt == 1){
            file = new File(context.getFilesDir(), filename);
        }else{
            file = new File(context.getCacheDir(), filename);
        }
        return this.readFile(file);
    }

    public void writeFile(File file, String param){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] bs = param.getBytes();
            fileOutputStream.write(bs);
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void storeInternalNewData(String param, String filename, int opt){
        File file = null;
        if(opt == 1){
            file = new File(context.getFilesDir(), filename);
        }else{
            file = new File(context.getCacheDir(), filename);
        }
        this.writeFileNewData(file, param);
    }

    public void writeFileNewData(File file, String param){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            byte[] bs = param.getBytes();
            fileOutputStream.write(bs);
            fileOutputStream.flush();
            fileOutputStream.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public List<String> readFile(File fileName){
        String data = "";
        List<String> list = new ArrayList<>();
        try{
            FileInputStream fis = new FileInputStream(fileName);
            int i = 0;
            while ((i = fis.read()) != -1) {
                if(i == 10){
                    list.add(data);
                    data = "";

                }else{
                    data = data + (char)i;
                }
            }
            fis.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }

    public Bitmap loadImageFromStorage(String string)
    {
        Bitmap bitmap = null;
        String str = string;
        String[] arrOfStr = str.split("@", 2);
        String[] arr = new String[2];
        int i =0;
        for (String a : arrOfStr){
            arr[i]= a;
            i++;
        }
        try {
            File f=new File(arr[0],arr[1]);
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return bitmap;

    }

    public String saveToInternalStorage(Bitmap bitmapImage, String timeStamp){
        ContextWrapper contextWrapper = new ContextWrapper(this.context.getApplicationContext());


        File directory = contextWrapper.getDir("images", Context.MODE_PRIVATE);
        File mypath=new File(directory,timeStamp);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}
