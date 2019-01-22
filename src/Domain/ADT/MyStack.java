package Domain.ADT;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {

    private Stack<T> stack;


    public MyStack() {
        stack = new Stack<T>();
    }

    public Stack<T> getStack(){
        return stack;
    }

    @Override
    public void push(T element){
        stack.push(element);
    }

    @Override
    public T pop(){
        return stack.pop();
    }

    @Override
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    @Override
    public String toString(){

        String str = "";

        for(T element : stack)
            str += "\t" + element.toString() + "\n";

        return str;

    }

}
