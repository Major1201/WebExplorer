package com.major.tools.webexplorer.domain;

import com.major.commons.util.FileUtil;
import com.major.commons.util.NumberUtil;
import com.major.commons.util.TimeUtil;
import com.major.tools.webexplorer.domain.exceptions.*;
import com.major.tools.webexplorer.entity.FileEntity;
import com.major.tools.webexplorer.entity.RootDirectory;

import java.io.File;
import java.util.*;
import java.util.regex.Pattern;

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
                rootDirectory.setName(root.getPath().replaceAll(Pattern.quote(File.separator), "/"));
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
    public static List<FileEntity> getDirectoryStructure(File directory, boolean showHidden, FileEntity.SortBy sortBy, boolean ascend)
            throws NotADirectoryException, DirectoryNotFoundException {
        if (directory == null || sortBy == null)
            return null;
        if (!directory.isDirectory())
            throw new NotADirectoryException("File : \"" + directory.getPath() + "\" is not a directory.");
        if (!directory.exists())
            throw new DirectoryNotFoundException("Directory : \"" + directory.getPath() + "\" is not existed.");

        TreeMap<Object, FileEntity> fileMap = new TreeMap<>();
        TreeMap<Object, FileEntity> dirMap = new TreeMap<>();
        List<File> fileList = FileUtil.listFilesAndDirs(directory);
        if (fileList != null) {
            for (File file : fileList) {
                if (!showHidden && file.isHidden())
                    continue;
                FileEntity fileEntity = new FileEntity();
                fileEntity.setDirectory(file.isDirectory());
                fileEntity.setName(file.getName().replaceAll(Pattern.quote(File.separator), "/"));
                fileEntity.setPath(file.getPath().replaceAll(Pattern.quote(File.separator), "/"));
                fileEntity.setHidden(file.isHidden());
                fileEntity.setLastModified(TimeUtil.formatDate(new Date(file.lastModified()), "yyyy-MM-dd HH:mm:ss"));
                fileEntity.setSize(NumberUtil.formatFileSize(file.length()));

                SortedMap<Object, FileEntity> sortedMap;
                if (file.isDirectory())
                    sortedMap = dirMap;
                else
                    sortedMap = fileMap;
                switch (sortBy) {
                    case NAME : sortedMap.put(file.getName(), fileEntity); break;
                    case LAST_MODIFIED : sortedMap.put(file.lastModified(), fileEntity); break;
                    case SIZE : sortedMap.put(file.length(), fileEntity); break;
                }
            }
        }
        List<FileEntity> list = new ArrayList<>();
        if (ascend) {
            list.addAll(dirMap.values());
            list.addAll(fileMap.values());
        } else {
            list.addAll(fileMap.descendingMap().values());
            list.addAll(dirMap.descendingMap().values());
        }
        return list;
    }
}
