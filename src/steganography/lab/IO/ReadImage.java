package steganography.lab.IO;

import steganography.lab.Setting.Param;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadImage {

    private BufferedImage img;

    public ReadImage(String path){
        path = Param.PATH + path;
        try {
            img = ImageIO.read(new File(path));
        } catch (FileNotFoundException e){
            System.out.println("Файл (" + path + ") - не существует!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Ошибка чтения");
            System.exit(0);
        }
    }

    public BufferedImage getImg() {
        return img;
    }
}
