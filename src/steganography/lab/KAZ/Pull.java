package steganography.lab.KAZ;

import steganography.lab.IO.ReadImage;
import steganography.lab.Setting.Param;
import steganography.lab.extend.BitByte;
import steganography.lab.extend.ForPicture;
import steganography.lab.extend.Transform;
import sun.security.util.BitArray;

import java.util.ArrayList;

public class Pull {

    private ArrayList<Integer> rgb;
    private ArrayList<Transform> dcp;
    private byte[] file;

    public Pull(ReadImage image, int length){
        rgb = ForPicture.createListImg(image.getImg());
        dcp = ForPicture.CreateListTransform(rgb, length*8);
        file = pullInformation(dcp, length);
    }

    private byte[] pullInformation(ArrayList<Transform> list, int quantity){
        byte[] res = new byte[quantity];

        int j=0;
        for (int count=0; count<quantity*8; j++){
            BitArray bit = new BitArray(8);
            for (int i=0; i<bit.length(); i++, count ++){
                double[][] container = list.get(count).getDcp();
                if (Math.abs(container[Param.X][Param.Y]) > Math.abs(container[Param.Y][Param.X])) bit.set(i, false);
                else if (Math.abs(container[Param.X][Param.Y]) < Math.abs(container[Param.Y][Param.X])) bit.set(i, true);
            }
            res[j] = BitByte.bitToByte(bit);
        }
        return res;
    }

    public byte[] getFile() {
        return file;
    }
}
