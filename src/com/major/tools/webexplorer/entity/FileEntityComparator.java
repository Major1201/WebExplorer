package com.major.tools.webexplorer.entity;

import org.apache.commons.lang3.BooleanUtils;

import java.io.File;
import java.util.Comparator;

/**
 * User: Minjie
 * Date: 13-11-17
 * Time: 下午5:30
 */
public class FileEntityComparator implements Comparator<File> {
    private int sortBy;
    private boolean ascend;

    public FileEntityComparator(int sortBy, boolean ascend) {
        this.sortBy = sortBy;
        this.ascend = ascend;
    }

    @Override
    public int compare(File o1, File o2) {
        int ascendVal;
        switch (sortBy) {
            case 1 :
                if (o1.isDirectory() ^ o2.isDirectory())
                    ascendVal = BooleanUtils.toInteger(o1.isDirectory(), -1, 1);
                else
                    ascendVal = o1.getName().compareTo(o2.getName());
                break;
            case 2 :
                ascendVal = new Long(o1.lastModified()).compareTo(o2.lastModified());
                break;
            case 3 :
                if (o1.isDirectory() ^ o2.isDirectory())
                    ascendVal = BooleanUtils.toInteger(o1.isDirectory(), -1, 1);
                else
                    ascendVal = new Long(o1.length()).compareTo(o2.length());
                break;
            default :
                sortBy = 1;
                return compare(o1, o2);
        }
        return ascendVal * BooleanUtils.toInteger(ascend, 1, -1);
    }
}
