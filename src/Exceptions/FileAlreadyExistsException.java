package Exceptions;

public class FileAlreadyExistsException extends RuntimeException {

    public FileAlreadyExistsException(){
        super("The file is already in the file table!\n");
    }
}
