package hashTable;

public interface IntegerIndexInterface<T> {
    public T search(Integer x);
    public void insert(Integer x);
    public void delete(Integer x);
    public boolean isEmpty();
    public void clear();
}
