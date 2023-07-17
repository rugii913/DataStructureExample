package heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    @Test
    void clearTest() throws PQException {
        Heap<Integer> heap = new Heap<>(5);
        heap.insert(1);
        heap.insert(10);
        heap.clear();
        Assertions.assertEquals(true, heap.isEmpty());
    }

    @Test
    void exceptionTest() throws PQException {
        Heap<Integer> heap = new Heap<>(5);
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);
        heap.insert(50);
        heap.deleteMax();
        heap.insert(1);
        Assertions.assertThrows(PQException.class, () -> heap.insert(2), "HeapErr: Insert()-Overflow!");
    }

}