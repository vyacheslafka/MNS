package steganography.lab.IO;

import steganography.lab.Setting.Param;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WriteImage {

    public WriteImage(BufferedImage img, String path){
        try {
            ImageIO.write(img, "bmp", new File(Param.PATH + path));
        } catch (FileNotFoundException e) {
            System.out.println("Файл записи не найден");
            System.exit(0);
        } catch (IOException e){
            System.out.println("Ошибка записи");
            System.exit(0);
        }
    }

}
