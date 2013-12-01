package com.major.tools.webexplorer.action;

import com.major.tools.webexplorer.domain.FileService;
import com.major.tools.webexplorer.entity.RootDirectory;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

/**
 * User: Minjie
 * Date: 13-11-9
 * Time: 上午12:50
 */
public class ShowDiskAction extends ActionSupport {
    private List<RootDirectory> rootDirectories; //Root list

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
