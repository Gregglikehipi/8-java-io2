package com.example.task01;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task01Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(extractSoundName(new File("task01/src/main/resources/3727.mp3")));

    }

    public static String extractSoundName(File file) throws IOException, InterruptedException {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd","/c","ffprobe -v error -of flat -show_format " + "\"" + file.getAbsolutePath() + "\"")
                .directory(new File("C:\\ffmpeg\\bin"));
        String name = null;
        try {

            Process process = processBuilder.start();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> wholeLine = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                wholeLine.add(line);
            }
            name = wholeLine.get(12);
            int i1 = name.indexOf('\"');
            for (int i = i1;;) {
                i++;
                if (name.charAt(i) == '\"') {
                    name = name.substring(i1 + 1, i);
                    break;
                }
            }
            return name;


        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }
}
