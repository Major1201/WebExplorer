package com.major.tools.webexplorer.domain;

import com.major.commons.util.FileUtil;
import com.major.commons.util.NumberUtil;
import com.major.commons.util.TimeUtil;
import com.major.tools.webexplorer.domain.exceptions.*;
import com.major.tools.webexplorer.entity.FileEntity;
import com.major.tools.webexplorer.entity.FileEntityComparator;
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
    public static List<FileEntity> getDirectoryStructure(File directory, boolean showHidden, int sortBy, boolean ascend)
            throws NotADirectoryException, DirectoryNotFoundException {
        if (directory == null)
            return null;
        if (!directory.isDirectory())
            throw new NotADirectoryException("File : \"" + directory.getPath() + "\" is not a directory.");
        if (!directory.exists())
            throw new DirectoryNotFoundException("Directory : \"" + directory.getPath() + "\" is not existed.");

        List<File> fileList = FileUtil.listFilesAndDirs(directory);
        if (fileList != null) {
            Collections.sort(fileList, new FileEntityComparator(sortBy, ascend));
            List<FileEntity> list = new ArrayList<>(fileList.size());
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
                list.add(fileEntity);
            }
            return list;
        }
        return null;
    }
}
