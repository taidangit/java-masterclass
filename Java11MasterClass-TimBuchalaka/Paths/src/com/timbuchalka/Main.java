package com.timbuchalka;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Main {

    public static void main(String[] args) {
//	    Path path= FileSystems.getDefault().getPath("WorkingDirectory.txt");
//	    printFile(path);
//        System.out.println("==================================================");
//        //Path path2= FileSystems.getDefault().getPath("files", "SubDirectory.txt");
//        Path path2=Paths.get(".", "files", "SubDirectory.txt");
//        printFile(path2);
//        System.out.println("===================================================");
 //      Path path3=Paths.get("D:\\HOC TAP\\HOC LAP TRINH\\LapTrinhJavaCoBan\\SourceCodeJava11MasterClass\\OutThere.txt");

//        Path path3= Paths.get("D:\\HOC TAP\\HOC LAP TRINH\\LapTrinhJavaCoBan","\\SourceCodeJava11MasterClass","OutThere.txt");
//        printFile(path3);

//        Path path3=Paths.get(".");
//        System.out.println(path3.toAbsolutePath());
//
/*        Path path4=FileSystems.getDefault().getPath("thisfiledoesntexist.txt");
        System.out.println(path4.toAbsolutePath());*/
//
//        Path path5=Paths.get("E:\\ING","abcdef","whatever.txt");
//        System.out.println(path5.toAbsolutePath());
//
//        Path path5=FileSystems.getDefault().getPath("files");
//        System.out.println("Exists= "+Files.exists(path5));

        try {
           /* Path sourceFile = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path copyFile = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
            Files.copy(sourceFile, copyFile, StandardCopyOption.REPLACE_EXISTING);
*/
            /*sourceFile=FileSystems.getDefault().getPath("Examples","Dir1");
            copyFile=FileSystems.getDefault().getPath("Examples","Dir4");
//            Files.copy(sourceFile,copyFile, StandardCopyOption.REPLACE_EXISTING);*/

          /*  Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1copy.txt");
            Path destination = FileSystems.getDefault().getPath("Examples", "Dir1", "file2copy.txt");
            Files.move(fileToMove, destination);
*/

         /*   Path fileToMove = FileSystems.getDefault().getPath("Examples", "file1.txt");
            Path destination = FileSystems.getDefault().getPath("Examples",  "file2.txt");
            Files.move(fileToMove, destination);*/

      /*   Path fileToDelete=FileSystems.getDefault().getPath("Examples","Dir1","file2copy.txt");
         Files.deleteIfExists(fileToDelete);*/

            //Path fileToCreate = FileSystems.getDefault().getPath("Examples", "file2.txt");
            //Files.createFile(fileToCreate);
            //Path dirToCreate=FileSystems.getDefault().getPath("Examples","Dir4");
            //Files.createDirectory(dirToCreate);

            //Path dirToCreate=FileSystems.getDefault().getPath("Examples","Dir2/Dir3/Dir4/Dir5/Dir6");
            //Files.createDirectories(dirToCreate);
            //Path dirToCreate=FileSystems.getDefault().getPath("Examples/Dir2/Dir3/Dir4/Dir5/Dir6/Dir7");
            //Files.createDirectories(dirToCreate);

            Path filePath = FileSystems.getDefault().getPath("Examples", "Dir1/file1.txt");
            long size = Files.size(filePath);
            System.out.println("Size= " + size);
            System.out.println("Last modified=" + Files.getLastModifiedTime(filePath));

            BasicFileAttributes attrs = Files.readAttributes(filePath, BasicFileAttributes.class);
            System.out.println("Size=" + attrs.size());
            System.out.println("Last modified=" + attrs.lastAccessTime());
            System.out.println("Created=" + attrs.creationTime());
            System.out.println("Is directory=" + attrs.isDirectory());
            System.out.println("Is regular file=" + attrs.isRegularFile());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    private static void printFile(Path path) {
        try(BufferedReader fileReader= Files.newBufferedReader(path)) {
            String line;
            while ((line=fileReader.readLine())!=null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
