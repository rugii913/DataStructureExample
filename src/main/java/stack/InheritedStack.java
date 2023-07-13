package stack;

import list.LinkedList;
import list.Node;

/*<Stack> p.197 InheritedStack ext LinkedList<E> impl StackInterface
        * StackInterface를 구현하는 데에 LinkedList에서 정의된 메서드와 필드를 이용함*/

public class InheritedStack<E> extends LinkedList<E> implements StackInterface<E> {
    // 상속을 통한 소프트웨어 재사용의 예, 어떤 클래스가 구축해놓은 기반 위에 추상 데이터 구조 레벨에서 다른 클래스를 만들 수 있다.

    public InheritedStack() {
        /*
        // LinkedList의 기본 생성자 코드
        numItems = 0;
        head = new Node<>(null, null); // 더미 헤드
        */
        super();
    }

    @Override
    public void push(E newItem) {
        add(0, newItem); // LinkedList의 add(int index, E item)
    }

    @Override
    public E pop() {
        if (!isEmpty()) {
            return remove(0); // LinkedList의 remove(int index)
        } else {
            return null;
        }
    }

    @Override
    public E top() {
        return get(0); // LinkedList의 get(int index)
    }

    // isEmpty()는 LinkedList에 정의되어 있고, 내용도 재정의할 필요가 없으므로
    // 굳이 다시 재정의 구현하지 않는다.

    @Override
    public void popAll() {
        clear(); // LinkedList의 clear()
    }
}
