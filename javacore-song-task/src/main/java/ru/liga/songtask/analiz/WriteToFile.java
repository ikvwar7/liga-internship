package ru.liga.songtask.analiz;
import java.io.FileWriter;
import java.io.IOException;

public class WriteToFile {
    private FileWriter file;
    public void close(){
        try {
            file.close();
        }
        catch (IOException e){System.out.println(e.getMessage());}
    }
    public WriteToFile(String str){
        try{
            file =new FileWriter(str);}
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void  write(String str){
        try{
            file.write(str);
        }
        catch (IOException e){}
    }
    public void write (Integer str){
        try{
            file.write(Integer.toString(str));
        }
        catch (IOException e){}
    }
}
