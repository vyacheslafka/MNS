package steganography.lab;

import steganography.lab.IO.Read;
import steganography.lab.IO.Write;
import steganography.lab.LSB.Decryption;
import steganography.lab.LSB.Encryption;
import java.util.Date;

public class TestDriveBMP {

    public static void main(String[] args){
        Date start = new Date();
        Read orig_pic = new Read("orig.bmp");
        Read orig_text = new Read("orig.txt");
        Encryption encryption = new Encryption(orig_pic, orig_text);
        Write new_pic = new Write(encryption.getFile(), "new_pic.bmp");
        Decryption decryption = new Decryption(orig_text.getMas().length, "new_pic.bmp");
        Write new_text = new Write(decryption.getText(), "new_file_pic.txt");
        Date finish = new Date();
        long ms = finish.getTime() - start.getTime();

        System.out.println("Исходное изображение = " + orig_pic.getMas().length + " байт(а)");
        System.out.println("Текстовое сообщение = " + orig_text.getMas().length + " байт(а)");
        System.out.println("Максимальный объем сообщения не должен превышать - " + encryption.getMax_length() + " байт(а)");
        System.out.println("Время выполнения: " + ms + " миллисекунд");
    }
}
