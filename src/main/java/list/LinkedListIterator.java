package list;

import java.util.Iterator;

public class LinkedListIterator implements Iterator<Node> {

    private Node current;

    public LinkedListIterator(LinkedList list) { // java.util의 LinkedList가 아니라 앞에서 만든 LinkedList임
        current = list.getNode(-1); // 생성했을 때 current는 더미 헤드 노드를 가리킴
    }

    @Override //implements Iterator - remove()와 forEachRemaining()은 default로 재정의하지 않아도 됨
    public boolean hasNext() {
        return current.next != null; // 원형이면 이렇게 못 씀
    }

    @Override //implements Iterator - remove()와 forEachRemaining()은 default로 재정의하지 않아도 됨
    public Node next() {
        return current = current.next;
    }
}
