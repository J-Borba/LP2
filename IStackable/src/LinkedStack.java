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
        this.list.add(v);
    }

    @Override
    public int pop() {
        while(this.list.size() != 1)
        {
            this.list.removeFirst();
        }
        return this.list.getFirst();
    }
}
