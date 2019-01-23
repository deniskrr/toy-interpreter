package Exceptions;

public class FileReadException extends RuntimeException {

    public FileReadException() {
        super ("Error while reading!\n");
    }
}
