package steganography.lab.IO;

import steganography.lab.Setting.Param;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Write {

    public Write(byte[] mas, String path){
        path = Param.PATH + path;
        try {
            FileOutputStream file = new FileOutputStream(path);
            file.write(mas, 0, mas.length);
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл записи не найден");
            System.exit(0);
        } catch (NullPointerException e){
            System.out.println("Массив для записи пуст");
            System.exit(0);
        } catch (IOException e){
            System.out.println("Ошибка записи");
            System.exit(0);
        }
    }

}
