package com.major.tools.webexplorer.action;

import com.major.commons.util.EncryptUtil;
import com.opensymphony.xwork2.Action;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * User: Minjie
 * Date: 13-11-17
 * Time: 下午10:17
 */
public class ShowPicturesAction implements Action {
    private String currentDirectory;
    private List<String> links;

    public String getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(String currentDirectory) {
        this.currentDirectory = currentDirectory;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }

    @Override
    public String execute() throws Exception {
        if (StringUtils.isNotEmpty(currentDirectory)) {
            currentDirectory = EncryptUtil.decodeBase64(currentDirectory);
            if (currentDirectory == null)
                return ERROR;
            File dir = new File(currentDirectory);
            if (dir.isDirectory() && dir.exists()) {
                String extensions[] = {".jpg", ".png", ".bmp"};
                Collection<File> files = FileUtils.listFiles(dir, new SuffixFileFilter(extensions, IOCase.INSENSITIVE), null);
                List<String> list = new ArrayList<>();
                for (File file : files) {
                    list.add("Download?file=" + URLEncoder.encode(EncryptUtil.encodeBase64(file.getPath()), "UTF-8"));
                }
                links = list;
                return SUCCESS;
            } else {
                return ERROR;
            }
        } else {
            return ERROR;
        }
    }
}
