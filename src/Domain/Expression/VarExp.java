package Domain.Expression;

import Domain.ADT.IDictionary;
import Exceptions.*;

public class VarExp extends Exp {

    private String var;


    public VarExp(String i){
        var = i;
    }


    @Override
    public int evaluate(IDictionary<String, Integer> table,  IDictionary<Integer, Integer> heap) throws VariableNotFound {
        try{
            return table.getValueForKey(var);

        } catch (VariableNotFound e){
            throw e;
        }
    }

    @Override
    public String toString() {
        return var;
    }
}
