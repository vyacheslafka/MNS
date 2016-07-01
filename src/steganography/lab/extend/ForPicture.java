package steganography.lab.extend;

import steganography.lab.Setting.Param;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class ForPicture {

    public static ArrayList<Integer> createListImg (BufferedImage img){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i=0; i<img.getWidth(); i++){
            for (int j=0; j<img.getHeight(); j++){
                list.add(img.getRGB(i,j));
            }
        }
        return list;
    }

    public static ArrayList<Transform> CreateListTransform(ArrayList<Integer> list, int quantity){
        ArrayList<Transform> transforms = new ArrayList<Transform>();
        int k = 0;
        for (int count=0; count<quantity; count++){
            int[][] mas = new int[Param.SIZE][Param.SIZE];
            for (int i=0; i<Param.SIZE; i++){
                for (int j=0; j<Param.SIZE; j++, k++){
                    mas[i][j] = list.get(k) & 0x000000ff;
                }
            }
            transforms.add(new Transform(mas));
        }
        return transforms;
    }
}
