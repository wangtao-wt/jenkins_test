package com.pyas.test.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveOutputStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class FileCompressUtil {
    public static File pack(File source, File target) throws IOException {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(target);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        TarArchiveOutputStream os = new TarArchiveOutputStream(out);
        InputStream inputStream = null;
        try {
            os.putArchiveEntry(new TarArchiveEntry(source,source.getName()));
            inputStream = new FileInputStream(source);
            IOUtils.copy(inputStream, os);
            os.closeArchiveEntry();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(inputStream!=null){
            inputStream.close();
        }
        if (os != null) {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return target;
    }

    public static File compress(File source,String FilePath) {
        File target = new File(FilePath);
        FileInputStream in = null;
        GZIPOutputStream out = null;

        try {
            in = new FileInputStream(source);
            out = new GZIPOutputStream(new FileOutputStream(target));
            byte[] array = new byte[1024];
            int number;
            while ((number = in.read(array, 0, array.length)) != -1) {
                out.write(array, 0, number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                    source.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            if (out != null) {
                try {
                    out.close();
//                    System.out.println("打包后文件为：" + target);
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return target;
    }
    public static void main(String[] args) throws IOException {
         /*   List<File> testFile = new ArrayList<>();
            String target = "C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\c.tar";
            testFile.add(new File("C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\a.txt"));
            testFile.add(new File("C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\b.txt"));*/


         long begin = System.currentTimeMillis();
         compress(pack(new File("C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\J50590000_D013_20200908_vol.txt"),
                         new File("C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\J50590000_D013_20200908_vol.tar")),
                    "C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\J50590000_D013_20200908_vol.tar.gz");

         long end = System.currentTimeMillis();
         System.out.println(end - begin);
    }
}
