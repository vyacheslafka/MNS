package steganography.lab.KAZ;

import steganography.lab.IO.Read;
import steganography.lab.IO.ReadImage;
import steganography.lab.Setting.Param;
import steganography.lab.extend.BitByte;
import steganography.lab.extend.ForPicture;
import steganography.lab.extend.Transform;
import sun.security.util.BitArray;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Embed {

    private ArrayList<Integer> rgb;
    private ArrayList<Transform> dcp;
    private BufferedImage new_image;
    private int  msg;

    public Embed(ReadImage image, Read text){
        int quantity = text.getMas().length * 8;
        msg = text.getMas().length;
        int max_length = image.getImg().getHeight() * image.getImg().getWidth()/(int)(Math.pow(Param.SIZE, 2));
        System.out.println("Максимальная длина сообщения не должна превышать - " + max_length/8 + " байт");
        System.out.println("Длина сообщения - " + msg + " байт");
        if (quantity > max_length) System.exit(2);
        rgb = ForPicture.createListImg(image.getImg());
        dcp = ForPicture.CreateListTransform(rgb, quantity);
        ListModified(dcp, text.getMas());
        rgb = insertListImg(rgb, dcp);
        new_image = assemblyImage(rgb, image.getImg());
    }

    private void ListModified(ArrayList<Transform> list, byte[] insert){
        int k=0;
        for (int i=0; i<insert.length; i++){
            BitArray bit = BitByte.byteToBit(insert[i]);
            for (int j=0; j<bit.length(); j++, k++){
                int z1=0, z2=0;
                double[][] container = list.get(k).getDcp();
                double coefficient1 = Math.abs(container[Param.X][Param.Y]);
                double coefficient2 = Math.abs(container[Param.Y][Param.X]);

                if (container[Param.X][Param.Y] >= 0) z1 = 1;
                else z1 = -1;
                if (container[Param.Y][Param.X] >= 0) z2 = 1;
                else z2 = -1;

                if (bit.get(j) == false && coefficient1 <= coefficient2) coefficient1 = Param.P + coefficient2 + 1;
                if (bit.get(j) == true && coefficient1 >= coefficient2) coefficient2 = Param.P + coefficient1 + 1;

                container[Param.X][Param.Y] = z1 * coefficient1;
                container[Param.Y][Param.X] = z2 * coefficient2;

                list.get(k).setDcp(container);
                list.get(k).startInverseDCP();
            }
        }
    }

    private ArrayList<Integer> insertListImg (ArrayList<Integer> img, ArrayList<Transform> list) {
        ArrayList<Integer> res = new ArrayList<Integer>(img);
        int k = 0;
        for (int count = 0; count < list.size(); count++) {
            for (int i = 0; i < Param.SIZE; i++) {
                for (int j = 0; j < Param.SIZE; j++, k++) {
                    int color = res.get(k);
                    int alpha = (color & 0xff000000) >> 24;
                    int red = (color & 0x00ff0000) >> 16;
                    int green = (color & 0x0000ff00) >> 8;
                    int blue = (int) list.get(count).getIdcp()[i][j];
                    int rgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
                    res.set(k, rgb);
                }
            }
        }
        return res;
    }

    private BufferedImage assemblyImage(ArrayList<Integer> img, BufferedImage image){
        BufferedImage new_image = image;
        int count=0;
        for (int i=0; i<image.getWidth(); i++){
            for (int j=0; j<image.getHeight(); j++, count++){
                new_image.setRGB(i, j, img.get(count));
            }
        }
        return new_image;
    }

    public BufferedImage getNew_image() {
        return new_image;
    }
    public int getMsg() {
        return msg;
    }
}
