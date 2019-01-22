package Domain;

import java.io.BufferedReader;

public class FileData {

    private String fileName;
    private BufferedReader reader;

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
