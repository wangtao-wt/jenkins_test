//package com.pyas.test.demo.util;
//
//import org.apache.commons.compress.archivers.zip.ZipUtil;
//
//import java.io.File;
//
//public class FileUtils {
//
//
//    public static String unzipAndDecryptTwo(String sftpFileName, String fileDownloadPathEncryp, String fileDownloadPathUnzip, String fileDownloadPath, String key) {
//        String tempName = null;
//        String tempdecryptZipName = null;
//        createFolder(fileDownloadPathUnzip);
//        createFolder(fileDownloadPath);
//        try {
//            tempName = sftpFileName.replace(".zip", ".txt");
//            tempdecryptZipName = sftpFileName.replace(".zip", ".zip");
//            ZipUtil.unzip(String.valueOf(fileDownloadPathEncryp) + "\\" + sftpFileName, fileDownloadPathUnzip);
//            ThreeDesUtil.getDecryptFileInHexBy3Des2(String.valueOf(fileDownloadPathUnzip) + "\\" + tempName,
//                    String.valueOf(fileDownloadPath) + "\\" + tempdecryptZipName, fileDownloadPathUnzip, key);
//            ZipUtil.unzip(String.valueOf(fileDownloadPath) + "\\" + tempdecryptZipName, fileDownloadPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//        return String.valueOf(fileDownloadPath) + "\\" + tempName;
//    }
//
//    public static void createFolder(String folderPath) {
//        File folder = new File(folderPath);
//        if (!folder.exists())
//            folder.mkdirs();
//    }
//}
