package com.epam.izh.rd.online.repository;

import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class SimpleFileRepository implements FileRepository {
    static long countFile=0;
    static long countDir=0;
    /**
     * Метод рекурсивно подсчитывает количество файлов в директории
     *
     * @param path путь до директори
     * @return файлов, в том числе скрытых
     */

    // Пришлось объявить переменную подсчета в классе, без этого, но с рекурсией, не могу придумать метода подсчета.

    @Override
    public long countFilesInDirectory(String path) {
        String prefix = "C:\\Programs\\java-data-handling-template\\src\\main\\resources";
        File folder = new File(prefix + "/" + path);
        File[] files = folder.listFiles();
        for (File f : files) {
            if (f.isFile())
                countFile ++;
            if (f.isDirectory()) {
                String newPath = path + "\\" + f.getName();
                countFilesInDirectory(newPath);
            }
        }
        return countFile;

    }

    /**
     * Метод рекурсивно подсчитывает количество папок в директории, считая корень
     *
     * @param path путь до директории
     * @return число папок
     */
    @Override
    public long countDirsInDirectory(String path) {
        String prefix = "C:\\Programs\\java-data-handling-template\\src\\main\\resources";
        File folder = new File(prefix + "/" + path);
        File[] files = folder.listFiles();

        for (File f : files) {
            if (f.isDirectory()) {
                countDir++;
                String newPath = path + "\\" + f.getName();
                countDirsInDirectory(newPath);
            }
        }
        return countDir + 1;
    }

    /**
     * Метод копирует все файлы с расширением .txt
     *
     * @param from путь откуда
     * @param to   путь куда
     */
    @Override
    public void copyTXTFiles(String from, String to) {
        File folder = new File(from);
        File[] listOfFiles = folder.listFiles();
        Path destDir = Paths.get(to);
        if (listOfFiles != null)
            for (File file : listOfFiles) {
                try {
                    Files.copy(file.toPath(), destDir.resolve(file.getName()), StandardCopyOption.REPLACE_EXISTING);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
    /**
     * Метод создает файл на диске с расширением txt
     *
     * @param path путь до нового файла
     * @param name имя файла
     * @return был ли создан файл
     */
    @Override
    public boolean createFile(String path, String name) {
        File filePath = new File("C:\\Programs\\java-data-handling-template\\target\\classes\\"+path);
        filePath.mkdir();
        File file = new File(filePath,name);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file.exists();
    }

    /**
     * Метод считывает тело файла .txt из папки src/main/resources
     *
     * @param fileName имя файла
     * @return контент
     */
    @Override
    public String readFileFromResources(String fileName) {
        StringBuilder sb = new StringBuilder();
        String pathName = "src/main/resources";
        if (fileName.endsWith(".txt")) {
            try (Scanner sc = new Scanner(new File(pathName, fileName))) {
                while(sc.hasNext()){
                    sb.append(sc.nextLine());
                }
            } catch (IOException e) {

                e.getMessage();
            }
            return sb.toString();
        }
        else return null;
    }
}
