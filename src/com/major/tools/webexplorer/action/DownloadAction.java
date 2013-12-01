package com.major.tools.webexplorer.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;

/**
 * User: Minjie
 * Date: 13-11-17
 * Time: 下午8:41
 */
public class DownloadAction extends ActionSupport {
    private String file;
    private String fileName;

    public InputStream getInputStream() throws Exception {
        if (StringUtils.isNotEmpty(file)) {
            file = URLDecoder.decode(file, "UTF-8");
            file = new String(Base64.decodeBase64(file));
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
