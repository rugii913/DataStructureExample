package bst;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeTest {

    @Test
    void binarySearchTreeTest() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(10);
        bst.insert(20);
        bst.insert(5);
        bst.insert(80);
        bst.insert(90);
        bst.insert(75);
        bst.insert(30);
        bst.insert(77);
        bst.insert(15);

        Integer del = 20;
        bst.delete(del);
        
        // cf. 현재 구현에서, 없는 키 값을 삭제하려고 시도할 때 처리가 없음

        assertNull(bst.search(20));
        assertNotNull(bst.search(30));
    }
}