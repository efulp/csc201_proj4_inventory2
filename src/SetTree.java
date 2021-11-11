
import java.util.ArrayList;

public class SetTree<E> {
    ArrayList<AVLTree<E>> table = null;

    public SetTree(int tableSize) {  }

    public boolean contains(E value) {
        return false;
    }

    public boolean add(E value) {
        return false;
    }

    public boolean remove(E value) {
        return false;
    }

    public int size() {
        return 0;  
    }

    public double loadFactor() {
        return 0.0;  
    }

    public double loadStd() {
        return 0.0;  
    }

    private int hash(E value){
        return 0;  
    }

    public String toString() {
        String result = "";
        return result;
    }
}


