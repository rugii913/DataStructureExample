package hashTable;

import bst.IndexInterface;
import list.LinkedList;
import list.Node;

public class ChainedHashTable implements IntegerIndexInterface<Node> { 
    // p.363 bst.IndexInterface에서는 Comparable을 받기 때문에 Integer를 받도록 Override 할 수 없어서 다른 인터페이스를 새로 만듦

    private LinkedList<Integer>[] table;
    int numItems = 0;

    public ChainedHashTable(int n) {
        table = (LinkedList<Integer>[]) new LinkedList[n];
        for (int i = 0; i < n; i++) {
            table[i] = new LinkedList<>();
        }
        numItems = 0;
    }

    private int hash(Integer x) {
        return x % table.length; // 해시함수 Division Method 간단한 예시
    }

    // [알고리즘 12-1] 구현: 검색, 삽입, 삭제
    @Override
    public void insert(Integer x) {
        int slot = hash(x);
        table[slot].add(0, x);
        numItems++;
    }

    @Override
    public Node search(Integer x) {
        int slot = hash(x);
        if (table[slot].isEmpty()) {
            return null; // null list
        } else {
            int i = table[slot].indexOf(x);
            if (i == LinkedList.NOT_FOUND) {
                return null; // not exist
            } else {
                return table[slot].getNode(i);
            }
        }
    }

    @Override
    public void delete(Integer x) {
        if (isEmpty()) {
            /* 에러 처리 */
        } else {
            int slot = hash(x);
            table[slot].removeItem(x);
            numItems--;
        }
    }

    // 기타
    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
        numItems = 0;
    }
}
