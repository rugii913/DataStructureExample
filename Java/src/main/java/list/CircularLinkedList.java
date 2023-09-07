package list;

public class CircularLinkedList<E> implements ListInterface<E> {
    // 더미 헤드를 가진 원형 연결 리스트
    // LinkedList<E>에서 tail과 관계된 부분만 바뀐다.

    private Node<E> tail;
    private int numItems;

    public CircularLinkedList() { // 생성자
        numItems = 0;
        tail = new Node<>(null);
        tail.next = tail;
    }

    @Override
    public void add(int index, E item) { // 첫 번째 원소는 0번 원소
        if (index >= 0 && index <= numItems) {
            Node<E> prevNode = getNode(index - 1);
            Node<E> newNode = new Node<>(item, prevNode.next);
            prevNode.next = newNode;
            if (index == numItems) {
                tail = newNode;
            }
            numItems++;
        }
    }

    @Override
    public void append(E item) {
        Node<E> prevNode = tail; // 더미 노드
        Node<E> newNode = new Node<>(item,tail.next);
        prevNode.next = newNode;
        tail = newNode;
        numItems++;
    }

    @Override
    public E remove(int index) {
        if (index >= 0 && index < numItems) {
            Node<E> prevNode = getNode(index - 1);
            E rItem = prevNode.next.item;
            prevNode.next = prevNode.next.next;
            if (index == numItems - 1) {
                tail = prevNode;
            }
            numItems--;
            return rItem;
        } else {
            return null;
        }
    }

    @Override
    public boolean removeItem(E x) {
        Node<E> currNode = tail.next; // 더미 헤드
        Node<E> prevNode;
        for (int i = 0; i < numItems; i++) {
            prevNode = currNode;
            currNode = currNode.next;
            if (((Comparable)(currNode.item)).compareTo(x) == 0) {
                prevNode.next = currNode.next;
                numItems--;
                return true;
            }
        }
        return false; // not found
    }

    @Override
    public E get(int index) {
        if (index >= 0 && index <= numItems - 1) {
            return getNode(index).item;
        } else {
            return null; // 에러
        }
    }

    public Node<E> getNode(int index) {
        if (index >= -1 && index <= numItems - 1) {
            Node<E> currNode = tail.next; // 더미 노드
            for (int i = 0; i <= index; i++) {
                currNode = currNode.next;
            }
            return currNode;
        } else {
            return null;
        }
    }

    @Override
    public void set(int index, E x) {
        if (index >= 0 && index <= numItems - 1) {
            getNode(index).item = x;
        } else {
            /* 에러 처리 */
        }
    }

    public final int NOT_FOUND = -12345;
    @Override
    public int indexOf(E x) { // 원소 x의 인덱스 리턴
        Node<E> currNode = tail.next; // 더미 노드
        for (int i = 0; i < numItems; i++) {
            currNode = currNode.next;
            if (((Comparable)(currNode.item)).compareTo(x) == 0) {
                return i;
            }
        }
        return NOT_FOUND; // not found
    }

    @Override
    public int len() {
        return numItems;
    }

    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public void clear() {
        numItems = 0;
        tail = new Node<>(null);
        tail.next = tail;
    }
}
