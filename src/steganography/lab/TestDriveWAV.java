package steganography.lab;

import steganography.lab.IO.Read;
import steganography.lab.IO.Write;
import steganography.lab.LSB.Decryption;
import steganography.lab.LSB.Encryption;
import java.util.Date;

public class TestDriveWAV {
    public static void main(String[] args){
        Date start = new Date();
        Read orig_wav = new Read("orig.wav");
        Read orig_text = new Read("orig.txt");
        Encryption encryption = new Encryption(orig_wav, orig_text);
        Write new_wav = new Write(encryption.getFile(), "new_wav.wav");
        Decryption decryption = new Decryption(orig_text.getMas().length, "new_wav.wav");
        Write new_text = new Write(decryption.getText(), "new_file_wav.txt");
        Date finish = new Date();
        long ms = finish.getTime() - start.getTime();

        System.out.println("WAV = " + orig_wav.getMas().length + " байт(а)");
        System.out.println("Текстовое сообщение = " + orig_text.getMas().length + " байт(а)");
        System.out.println("Максимальный объем сообщения не должен привышать - " + encryption.getMax_length() + " байт(а)");
        System.out.println("Время выполнения: " + ms + " миллисекунд");
    }
}
