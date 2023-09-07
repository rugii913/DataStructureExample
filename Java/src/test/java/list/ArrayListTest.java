package list;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    @Test
    void arrayListTest1_Integer() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0, 300); // 오토박싱으로 300은 Integer(300)으로 취급해준다.
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

    @Test
    void arrayListTest2_String() {
        ArrayList<String> list = new ArrayList<>();
        list.add(0, "test");
        list.add(0, "sample");
        list.add(0, "String");
        list.append("test2");
        list.remove(3);
        list.add(3, "added-at-3");
        list.add(1, "added-at-1");
        list.add(0, "added-at-0");
        list.append("appended");
        list.remove(1);
        list.removeItem("added-at-3");
        assertEquals("added-at-0", list.get(0));
        assertEquals("added-at-1", list.get(1));
        assertEquals("sample", list.get(2));
        assertEquals("test", list.get(3));
        assertEquals("appended", list.get(4));
    }
}