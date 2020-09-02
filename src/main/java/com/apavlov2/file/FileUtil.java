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
        //for (int i = 0; i < Indents; i++) System.out.print("\t");
        if (Directory.isFile()) {
            //System.out.println(Directory.getName());
            list.add(new FileInfo()
                    .setName(Directory.getName())
            .setPath(Directory.getAbsolutePath())
            .setExtension(getExtensionByStringHandling(Directory.getName()).orElse(""))
            .setSize(Directory.length()));
        }
        else {
            //System.out.println(Directory.getName());
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
