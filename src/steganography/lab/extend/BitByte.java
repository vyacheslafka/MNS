package steganography.lab.extend;

import sun.security.util.BitArray;

public class BitByte {

    public static BitArray byteToBit(byte b){
        BitArray bitArray = new BitArray(8);
        boolean res;
        for (int i=0; i<8; i++){
            if((b>>i & 1) == 1) res = true;
            else res = false;
            bitArray.set(i,res);
        }
        return bitArray;
    }

    public static byte bitToByte(BitArray bits){
        byte num = 0;
        for (int i=0; i<bits.length(); i++)
            if (bits.get(i) == true)
                num += (byte)Math.pow(2,i);
        return num;
    }

}
