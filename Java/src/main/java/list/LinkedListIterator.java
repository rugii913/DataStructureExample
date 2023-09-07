package list;

import java.util.Iterator;

public class LinkedListIterator implements Iterator<Node> {

    // 구현 1
    /*
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
    */

    // 구현 2
    private Node nextNode;

    public LinkedListIterator(LinkedList list) {
        nextNode = list.getNode(0); // 0th Node
    }

    @Override
    public boolean hasNext() {
        return nextNode != null;
    }

    @Override
    public Node next() { // 필드 nextNode에는 nextNode.next를 저장하고, 원래 nextNode였던 것을 반환
        Node tmp = nextNode;
        nextNode = nextNode.next;
        return tmp;
    }
}
