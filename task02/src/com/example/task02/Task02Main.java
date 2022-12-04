package com.example.task02;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Task02Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //здесь вы можете вручную протестировать ваше решение, вызывая реализуемый метод и смотря результат
        // например вот так:


        System.out.println(listFiles(Paths.get("task02/src/main/resources/")));


    }

    public static List<Path> listFiles(Path rootDir) throws IOException, InterruptedException {
        File folder = new File("/Users/you/folder/");
        File[] listOfFiles = folder.listFiles();
        List<Path> list = new ArrayList();
        try (DirectoryStream<Path> dirStream =
                Files.newDirectoryStream(rootDir)) {
            for (Path child : dirStream)
                if (Files.isDirectory(child))
                    list.addAll(listFiles(child));
                else
                    list.add(child);
        }
        /*
        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }

        return Files.walk(Paths.get("/path/to/folder"))
                .filter(Files::isRegularFile)
                .collect(Collectors.toList());
        */
        return list;
    }
}
