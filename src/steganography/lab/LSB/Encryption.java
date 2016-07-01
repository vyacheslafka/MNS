package steganography.lab.LSB;

import steganography.lab.IO.Read;
import steganography.lab.extend.BitByte;
import sun.security.util.BitArray;

public class Encryption {

    private int notTouch = 54;
    private boolean encoding = false;
    private int max_length;
    private int length_text;
    private byte[] file;

    public Encryption(Read pic, Read text){
        super();
        this.max_length = (pic.getMas().length-54)/8;
        this.length_text = (text.getMas().length);
        if (max_length > length_text) encoding = true;
        if (encoding == true) this.file = encryptionFile(pic.getMas(), text.getMas());
        else System.exit(0);
    }

    private byte[] encryptionFile(byte[] pic, byte[] text){
        for (int i=0; i<text.length; i++){
            BitArray mes = BitByte.byteToBit(text[i]);
            for (int j=0; j<8; j++){
                BitArray picture = BitByte.byteToBit(pic[notTouch+j]);
                picture.set(0, mes.get(j));
                byte re = BitByte.bitToByte(picture);
                pic[notTouch+j] = re;
            }
            notTouch+=8;
        }
        return pic;
    }

    public byte[] getFile() {
        return file;
    }
    public int getMax_length() {
        return max_length;
    }
}