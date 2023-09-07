package list;

import java.util.Iterator;

public class LinkedListItemIterator<T> implements Iterator<Comparable<T>> {

    // 구현 3 - 구현 1 바탕
    private Node<Comparable<T>> current;

    public LinkedListItemIterator(LinkedList<Comparable<T>> list) { // java.util의 LinkedList가 아니라 앞에서 만든 LinkedList임
        current = list.getNode(-1); // 생성했을 때 current는 더미 헤드 노드를 가리킴
    }

    @Override
    public boolean hasNext() {
        return current.next != null; // 원형이면 이렇게 못 씀
    }

    @Override
    public Comparable<T> next() {
        current = current.next;
        return (Comparable<T>) (current.item);
    }
}
