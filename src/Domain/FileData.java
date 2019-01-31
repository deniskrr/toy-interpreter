package Domain;

import java.io.BufferedReader;

/**
 * A wrapper class for a File
 */
public class FileData {

    private String fileName;
    private BufferedReader reader;

    /**
     * Constructor
     * @param fileName - the name of the file
     * @param reader - the reader
     */
    public FileData(String fileName, BufferedReader reader) {
        this.fileName = fileName;
        this.reader = reader;
    }

    public String getFileName() {
        return fileName;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public String toString(){
        return "Filename: "+ fileName;
    }
}
