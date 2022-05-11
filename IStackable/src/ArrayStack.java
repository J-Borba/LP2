import java.util.ArrayList;

public class ArrayStack implements IStackable{
    ArrayList<Integer> array;

    public ArrayStack(){
        array = new ArrayList<>();
    }
    @Override
    public int size() {
        return this.array.size();
    }

    @Override
    public void push(int v) {
        this.array.add(v);
    }

    @Override
    public int pop() {
        while(this.array.size() != 1)
        {
            this.array.remove(0);
        }
        return this.array.get(0);
    }
}
