package Domain;

import Domain.ADT.SemaphoreTable;

import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper for an entry in the {@link SemaphoreTable}.
 */
public class SemaphoreEntry {
    private int counter;
    private List<Integer> list;

    public SemaphoreEntry(int counter) {
        this.counter = counter;
        this.list = new ArrayList<>();
    }

    public int getCounter() {
        return counter;
    }

    public List<Integer> getList() {
        return list;
    }

    @Override
    public String toString() {
        String str = "";

        str += "Counter: " + counter + " Programs:";
        for (Integer id : list) {
            str += id + ", ";
        }

        return str;
    }
}
