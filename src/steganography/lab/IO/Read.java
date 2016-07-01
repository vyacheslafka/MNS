package steganography.lab.IO;

import steganography.lab.Setting.Param;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Read {

    private byte[] mas;

    public Read(String path){
        path = Param.PATH + path;
        try {
            FileInputStream file = new FileInputStream(path);
            int lengthFile = file.available();
            mas = new byte[lengthFile];
            file.read(mas);
            file.close();
        } catch (FileNotFoundException e){
            System.out.println("Файл (" + path + ") - не существует!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
            System.exit(0);
        }
    }

    public byte[] getMas() {return mas;}
}