package heap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeapTest {

    @Test
    void clearTest() throws PQException {
        Heap<Integer> heap = new Heap<>(5);
        heap.insert(1);
        heap.insert(1);
        heap.clear();
        Assertions.assertEquals(true, heap.isEmpty());
    }

}