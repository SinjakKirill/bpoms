package com.pek.cameraapi;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Sinyak Kirill on 18.03.2017.
 */

/*public class Hash77 {
    private static byte[] X_bytes = new byte[512];
    public static byte[] C = DatatypeConverter.parseHexBinary(" B194BAC80A08F53B");
    public static byte [] S_value  = DatatypeConverter.parseHexBinary("B194BAC80A08F53B366D008E584A5DE48504FA9D1BB6C7AC252E72C202FDCE0D5BE3D61217B96181FE6786AD716B890B" +
            "5CB0C0FF33C356B835C405AED8E07F99E12BDC1AE28257EC703FCCF095EE8DF1C1AB76389FE678CAF7C6F860D5BB9C4FF33C657B637C306ADD4EA7799EB23D3" +
            "3E98B56E27D3BCCF591E181F4C5AB793E9DEE72C8F0C0FA62DDB49F46F73964706075316ED247A3739CBA38303A98BF692BD9B1CE5D141015445FBC95E4D0EF2682080AA227D642F" +
            "2687F93490405511");


    private static long Two_In32 = 4294967296L;
    private static long Two_In24 = 16777216L;
    private static long Two_In16 = 65536L;
    private static long Two_In8 = 256L;
    private static int T;
    private static int flag;
    private static long length;
    public static int N=0;
    public static String getHash(){
        RandomAccessFile file = null;
        long point = 0;
        long current;
        try {
            System.out.println(S_value);
            file = new RandomAccessFile("C:/des/myVideo.mp4", "rw");
            length = file.length();
            point = ((int)(length/256)*256);
            while (file.getFilePointer()!= length) {
                if(file.getFilePointer() == point){
                    flag = file.read(X_bytes = new byte[(int)(length-file.getFilePointer())]);
                    ++ N;
                    madeMultiple();
                }
                else {
                    flag = file.read(X_bytes);
                    System.out.println(X_bytes);
                    ++N;
                }
            }
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return String.valueOf(N);
    }




    public static void madeMultiple(){
        T  = (X_bytes.length%(1536-256*4));

        X_bytes = ArrayUtils.addAll(X_bytes,DatatypeConverter.parseHexBinary("01000000"));

        for(int i = 0; i < ((1536-256*4)-T-1);i++){

            X_bytes = Arrays.copyOf(X_bytes,256);
            System.out.println(X_bytes);
        }

    }
    //0-64 64-128 128-192 192-256 256-320 320-384  384-448 448-512 // 512-576 576-640 640-704 704-768 768-832 832-896 896-960 960-1024
    //
    public Map<String, Long> bash_S(long w0, long w1, long w2, int m1, int m2, int n1, int n2, long T0, long T1, long T2){



        Map<String, Long> result = new HashMap<String, Long>();

        result.put("w0", w0);
        result.put("w1", w1);
        result.put("w2", w2);
        return result;
    }
    public byte[] Bush_f(byte [] S)
    {

        for(int i=0;i<24;i++)
        { int m1=8,n1=53,m2=14,n2=1;
            int kol=64*i;
            byte [] S_j=Arrays.copyOfRange(S,0+kol,64+kol);
            byte [] S_j8=Arrays.copyOfRange(S,448+kol,512+kol);
            byte [] S_j16=Arrays.copyOfRange(S,960+kol,1024+kol);
            for(int j=0;j<7;j++)
            {
                bash_S(S_j,S_j8,S_j16);


            }


        }
    }

    public byte[] Lo(byte[] S){
        T  = (X_bytes.length%512);
        S=ArrayUtils.addAll(X_bytes,Arrays.copyOfRange(S,512,1536));
        return null;
    }
}*/
