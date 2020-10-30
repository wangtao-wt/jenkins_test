//package com.pyas.test.demo.util;
//
//import javax.crypto.Cipher;
//import javax.crypto.CipherInputStream;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKeyFactory;
//import javax.crypto.spec.DESedeKeySpec;
//import javax.crypto.spec.IvParameterSpec;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.InputStream;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.Key;
//import java.security.NoSuchAlgorithmException;
//
//public class ThreeDesUtil {
//
//
//    private static final byte[] DEFAULT_IVPARAMS = new byte[8];
//
//    private static final String DEFAULT_DESKEYALGORITHM = "DESede";
//
//    private static final String DEFAULT_CIPHERALGORITHM = "DESede/CBC/PKCS5Padding";
//
//    private static final char[] BCD_LOOKUP = new char[] {
//            '0', '1', '2', '3', '4', '5',
//            '6', '7', '8', '9',
//            'A', 'B', 'C', 'D', 'E', 'F' };
//
//    private static SecretKeyFactory keyFactory = null;
//
//    private static String key = TenfundConstant.TENPAY_KEY;
//
//    private static DESedeKeySpec keySpec = null;
//
//    private static Key desKey = null;
//
//    static {
//        try {
//            keyFactory = SecretKeyFactory.getInstance(getDESKeyAlgorithm(), getProvider());
//            try {
//                keySpec = new DESedeKeySpec(Hex.decode(key));
//
//            } catch (InvalidKeyException e) {
//                e.printStackTrace();
//            }
//            try {
//                desKey = getDesKey(key);
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static boolean getDecryptFileInHexBy3Des2(String encryptFilePath, String origFilePath, String tempPath, String key) throws Exception {
//                File encryptFile = new File(encryptFilePath);
//        File origFile = new File(origFilePath);
//        FileInputStream fileInput = new FileInputStream(encryptFile);
//        byte[] buff = new byte[1024];
//        int len = 0;
//        FileOutputStream outFile = new FileOutputStream(origFile);
//        File tempFilePath = new File(String.valueOf(tempPath) + "\\temp");
//        if (!tempFilePath.exists())
//            tempFilePath.mkdir();
//        File tempFile = new File(String.valueOf(tempPath) + "\\temp\\" + encryptFile.getName());
//        if (!tempFile.exists())
//            tempFile.createNewFile();
//        FileOutputStream outTempFile = new FileOutputStream(tempFile);
//        byte[] paramArrayOfByte = null;
//        String tempStr = null;
//        while ((len = fileInput.read(buff)) != -1) {
//            tempStr = new String(buff, 0, len);
//            paramArrayOfByte = hexStrToBytes(tempStr);
//            outTempFile.write(paramArrayOfByte, 0, paramArrayOfByte.length);
//            paramArrayOfByte = null;
//        }
//        outTempFile.flush();
//        outTempFile.close();
//        fileInput.close();
//        fileInput = null;
//        FileInputStream fileIn = new FileInputStream(tempFile);
//        InputStream desInput = getDesCipher(2, key, fileIn);
//        byte[] buff2 = new byte[1024];
//        int len2 = 0;
//        while ((len2 = desInput.read(buff2)) != -1)
//            outFile.write(buff2, 0, len2);
//        outFile.flush();
//        outFile.close();
//        fileIn.close();
//        outFile = null;
//        desInput = null;
//        fileIn = null;
//        tempFile.delete();
//        buff = null;
//        buff2 = null;
//        return true;
//    }
//
//    public static byte[] hexStrToBytes(String paramString) {
//        byte[] arrayOfByte = new byte[paramString.length() / 2];
//        int i = 0;
//        int j = arrayOfByte.length;
//        while (i < j) {
//            arrayOfByte[i] = (byte)Integer.parseInt(paramString.substring(2 * i, 2 * i + 2), 16);
//            i++;
//        }
//        return arrayOfByte;
//    }
//
//
//    private static CipherInputStream getDesCipher(int mode, String key, InputStream inputstream) throws Exception {
//        if (mode == 2)
//            return new CipherInputStream(inputstream, GetDecCipher());
//        if (mode == 1)
//            return new CipherInputStream(inputstream, GetEncCipher());
//        return null;
//    }
//
//    public static Cipher GetDecCipher() {
//        Cipher cipherDEC = null;
//        try {
//            cipherDEC = Cipher.getInstance(getDESCipherAlgorithm());
//            try {
//                cipherDEC.init(2, desKey, new IvParameterSpec(DEFAULT_IVPARAMS));
//            } catch (InvalidKeyException e) {
//                        e.printStackTrace();
//            } catch (InvalidAlgorithmParameterException e) {
//                        e.printStackTrace();
//            }
//        } catch (NoSuchPaddingException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e1) {
//                    e1.printStackTrace();
//        }
//        return cipherDEC;
//    }
//
//    private static String getDESCipherAlgorithm() {
//        return "DESede/CBC/PKCS5Padding";
//    }
//}
