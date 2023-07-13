package stack;

import list.LinkedList;
import list.ListInterface;

public class ListStack<E> implements StackInterface<E> {

    // (앞의 InheritedStack이 상속을 이용했다면)
    // 여기서는 composition을 이용하여 ADT 리스트의 관점에서 이용한다.
    // InheritedStack에서 했듯이 LinkedList의 메서드들을 이용할 수 있다.
    // LinkedStack을 구현했듯이 구현
    // 상속 대비 composition의 장점을 가져갈 수 있음 - 캡슐화를 깨뜨리지 않음(private 필드 등...), 기존 클래스의 변화에 대해 적은 영향
    private ListInterface<E> list;

    public ListStack() { // 생성자
        this.list = new LinkedList<>(); // ArrayStack<E>()로도 가능
    }

    @Override
    public void push(E newItem) {
        list.add(0, newItem);
    }

    @Override
    public E pop() {
        return list.remove(0);
    }

    @Override
    public E top() {
        return list.get(0);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void popAll() {
        list.clear();
    }
}
