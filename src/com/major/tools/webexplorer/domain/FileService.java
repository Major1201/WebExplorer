package com.major.tools.webexplorer.domain;

import com.major.tools.webexplorer.domain.exceptions.*;
import com.major.tools.webexplorer.entity.FileEntity;
import com.major.tools.webexplorer.entity.RootDirectory;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: Minjie
 * Date: 13-11-10
 * Time: 下午11:39
 */
public class FileService {
    /**
     * Get root list.
     * @return a list
     */
    public static List<RootDirectory> getRootDirList() {
        File roots[] = File.listRoots();

        if (roots != null) {
            List<RootDirectory> rootDirectories = new ArrayList<>(roots.length);
            for (File root : roots) {
                RootDirectory rootDirectory = new RootDirectory();
                rootDirectory.setName(root.getPath());
                rootDirectories.add(rootDirectory);
            }
            return rootDirectories;
        }
        return new ArrayList<>();
    }

    /**
     * Get file and directory list for the directory.
     * @return a list
     */
    public static List<FileEntity> getDirectoryStructure(File directory) throws NotADirectoryException, DirectoryNotFoundException {
        if (!directory.isDirectory())
            throw new NotADirectoryException("File : \"" + directory.getPath() + "\" is not a directory.");
        if (!directory.exists())
            throw new DirectoryNotFoundException("Directory : \"" + directory.getPath() + "\" is not existed.");

        List<FileEntity> list = new ArrayList<>();
        Iterator<File> files = FileUtils.iterateFilesAndDirs(directory, FileFilterUtils.trueFileFilter(), FileFilterUtils.directoryFileFilter());
        if (!files.hasNext())
            return new ArrayList<>();
        files.next(); //step over the base directory
        while (files.hasNext()) {
            File file = files.next();
            FileEntity fileEntity = new FileEntity();
            fileEntity.setPath(file.getPath());
            fileEntity.setName(file.getName());
            fileEntity.setDirectory(file.isDirectory());
            list.add(fileEntity);
        }
        return list;
    }
}
