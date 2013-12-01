package com.major.tools.webexplorer.action;

import com.major.commons.util.EncryptUtil;
import com.major.tools.webexplorer.domain.FileService;
import com.major.tools.webexplorer.domain.exceptions.DirectoryNotFoundException;
import com.major.tools.webexplorer.domain.exceptions.NotADirectoryException;
import com.major.tools.webexplorer.entity.FileEntity;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

/**
 * User: Minjie
 * Date: 13-11-11
 * Time: 下午12:13
 */
public class ShowDirectoryAction extends ActionSupport {
    private String parentDirectory;
    private String currentDirectory;
    private List<FileEntity> fileEntities;
    private int sortBy = 1;
    private boolean ascend = true;
    private boolean showHidden = false;

    public String getParentDirectory() {
        return parentDirectory;
    }

    public void setParentDirectory(String parentDirectory) {
        this.parentDirectory = parentDirectory;
    }

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public List<FileEntity> getFileEntities() {
        return fileEntities;
    }

    public void setFileEntities(List<FileEntity> fileEntities) {
        this.fileEntities = fileEntities;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public boolean isAscend() {
        return ascend;
    }

    public void setAscend(boolean ascend) {
        this.ascend = ascend;
    }

    public boolean isShowHidden() {
        return showHidden;
    }

    public void setShowHidden(boolean showHidden) {
        this.showHidden = showHidden;
    }

    @Override
    public String execute() {
        if (StringUtils.isNotEmpty(currentDirectory)) {
            //decode
            currentDirectory = EncryptUtil.decodeBase64(currentDirectory);
            currentDirectory = currentDirectory.endsWith("/") ? currentDirectory : currentDirectory + "/";
            parentDirectory = new File(currentDirectory).getParent();
            if (parentDirectory == null)
                parentDirectory = "";
            else {
                parentDirectory = parentDirectory.replaceAll(Pattern.quote(File.separator), "/");
                parentDirectory = parentDirectory.endsWith("/") ? parentDirectory : parentDirectory + "/";
            }
        } else {
            return ERROR;
        }
        try {
            fileEntities = FileService.getDirectoryStructure(new File(currentDirectory), showHidden, sortBy, ascend);
        } catch (NotADirectoryException e) {
            return ERROR;
        } catch (DirectoryNotFoundException e) {
            return ERROR;
        }
        return SUCCESS;
    }
}
