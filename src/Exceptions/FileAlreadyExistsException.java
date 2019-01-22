package Exceptions;

public class FileAlreadyExistsException extends Exception {

    public FileAlreadyExistsException(){
        super("The file is already in the file table!\n");
    }
}
