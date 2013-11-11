package com.major.tools.webexplorer.domain.exceptions;

/**
 * User: Minjie
 * Date: 13-11-11
 * Time: 下午12:29
 */
public class NotADirectoryException extends Exception {
    public NotADirectoryException() {
        super("Not a directory, may be a file.");
    }

    public NotADirectoryException(String message) {
        super(message);
    }
}
