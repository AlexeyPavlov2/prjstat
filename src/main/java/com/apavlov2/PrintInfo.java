package com.apavlov2;

import com.apavlov2.file.ExtInfo;
import org.apache.maven.project.MavenProject;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class PrintInfo {
    public static void printCommonProjectInfo(MavenProject project) {
        System.out.println("\n**********************************************");
        System.out.println("Project statistic <prjstat-maven-plugin>");
        System.out.println("GroupId: " + project.getGroupId());
        System.out.println("ArtifactId: " + project.getArtifactId());
        System.out.println("Version: " + project.getVersion());
        System.out.println();
    }

    public static void printFilesInfo(Map<String, ExtInfo> map) {
        long sum = 0;
        int count = 0;
        System.out.println(String.format( "%-12s %-9s %-12s", "Extension", "Count", "Size" ));
        for (Map.Entry<String, ExtInfo> entry : map.entrySet()) {
            if (entry.getValue().getCount() > 0) {
                ExtInfo value = entry.getValue();
                System.out.println(String.format("%-12s %-9s %-12s", entry.getKey().toUpperCase(), value.getCount(), getNumberTriad(value.getSize())));
                sum = value.getSize() + sum;
                count = value.getCount() + count;
            }
        }
        System.out.println(String.format("%-12s %-9s %-12s", "Total", count, getNumberTriad(sum)));
        System.out.println("**********************************************\n");
    }

    private static String getNumberTriad(long value) {
        String[] languages = { "en", "de", "ru" };
        Locale loc = new Locale(languages[2]);
        NumberFormat formatter = NumberFormat.getInstance(loc);
        return formatter.format(value);
    }

}
