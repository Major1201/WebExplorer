package com.major.tools.webexplorer.action;

import com.major.tools.webexplorer.domain.FileService;
import com.major.tools.webexplorer.domain.exceptions.DirectoryNotFoundException;
import com.major.tools.webexplorer.domain.exceptions.NotADirectoryException;
import com.major.tools.webexplorer.entity.FileEntity;
import com.opensymphony.xwork2.Action;

import java.io.File;
import java.util.List;

/**
 * User: Minjie
 * Date: 13-11-11
 * Time: 下午12:13
 */
public class ShowDirectoryAction implements Action {
    private String parentDirectory;
    private List<FileEntity> fileEntities;

    public String getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(String parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    public List<FileEntity> getFileEntities() {
        return fileEntities;
    }

    public void setFileEntities(List<FileEntity> fileEntities) {
        this.fileEntities = fileEntities;
    }

    @Override
    public String execute() {
        try {
            fileEntities = FileService.getDirectoryStructure(new File(parentDirectory));
        } catch (NotADirectoryException e) {
            return ERROR;
        } catch (DirectoryNotFoundException e) {
            return ERROR;
        }
        return SUCCESS;
    }
}
