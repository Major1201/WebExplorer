package com.major.tools.webexplorer.entity;

/**
 * User: Minjie
 * Date: 13-11-10
 * Time: 下午11:35
 */
public class FileEntity {
    private String path; //file/directory path
    private String name; //file/directory name
    private boolean isDirectory;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }
}
