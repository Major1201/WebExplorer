package com.major.tools.webexplorer.action;

import com.major.commons.util.EncryptUtil;
import com.opensymphony.xwork2.Action;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * User: Minjie
 * Date: 13-11-17
 * Time: 下午8:41
 */
public class DownloadAction implements Action {
    private String file;
    private String fileName;

    public InputStream getInputStream() throws Exception {
        if (StringUtils.isNotEmpty(file)) {
            file = EncryptUtil.decodeBase64(file);
            fileName = new File(file).getName();
            return new FileInputStream(file);
        }
        return null;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }
}
