package com.apavlov2;

import com.apavlov2.file.ExtInfo;
import com.apavlov2.file.FileInfo;
import com.apavlov2.file.FileUtil;
import com.apavlov2.print.PrintInfo;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mojo(name = "project-statistic", defaultPhase = LifecyclePhase.INSTALL)
public class ProjectStatisticMojo extends AbstractMojo {
    private final static String INCLUDE_FILE_EXTENSION = "java, xml, properties, yml, json, md, sql, txt, html, css, js, jsp, jsf";
    private final static String EXCLUDE_DIRICTORIES = "target, .git, .idea, .mvn";

    @Parameter(defaultValue = "${project}", readonly = true)
    private MavenProject project;

    @Parameter(property = "isActive")
    private boolean isActive = true;

    @Parameter(property = "exludeDirectories")
    private String exludeDirectories;

    @Parameter
    private String includeFileExtensions;

    public void execute() throws MojoExecutionException {
        if (!isActive) {
            return;
        }
        final Map<String, ExtInfo> includeFileExtMap = new LinkedHashMap<>();
        getItemsFromString(includeFileExtensions == null ? INCLUDE_FILE_EXTENSION : includeFileExtensions)
                .forEach(el -> includeFileExtMap.put(el,
                        new ExtInfo()
                                .setExt(el)
                                .setCount(0)
                                .setSize(0)));

        List<FileInfo> fileInfoList = FileUtil.getTreeFiles(project.getBasedir().getAbsolutePath())
                .stream()
                .filter(el -> el.getExtension() == null || includeFileExtMap.containsKey(el.getExtension()))
                .filter(el -> getItemsFromString(exludeDirectories == null ? EXCLUDE_DIRICTORIES : exludeDirectories).stream().noneMatch(dir -> el.getPath().contains(dir)))
                .collect(Collectors.toList());

        for (FileInfo fileInfo : fileInfoList) {
            ExtInfo extInfo = new ExtInfo()
                    .setExt(fileInfo.getExtension())
                    .setCount(1)
                    .setSize(fileInfo.getSize());
            includeFileExtMap.computeIfPresent(extInfo.getExt(), (a, b) -> b = new ExtInfo()
                    .setCount(b.getCount() + 1)
                    .setSize(b.getSize() + extInfo.getSize())
                    .setExt(extInfo.getExt()));
        }

        PrintInfo.printCommonProjectInfo(project);
        PrintInfo.printFilesInfo(includeFileExtMap);

    }

    private List<String> getItemsFromString(String value) {
        return Stream.of(value.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }


}
