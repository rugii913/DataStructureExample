package list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircularDoublyLinkedListTest {

    @Test
    void circularLinkedListTest() {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.add(0, 300); // 오토 박싱
        list.add(0, 200);
        list.add(0, 100);
        list.append(500);
        list.append(600);
        list.remove(3);
        list.add(3, 250);
        list.add(1, 50);
        list.add(0, 10);
        list.append(700);
        list.remove(1);
        list.removeItem(600);
        assertEquals(10, list.get(0));
        assertEquals(50, list.get(1));
        assertEquals(200, list.get(2));
        assertEquals(300, list.get(3));
        assertEquals(250, list.get(4));
        assertEquals(700, list.get(5));
    }
}