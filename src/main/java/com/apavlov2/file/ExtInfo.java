package com.apavlov2.file;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@ToString
public class ExtInfo {
    private String ext;
    private int count;
    private long size;
}
