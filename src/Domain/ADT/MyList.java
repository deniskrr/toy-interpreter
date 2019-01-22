package Domain.ADT;

import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class MyList<T> implements IList<T> {

    private Queue<T> list;

    public MyList(){
        list = new LinkedList<T>();
    }

    @Override
    public void add(T element){
        list.add(element);
    }

    @Override
    public T pop(){
        return list.poll();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    @Override
    public String toString(){
        String str = "";

        for(T element : list)
            str += "\t" +  element.toString();

        return str;
    }

    @Override
    public Queue<T> getQueue(){
        return list;
    }
}
