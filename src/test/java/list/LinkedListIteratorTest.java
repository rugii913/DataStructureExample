package list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListIteratorTest {

    @Test
    void LinkedListIteratorTest() {
        LinkedList list = new LinkedList();
        list.add(0, 200);
        list.add(0, 100);
        list.append(500);
        list.append(600);
        list.append(700);

        LinkedListIterator iter = new LinkedListIterator(list);
        while (iter.hasNext()) {
            System.out.println(iter.next().item);
            // 구현 1: 생성 후 current는 더미 헤드
            // 처음 iter.next()하면
            // index 0 노드를 current에 할당하면서, 새로 할당된 current(= index 0 노드)를 반환
            // 출력 결과: 100 200 500 600 700

            // 구현 2: 생성 후 nextNode는 index 0 노드
            // 처음 iter.next()하면
            // 원래의 nextNode는 tmp로 참조하고 
            // index 1 노드를 nextNode에 할당하면서, tmp로 참조한 index 0 노드를 반환 
            // 출력 결과: 100 200 500 600 700
        }
    }
}