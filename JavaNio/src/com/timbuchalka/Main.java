package com.timbuchalka;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        try (FileOutputStream binFile = new FileOutputStream("data.dat")) {
            /*Path dataPath = FileSystems.getDefault().getPath("data.txt");
            Files.write(dataPath, "\nLine 5".getBytes("UTF-8"), StandardOpenOption.APPEND);
            List<String> lines = Files.readAllLines(dataPath);
            for (String line : lines) {
                System.out.println(line);
            }*/

            FileChannel binChanel = binFile.getChannel();

            byte[] outputBytes = "Hello World!".getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(outputBytes);
            int numBytes = binChanel.write(buffer);
            System.out.println("numBytes written was " + numBytes);

            ByteBuffer inBuffer = ByteBuffer.allocate(Integer.BYTES);
            inBuffer.putInt(245);
            inBuffer.flip();
            numBytes = binChanel.write(inBuffer);
            System.out.println("numBytes written was: " + numBytes);

            RandomAccessFile ra=new RandomAccessFile("data.dat","rwd");
            byte[] b=new byte[outputBytes.length];
            ra.read(b);
            System.out.println(new String(b));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
