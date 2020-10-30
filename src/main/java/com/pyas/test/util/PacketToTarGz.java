package com.pyas.test.util;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tools.tar.TarEntry;
import org.apache.tools.tar.TarOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.GZIPOutputStream;

public class PacketToTarGz {

    /**
     * @功能描述 压缩tar.gz 文件
     * @param resourceList 源文件集合
     * @param outPath 目标文件
     * @return 返回压缩结果
     * @throws Exception
     */
    public static void packet(List<File> resourceList, String outPath) throws Exception {
        //1. 参数验证, 初始化输出路径
        if(resourceList == null || resourceList.size() < 1 || outPath.isEmpty()){
            throw new Exception();
        }
        long startTime = System.currentTimeMillis();
        // 2. 迭代源文件集合, 将文件打包为Tar
        try (FileOutputStream fileOutputStream = new FileOutputStream(outPath+".tmp");
             BufferedOutputStream bufferedOutput = new BufferedOutputStream(fileOutputStream);
             TarOutputStream tarOutputStream = new TarOutputStream(bufferedOutput);) {
            for (File resourceFile : resourceList) {
                if(!resourceFile.isFile()){
                    continue;
                }
                try(FileInputStream fileInputStream = new FileInputStream(resourceFile);
                    BufferedInputStream bufferedInput = new BufferedInputStream(fileInputStream);){
                    TarEntry entry = new TarEntry(new File(resourceFile.getName()));
                    entry.setSize(resourceFile.length());
                    tarOutputStream.putNextEntry(entry);
                    IOUtils.copy(bufferedInput, tarOutputStream);
                } catch (Exception e) {
                    throw new Exception();
                }finally {
                    tarOutputStream.closeEntry();
                }
            }
        } catch (Exception e) {
            Files.delete(Paths.get(outPath+".tmp"));

        }
        //3. 读取打包好的Tar临时文件文件, 使用GZIP方式压缩
        try (FileInputStream fileInputStream = new FileInputStream(outPath+".tmp");
             BufferedInputStream bufferedInput = new BufferedInputStream(fileInputStream);
             FileOutputStream fileOutputStream = new FileOutputStream(outPath);
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(fileOutputStream);
             BufferedOutputStream bufferedOutput = new BufferedOutputStream(gzipOutputStream);
        ) {
            byte[] cache = new byte[1024];
            for (int index = bufferedInput.read(cache); index != -1; index = bufferedInput.read(cache)) {
                bufferedOutput.write(cache,0,index);
            }
            long endTime = System.currentTimeMillis();
        } catch (Exception e) {
            throw new Exception();
        }finally {
            Files.delete(Paths.get(outPath+".tmp"));
        }
    }

    public static void main(String[] args) {

       /* String a = "a.txt";
       String s = a.replace(".txt", ".tar");
        System.out.println(s);*/
        new File("C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\a.txt").delete();




//        execute("C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\a.txt","C:\\Users\\pyas-gys\\IdeaProjects\\demo\\src\\main\\resources\\a.tar.gz");
    }


    private static int BUFFER = 1024 * 4; // 缓冲大小
    private static byte[] B_ARRAY = new byte[BUFFER];

    /*
     * 方法功能：打包单个文件或文件夹 参数：inputFileName 要打包的文件夹或文件的路径 targetFileName 打包后的文件路径
     */
    public static void execute(String inputFileName, String targetFileName) {
        File inputFile = new File(inputFileName);
        String base = inputFileName
                .substring(inputFileName.lastIndexOf("/") + 1);
        TarOutputStream out = getTarOutputStream(targetFileName);
        tarPack(out, inputFile, base);
        try {
            if (null != out) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        compress(new File(targetFileName));
    }

   /* *//*
     * 方法功能：打包多个文件或文件夹 参数：inputFileNameList 要打包的文件夹或文件的路径的列表 targetFileName
     * 打包后的文件路径
     *//*
    public void execute(List<String> inputFileNameList, String targetFileName) {
        TarOutputStream out = getTarOutputStream(targetFileName);

        for (String inputFileName : inputFileNameList) {
            File inputFile = new File(inputFileName);
            String base = inputFileName.substring(inputFileName
                    .lastIndexOf("/") + 1);
            tarPack(out, inputFile, base);
        }

        try {
            if (null != out) {
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        compress(new File(targetFileName));
    }*/

    /*
     * 方法功能：打包成tar文件 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
     */

    private static void tarPack(TarOutputStream out, File inputFile, String base) {
        if (inputFile.isDirectory()) // 打包文件夹
        {
            packFolder(out, inputFile, base);
        } else // 打包文件
        {
            packFile(out, inputFile, base);
        }
    }

    /*
     * 方法功能：遍历文件夹下的内容，如果有子文件夹，就调用tarPack方法 参数：out 打包后生成文件的流 inputFile 要压缩的文件夹或文件
     * base 打包文件中的路径
     */
    private static void packFolder(TarOutputStream out, File inputFile, String base) {
        File[] fileList = inputFile.listFiles();
        try {
            // 在打包文件中添加路径
            out.putNextEntry(new TarEntry(base + "/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        base = base.length() == 0 ? "" : base + "/";
        for (File file : fileList) {
            tarPack(out, file, base + file.getName());
        }
    }

    /*
     * 方法功能：打包文件 参数：out 压缩后生成文件的流 inputFile 要压缩的文件夹或文件 base 打包文件中的路径
     */
    private static void packFile(TarOutputStream out, File inputFile, String base) {
        TarEntry tarEntry = new TarEntry(base);

        // 设置打包文件的大小，如果不设置，打包有内容的文件时，会报错
        tarEntry.setSize(inputFile.length());
        try {
            out.putNextEntry(tarEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int b = 0;

        try {
            while ((b = in.read(B_ARRAY, 0, BUFFER)) != -1) {
                out.write(B_ARRAY, 0, b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err
                    .println("NullPointerException info ======= [FileInputStream is null]");
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.closeEntry();
                }
            } catch (IOException e) {
            }
        }
    }

    /*
     * 方法功能：把打包的tar文件压缩成gz格式 参数：srcFile 要压缩的tar文件路径
     */
    private static void compress(File srcFile) {
        File target = new File(srcFile.getAbsolutePath() + ".gz");
        FileInputStream in = null;
        GZIPOutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new GZIPOutputStream(new FileOutputStream(target));
            int number = 0;
            while ((number = in.read(B_ARRAY, 0, BUFFER)) != -1) {
                out.write(B_ARRAY, 0, number);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }

                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 方法功能：获得打包后文件的流 参数：targetFileName 打包后文件的路径
     */
    private static TarOutputStream getTarOutputStream(String targetFileName) {
        // 如果打包文件没有.tar后缀名，就自动加上
        targetFileName = targetFileName.endsWith(".tar") ? targetFileName
                : targetFileName + ".tar";
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(targetFileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                fileOutputStream);
        TarOutputStream out = new TarOutputStream(bufferedOutputStream);

        // 如果不加下面这段，当压缩包中的路径字节数超过100 byte时，就会报错
        out.setLongFileMode(TarOutputStream.LONGFILE_GNU);
        return out;
    }
}
