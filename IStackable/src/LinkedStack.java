import java.util.LinkedList;

public class LinkedStack implements IStackable{

    LinkedList<Integer> list;
    public LinkedStack(){
        list = new LinkedList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void push(int v) {
        this.list.addLast(v);
    }

    @Override
    public int pop() {
        int backup = this.list.getLast();
        this.list.removeAll(list);

        this.list.addLast(backup);

        return this.list.get(0);
    }
}
