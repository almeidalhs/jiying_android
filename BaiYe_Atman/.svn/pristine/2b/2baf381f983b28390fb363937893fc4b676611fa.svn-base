package com.corelib.util;

import org.apache.http.util.EncodingUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileUtil {

    /**
     * 写入SD卡文件
     *
     * @param fileName 文件名
     * @param message  内容
     */
    public static void writeFileSdcard(String fileName, String message) {
        try {
            mkDir(fileName);
            FileOutputStream fout = new FileOutputStream(fileName);
            byte[] bytes = message.getBytes();
            fout.write(bytes);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取SD卡文件
     *
     * @param fileName 文件名
     * @return 内容
     */
    public static String readFileSdcard(String fileName) {
        String res = "";
        try {
            FileInputStream fin = new FileInputStream(fileName);
            int length = fin.available();
            byte[] buffer = new byte[length];
            fin.read(buffer);
            res = EncodingUtils.getString(buffer, "UTF-8");
            fin.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }

    /**
     * 创建文件夹
     *
     * @param fileName 文件名
     */
    public static void mkDir(String fileName) {
        try {
            File file = new File(fileName);
            if (file.exists())
                return;

            if (file.isDirectory()) {
                file.mkdirs();
            } else {
                File parent = file.getParentFile();
                if (!parent.exists())
                    parent.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * the traditional io way
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(String filename) throws IOException{

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
        BufferedInputStream in = null;
        try{
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while(-1 != (len = in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            return bos.toByteArray();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                in.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }


    /**
     * NIO way
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(String filename)throws IOException{

        File f = new File(filename);
        if(!f.exists()){
            throw new FileNotFoundException(filename);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try{
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());
            while((channel.read(byteBuffer)) > 0){
                // do nothing
//              System.out.println("reading");
            }
            return byteBuffer.array();
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                channel.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            try{
                fs.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Mapped File  way
     * MappedByteBuffer 可以在处理大文件时，提升性能
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray3(String filename)throws IOException{

        FileChannel fc = null;
        try{
            fc = new RandomAccessFile(filename,"r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int)fc.size()];
            if (byteBuffer.remaining() > 0) {
//              System.out.println("remain");
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        }catch (IOException e) {
            e.printStackTrace();
            throw e;
        }finally{
            try{
                fc.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 输入流转byte[]<br>
     */
    public static final byte[] input2byte(InputStream inStream) {
        if (inStream == null) {
            return null;
        }
        byte[] in2b = null;
        BufferedInputStream in = new BufferedInputStream(inStream);
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        int rc = 0;
        try {
            while ((rc = in.read()) != -1) {
                swapStream.write(rc);
            }
            in2b = swapStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIO(inStream, in, swapStream);
        }
        return in2b;
    }

    /**
     * 关闭流
     *
     * @param closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }
                cb.close();
            } catch (IOException e) {
                throw new RuntimeException(
                        FileUtil.class.getClass().getName(), e);
            }
        }
    }
}
