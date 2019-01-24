package Domain.ADT;

import java.util.Stack;

public class MyStack<T> implements IStack<T> {

    private Stack<T> stack;


    public MyStack() {
        stack = new Stack<T>();
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
    public T peek() {
        if (stack.size() > 0) {
            return stack.peek();
        }
        return null;
    }

    @Override
    public Stack<T> getStack() {
        return stack;
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
