package heap;

public class Heap<E extends Comparable> implements PQInterface<E> {

    private E[] heapArray;
    private int numItems;

    public Heap(int arraySize) {
        this.heapArray = (E[]) new Comparable[arraySize];
        this.numItems = 0;
    }

    public Heap(E[] array, int numElements) {
        this.heapArray = array; // 배열 레퍼런스 복사
        this.numItems = numElements;
    }

    // [알고리즘 8-2] 구현: 힙에 원소 삽입하기(재귀 알고리즘 버전)
    @Override
    public void insert(E newItem) throws PQException {
        // 힙 E[0 ... numItems - 1]에 원소 newItem을 추가한다.
        if (numItems < heapArray.length) {
            heapArray[numItems] = newItem;
            percolateUp(numItems);
            numItems++;
        } else {
            throw new PQException("HeapErr: Insert()-Overflow!");
        }
    }

    // 스며오르기 percolateUp()
    private void percolateUp(int i) {
        // 힙 E[i]에서 시작해서 힙 성질을 만족하도록 수선한다.
        // E[0 ... i-1]은 힙 성질을 만족하고 있음
        int parent = (i -1) / 2;
        if (parent >= 0 && heapArray[i].compareTo(heapArray[parent]) > 0) {
            E tmp = heapArray[i];
            heapArray[i] = heapArray[parent];
            heapArray[parent] = tmp;
            percolateUp(parent);
        }

    }

    // [알고리즘 8-3] 구현: 힙에서 원소 삭제하가
    @Override
    public E deleteMax() throws PQException {
        // 힙 E[0 ... numItems-1]에서 최댓값을 삭제하면서 리턴한다.
        if (!isEmpty()) {
            E max = heapArray[0];
            heapArray[0] = heapArray[numItems - 1];
            numItems--;
            percolateDown(0);
            return max;
        } else {
            throw new PQException("HeapErr: DeleteMax()-Underflow");
        }
    }

    // 스며내리기 percolateDown()
    private void percolateDown(int i) {
        // E[0 ... numItems-1]에서 A[i]를 루트로 스며내리기
        int child = 2 * i + 1; // 왼쪽 자식
        int rightChild = 2 * 1 + 2; // 오른쪽 자식
        if (child <= numItems - 1) {
            if (rightChild <= numItems - 1 && heapArray[child].compareTo(heapArray[rightChild]) < 0) {
                child = rightChild; // 왼쪽 오른쪽 자식을 비교해서 더 큰 자식의 인덱스를 child에 저장
            }
            if (heapArray[i].compareTo(heapArray[child]) < 0) {
                E tmp = heapArray[i];
                heapArray[i] = heapArray[child];
                heapArray[child] = tmp;
                percolateDown(child);
            }
        }
    }

    // [알고리즘 8-4] 구현: 힙 만들기
    public void buildHeap() {
        if (numItems >= 2) {
            for (int i = (numItems - 2) / 2; i >= 0; i--) {
                percolateDown(i);
            }
        }
    }

    // [알고리즘 8-5] 구현: 힙의 최댓값 구하기
    @Override
    public E max() throws PQException {
        if (!isEmpty()) {
            return heapArray[0];
        } else {
            throw new PQException("HeapErr: Max()-Empty!");
        }
    }

    // [알고리즘 8-6] 구현: 힙이 비었는지 확인하기
    @Override
    public boolean isEmpty() { // 힙이 비어있는지 알려준다.
        return numItems == 0;
    }

    // [알고리즘 8-7] 구현: 힙 비우기
    @Override
    public void clear() {
        heapArray = (E[]) new Comparable[heapArray.length];
        numItems = 0;
    }
}
