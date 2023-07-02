package list;

public class Node<E> {
    // (참고 1) java.util의 LinkedList는 Node는 내부 static 클래스이다.
    //         왜 static인지는 다음을 참고 -> https://siyoon210.tistory.com/141 및 이펙티브 자바
    // (참고 2) java.util의 LinkedList는 double linked list이다. next 뿐만 아니라 prev도 참조 변수로 갖고 있음
    public E item;
    public Node<E> next;

    public Node(E newItem) {
        item = newItem;
        next = null;
    }

    public Node(E newItem, Node<E> nextNode) {
        item = newItem;
        next = nextNode;
    }
}
