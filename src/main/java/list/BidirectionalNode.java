package list;

public class BidirectionalNode<E> {
    // (참고 1) java.util의 LinkedList는 Node는 내부 static 클래스이다.
    //         왜 static인지는 다음을 참고 -> https://siyoon210.tistory.com/141 및 이펙티브 자바
    // (참고 2) java.util의 LinkedList는 double linked list이다. next 뿐만 아니라 prev도 참조 변수로 갖고 있음
    public BidirectionalNode<E> prev;
    public E item;
    public BidirectionalNode<E> next;

    public BidirectionalNode() {
        prev = next = null;
        item = null;
    }

    public BidirectionalNode(E newItem) {
        prev = next = null;
        item = newItem;
    }

    public BidirectionalNode(BidirectionalNode<E> prevNode, E newItem, BidirectionalNode<E> nextNode) {
        prev = prevNode;
        next = nextNode;
        item = newItem;
    }
}
