package list;

public class CircularDoublyLinkedList<E> implements ListInterface<E> {
    // 더미 헤드를 가진 원형 양방향 연결 리스트
    // 리스트에서 마지막 원소의 next가 헤드 노드를 가리키고, 헤드 노드의 prev가 마지막 노드를 가리킨다.
    // getNode() 메서드 다시 살펴볼 것

    private BidirectionalNode<E> head;
    private int numItems;

    public CircularDoublyLinkedList() { // 생성자
        numItems = 0;
        head = new BidirectionalNode<>(null); // 더미 헤드
        head.next = head.prev = head;
    }

    @Override
    public void add(int index, E item) { // 첫 번째 원소는 0번 원소
        if (index >= 0 && index <= numItems) {
            BidirectionalNode<E> prevNode = getNode(index - 1);
            BidirectionalNode<E> newNode = new BidirectionalNode<>(prevNode, item, prevNode.next);
            newNode.next.prev = newNode;
            prevNode.next = newNode;
            numItems++;
        } else {
            /* 에러 처리 */
        }
    }

    @Override
    public void append(E item) {
        BidirectionalNode<E> prevNode = head.prev; // 더미 노드
        BidirectionalNode<E> newNode = new BidirectionalNode<>(prevNode, item, head);
        prevNode.next = newNode;
        head.prev = newNode;
        numItems++;
    }

    @Override
    public E remove(int index) {
        if (index >= 0 && index < numItems) {
            BidirectionalNode<E> currNode = getNode(index);
            currNode.prev.next = currNode.next;
            currNode.next.prev = currNode.prev;
            numItems--;
            return currNode.item;
        } else {
            return null;
        }
    }

    @Override
    public boolean removeItem(E x) {
        BidirectionalNode<E> currNode = head; // 더미 헤드
        for (int i = 0; i < numItems; i++) {
            currNode = currNode.next;
            if (((Comparable)(currNode.item)).compareTo(x) == 0) {
                currNode.prev.next = currNode.next;
                currNode.next.prev = currNode.prev;
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

    public BidirectionalNode<E> getNode(int index) { // 첫 번째 원소는 0번 원소
        if (index >= -1 && index <= numItems - 1) { // ex. numItems = 9, index = 9이면 index > numItems - 1이므로 null
            BidirectionalNode<E> currNode = head;
            if (index < numItems/2) { // ex. numItems = 8이면 (-1), 0, 1, 2, 3 -> 4개 / numItems = 9이면 (-1), 0, 1, 2, 3 -> 4개
                for (int i = 0; i <= index; i++) { // index = 0이면 한 번 수행 -> head.next인 node
                    currNode = currNode.next;
                }
            } else { // ** 다시 생각해볼 것
                for (int i = numItems - 1; i >= index; i--) {
                    currNode = currNode.prev;
                    // numItems = 9, index = 8이면 int i = 8, i >= index 동안 -> 한 번 수행 -> head.prev인 node
                    // numItems = 9, index = 7이면 int i = 8, i >= index 동안 -> 두 번 수행 -> head.prev.prev인 node
                }
            }
            return currNode;
        } else {
            return null; // 에러
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
        BidirectionalNode<E> currNode = head; // 더미 노드
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
        head.next = head.prev = head;
    }
}
