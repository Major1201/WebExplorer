package com.major.tools.webexplorer.domain.exceptions;

/**
 * User: Minjie
 * Date: 13-11-11
 * Time: 下午12:26
 */
public class DirectoryNotFoundException extends Exception {
    public DirectoryNotFoundException() {
        super("Directory not found.");
    }

    public DirectoryNotFoundException(String message) {
        super(message);
    }
}
