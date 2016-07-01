package steganography.lab.LSB;

import steganography.lab.IO.Read;
import steganography.lab.extend.BitByte;
import sun.security.util.BitArray;

public class Decryption {

    private int notTouch = 54;
    private int length;
    private byte[] text;

    public Decryption(int length, String path){
        super();
        this.length = length;
        Read pic = new Read(path);
        this.text = decryptionFile(pic.getMas());
    }

    private byte[] decryptionFile(byte[] picture){
        byte[] mas = new byte[length];
        int a=0;
        for(int i=0; i<length*8; i=i+8){
            BitArray text = new BitArray(8);
            for (int j=0; j<8; j++){
                BitArray pic = BitByte.byteToBit(picture[notTouch+j]);
                text.set(j, pic.get(0));
            }
            byte t = BitByte.bitToByte(text);
            mas[a] = t;
            notTouch+=8;
            a++;
        }

        return mas;
    }

    public byte[] getText() {
        return text;
    }
}