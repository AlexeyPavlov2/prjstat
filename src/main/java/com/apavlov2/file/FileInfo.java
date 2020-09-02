package com.apavlov2.file;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class FileInfo {
    private String name;
    private String extension;
    private String path;
    private long size;

}
