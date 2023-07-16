package queue;

import list.Node;

public class LinkedQueue<E> implements QueueInterface<E> {
    // 연결 리스트로 구현한 것이 배열 리스트로 구현한 것에 비해 크기 제한이 없어서 편리

    private Node<E> tail;
    private final E ERROR = null; // 임의의 에러 값

    public LinkedQueue() {
        tail = null;
    }

    // [알고리즘 7-7] 구현: 큐에 원소 삽입하기
    @Override
    public void enqueue(E newItem) {
        Node<E> newNode = new Node<>(newItem);
        if (isEmpty()) {
            newNode.next = newNode;
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    // [알고리즘 7-8] 구현: 큐에서 원소 삭제하기
    @Override
    public E dequeue() {
        if (isEmpty()) {
            return ERROR;
        } else {
            Node<E> front = tail.next;
            if (front == tail) { // only one item in queue
                tail = null;
            } else { //  more than one item
                tail.next = front.next;
            }
            return front.item;
        }
    }

    // [알고리즘 7-9] 구현: 큐의 맨 앞 원소 알려주기
    @Override
    public E front() {
        if (isEmpty()) {
            return ERROR;
        } else {
            return tail.next.item; // tail.next: front
        }
    }

    // [알고리즘 7-10] 구현: 큐가 비었는지 확인하기
    @Override
    public boolean isEmpty() {
        return (tail == null);
    }
    
    // [알고리즘 7-11] 구현: 큐 비우기
    @Override
    public void dequeueAll() {
        tail = null;
    }
}
