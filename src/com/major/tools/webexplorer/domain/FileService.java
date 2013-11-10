package com.major.tools.webexplorer.domain;

import com.major.tools.webexplorer.entity.RootDirectory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Minjie
 * Date: 13-11-10
 * Time: 下午11:39
 */
public class FileService {
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
}
