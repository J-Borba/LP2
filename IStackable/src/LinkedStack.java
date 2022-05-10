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

    }

    @Override
    public int pop() {
        return 0;
    }
}
