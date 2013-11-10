package com.major.tools.webexplorer.action;

import com.major.tools.webexplorer.domain.FileService;
import com.major.tools.webexplorer.entity.RootDirectory;
import com.opensymphony.xwork2.Action;

import java.util.List;

/**
 * User: Minjie
 * Date: 13-11-9
 * Time: 上午12:50
 */
public class ShowDiskAction implements Action {
    private List<RootDirectory> rootDirectories; //盘符列表

    public List<RootDirectory> getRootDirectories() {
        return rootDirectories;
    }

    public void setRootDirectories(List<RootDirectory> rootDirectories) {
        this.rootDirectories = rootDirectories;
    }

    @Override
    public String execute() throws Exception {
        rootDirectories = FileService.getRootDirList();
        return SUCCESS;
    }
}
