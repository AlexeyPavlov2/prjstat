package com.apavlov2.file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileUtil {

    private FileUtil() {
    }

    public static List<FileInfo> getTreeFiles(String Path) {
        List<FileInfo> list = new ArrayList<>();
        File Directory = new File(Path);

        if (Directory.exists()) {
            getContent(Directory, 0, list);
        } else System.out.println("Directory is not found...");
        return list;
    }

    private static void getContent(File Directory, int Indents, List<FileInfo> list) {
        if (Directory.isFile()) {
            list.add(new FileInfo()
                    .setName(Directory.getName())
            .setPath(Directory.getAbsolutePath())
            .setExtension(getExtensionByStringHandling(Directory.getName()).orElse(""))
            .setSize(Directory.length()));
        }
        else {
            File[] SubDirectory = Directory.listFiles();
            for (File SubWay : SubDirectory)
                getContent(SubWay, Indents + 1, list);
        }
    }

    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

}
