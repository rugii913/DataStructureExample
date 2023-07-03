package list;

import org.junit.jupiter.api.Test;

class LinkedListItemIteratorTest {

    @Test
    void linkedListItemIteratorTest() {
        LinkedList list = new LinkedList();
        list.add(0, 200);
        list.add(0, 100);
        list.append(500);
        list.append(600);
        list.append(700);

        LinkedListItemIterator<Integer> iter = new LinkedListItemIterator<Integer>(list);
        while (iter.hasNext()) {
            System.out.println(iter.next());
            // 구현 3: 구현 1 바탕
            // System.out.println(iter.next().item);에서 System.out.println(iter.next())으로 바뀜
            // 출력 결과: 100 200 500 600 700
        }
    }
}