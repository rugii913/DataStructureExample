package stack;

import list.Node;

public class LinkedStack<E> implements StackInterface<E> {
    
    private Node<E> topNode;
    private final E ERROR = null; // 임의의 에러 값

    public LinkedStack() {
        topNode = null;
    }

    // [알고리즘 6-7] 구현: 스택에 newItem이 있는 Node 삽입하기
    @Override
    public void push(E newItem) {
        topNode = new Node<>(newItem, topNode);
    }

    // [알고리즘 6-8] 구현: 스택에서 원소 삭제하기
    @Override
    public E pop() {
        if (isEmpty()) {
            return ERROR;
        } else {
            Node<E> temp = topNode;
            topNode = topNode.next;
            return temp.item;
        }
    }

    // [알고리즘 6-9] 구현: 스택 탑 원소 알려주기
    @Override
    public E top() {
        if (isEmpty()) {
            return ERROR;
        } else {
            return topNode.item;
        }
    }

    // [알고리즘 6-10] 구현: 스택이 비었는지 확인하기
    @Override
    public boolean isEmpty() {
        return (topNode == null);
    }
    
    // LinkedStack에서는 ArrayStack과 달리 isFull()이 필요하지 않음
    // -> 이 때문에 인터페이스에서도 isFull()을 선언하지 않았음

    // [알고리즘 6-11] 구현: 스택 비우기
    @Override
    public void popAll() {
        topNode = null;
    }
}
