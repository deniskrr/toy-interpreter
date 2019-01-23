package Domain.Statements.FileStatements;

import Domain.ADT.IDictionary;
import Domain.FileData;
import Domain.PrgState;
import Domain.Statements.IStmt;
import Exceptions.FileAlreadyExistsException;
import Exceptions.FileReadException;
import Utils.IdGenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class OpenFile implements IStmt {

    private String varFileId;
    private String filename;

    public OpenFile(String fileId, String filename) {
        this.varFileId = fileId;
        this.filename = filename;
    }

    public boolean checkExistence(String filename, IDictionary<Integer, FileData> fileTable){
        for(Map.Entry<Integer, FileData> it: fileTable.getAll())
            if (filename.equals(it.getValue().getFileName()))
                return true;
        return false;
    }

    @Override
    public PrgState execute(PrgState state) throws FileAlreadyExistsException, FileReadException {

        BufferedReader reader;
        try{
            if(checkExistence(filename, state.getFileTable())) {
                throw new FileAlreadyExistsException();
            }
            reader = new BufferedReader(new FileReader(filename));
            FileData tuple = new FileData(filename, reader);

            int newId = IdGenerator.generateUniqueId();

            state.getFileTable().add(newId,tuple);
            state.getSymTable().add(varFileId, newId);

        }
        catch(IOException e){
            throw new FileReadException();
        }
        catch (NullPointerException e){
            System.err.println("Null pointer");
        }
        catch (FileAlreadyExistsException e){
            throw new FileAlreadyExistsException();
        }
        return null;
    }

    @Override
    public String toString() {
        return "openFile (" + varFileId + ",\"" + filename + "\")";
    }

}
