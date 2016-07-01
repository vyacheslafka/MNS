package steganography.lab;

import steganography.lab.IO.Read;
import steganography.lab.IO.ReadImage;
import steganography.lab.IO.Write;
import steganography.lab.IO.WriteImage;
import steganography.lab.KAZ.Embed;
import steganography.lab.KAZ.Pull;
import java.util.Date;

public class TestDriveKAZ {
    public static void main(String[] args){
        Date start = new Date();
        Embed embed = new Embed(new ReadImage("orig.bmp"), new Read("orig.txt"));
        WriteImage write = new WriteImage(embed.getNew_image(), "new_pic.bmp");
        Pull pull = new Pull(new ReadImage("new_pic.bmp"), embed.getMsg());
        Write txt = new Write(pull.getFile(), "new_text.txt");
        Date finish = new Date();
        long ms = finish.getTime() - start.getTime();
        System.out.println("Время выполнения: " + ms + " миллисекунд");
    }
}
