package sorting;

public class Sorting {

    int array[];

    public Sorting(int[] array) {
        this.array = array;
    }

    // [알고리즘 9-1] 구현: 선택 정렬
    public void selectionSort() {
        int k;
        int tmp;

        for (int last = array.length - 1; last >= 1; last--) {
            k = theLargest(last); // array[0 ... last] 중 가장 큰 수 array[k] 찾기

            tmp = array[k];
            array[k] = array[last];
            array[last] = tmp;
        }
    }

    private int theLargest(int last) { // array[0 ... last]에서 가장 큰 수의 인덱스를 반환한다.
        int largest = 0;
        for (int i = 0; i <= last; i++) {
            if (array[i] > array[largest]) {
                largest = i;
            }
        }
        return largest;
    }

    // [알고리즘 9-3] 구현: 버블 정렬
    public void bubbleSort() {
        int tmp = 0;
        boolean swapped;

        for (int last = array.length - 1; last >= 1; last--) { // 책에는 last > = 2;로 나와있는데 그러면 맨 앞 두 개가 정렬이 안 될 듯
            swapped = false;

            for (int i = 0; i <= last - 1; i++) {
                if (array[i] > array[i + 1]) {
                    tmp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = tmp;

                    swapped = true;
                }

                if (swapped == false) {
                    break; // 원소 교환이 발생했는지 체크하는 코드 - last까지 훑었는데 정렬되어 있다면 swapped false => 정렬 수행 종료
                }
            }
        }
    }

    /*
    * selection sort vs. bubble sort
    * 선택 정렬은 반복문 순회하면서 largest의 index를 저장해두다가 마지막에 배열 가장 끝으로 보냄
    * 버블 정렬은 차례로 비교하면서 순서가 제대로 되어 있지 않으면 그 때마다 바꿈
    * -> 이 부분 때문에 버블 정렬이 선택 정렬보다 느리다.
    * */

    // [알고리즘 9-4] 구현: 삽입 정렬
    public void insertionSort() {
        for (int i = 1; i <= array.length - 1; i++) {
            int loc = i - 1; // 인덱스 i - 1까지는 정렬이 완료되있는 상태임을 변수 loc에 표시해둠 // loc: location?
            int newItem = array[i];

            while (loc >= 0 && newItem < array[loc]) { // 비교 대상 loc index 0까지 비교 반복, newItem이 비교 대상보다 작다면
                array[loc + 1] = array[loc]; // newItem이 비교 대상보다 작다면, loc의 원소의 인덱스를 하나 늘려줌
                loc--;
            }
            array[loc + 1] = newItem; // 마지막에 loc--를 했으므로 loc + 1 해줌, index 0까지 갔을 때를 생각해볼 것
        }
    }

    /*
    * insertion sort (vs. selection sort vs. bubble sort)
    * 삽입 정렬은 배열이 거의 정렬된 상태로 입력된다면 가장 매력적인 알고리즘이 됨.
    * (cf. 선택 정렬과 버블 정렬은 정렬되지 않은 배열의 크기를 줄인다 - 삽입 정렬은 정렬된 배열의 크기를 늘린다. > 선택 정렬과 버블 정렬은 거의 유사)
    * 다른 정렬을 사용하는 경우에도 상황에 따라 삽입 정렬을 섞어서 쓰면 효율적이다.
    * */

    // [알고리즘 9-6] 구현: 병합 정렬
    public void mergeSort() {
        int[] tempArray = new int[array.length]; // 새 배열을 생성하기 때문에 내부 정렬(In-Place Sorting)이 되지 못함
        mSort(0, array.length - 1, tempArray);
    }

    private void mSort(int p, int r, int[] tempArray) { // 1/2 크기 기준으로 나누고, merge(~) 호출하는 역할
        if (p < r) {
            int q = (p + r)/2; // cf. 부분 배열이 홀수 개면 앞 부분이 더 크고, 짝수 개면 두 부분 크기가 같음
            mSort(p, q, tempArray);
            mSort(q + 1, r, tempArray);
            merge(p, q, r, tempArray);
        }
    }

    private void merge(int p, int q, int r, int[] tempArray) {
        int i = p; // 왼쪽 부분 배열 시작 인덱스
        int j = q + 1; // 오른쪽 부분 배열 시작 인덱스
        int t = 0;

        while (i <= q && j <= r) {
            if (array[i] <= array[j]) { // 현재 i, j 인덱스 위치의 값을 비교하고 작은 것을 tempArray t 인텍스 위치에 대입
                tempArray[t++] = array[i++];
            } else {
                tempArray[t++] = array[j++];
            }
        }

        while (i <= q) { // 왼쪽 부분 배열이 남은 경우 // 남지 않았다면 i = q + 1로 q보다 커져 있는 상태
            tempArray[t++] = array[i++]; // 부분 배열의 나머지 원소를 털어준다.
        }

        while (j <= r) { // 오른쪽 부분 배열이 남은 경우 // 남지 않았다면 j = r + 1로 r보다 커져 있는 상태
            tempArray[t++] = array[j++];
        }

        i = p;
        t = 0;
        while (i <= r) { // 결과를 A[p ... r]에 저장
            array[i++] = tempArray[t++];
        }
        // array = tempArray;로 단순하게 끝내지 않는 이유가 있을까?
    }

    // [알고리즘 9-8] 구현: 퀵 정렬
    public void quickSort() {
        qSort(0, array.length - 1);
    }

    private void qSort(int p, int r) {
        if (p < r) {
            int q = partition(p, r); // 기준 정해서 작은 부분 / 기준 / 큰 부분 나누기
            qSort(p, q - 1); // 작은 부분 정렬
            qSort(q + 1, r); // 큰 부분 정렬
        }
    }

    private int partition(int p, int r) {
        int x = array[r]; // 기준이 되는 원소 - 이 경우에는 부분 배열 맨 뒤 원소를 잡음
        int i = p - 1; // i + 1이 나중에 기준 원소가 들어갈 인덱스가 됨
        int tmp;

        for (int j = p; j <= r - 1; j++) {
            if (array[j] <= x) { // 인덱스 j 위치에 있는 수가 기준보다 작으면 왼쪽에 쌓아나감
                i++;
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 맨 끝에 있던 기준 원소를 i + 1 위치로 교환 => [p부터 i], [i + 1], [i + 2부터 r]로 array[i + 1]의 크기 기준으로 갈라짐
        tmp = array[i + 1];
        array[i + 1] = array[r];
        array[r] = tmp;

        return i + 1; // 기준 원소의 인덱스 반환
    }

    // [알고리즘 9-10] 구현: 힙 정렬
    public void heapSort() {
        buildHeap();
        int tmp;

        for (int i = array.length - 1; i >= 1; i--) {
            // 힙의 특성에 따라 index 0에는 가장 큰 원소 -> 맨 끝으로 보냄
            // 크기가 1 작아진 부분 배열로 스며내리기 반복
            tmp = array[0];
            array[0] = array[i];
            array[i] = tmp;
            percolateDown(0, i - 1);
        }
    }

    private void buildHeap() { // 기존 힙 코드와 같다.
        if (array.length >= 2) {
            for (int i = (array.length - 2) / 2; i >= 0; i--) {
                percolateDown(i, array.length - 1);
            }
        }
    }

    private void percolateDown(int i, int n) { // 다른 부분은 기존 힙의 코드와 같지만 i-1까지만 대상으로 하는 점이 다르다.
        int child = 2 * i + 1; // 일단은 left child의 index
        int rightChild = 2 * 1 + 2;

        if (child <= n) { // child > n이면 대상이 아니거나(정렬 시킨 부분) i가 리프 노드의 index이다.
            if ((rightChild <= n) && (array[child] < array[rightChild])) {
                child = rightChild; // child는 더 큰 자식의 index가 됨
            }
            if (array[i] < array[child]) {
                int tmp = array[i];
                array[i] = array[child];
                array[child] = tmp;
                percolateDown(child, n);
            }
        }
    }

    // [알고리즘 9-11] 구현: 셸 정렬
    public void shellSort() {
        for (int h = array.length / 7; h > 5; h = h / 5 - 1) {
            // ex. n = 50이면 gap sequence는 {7}, n = 200이면 {28}, n = 500이면 {71, 13}, n = 3000이면 {428, 84, 15}
            // array.length가 41이라면 조건에서 튕겨져 나감, 42일 때부터 h = 6으로 시작
            for (int k = 0; k <= h - 1; k++) { // ex. n = 500이면 k = 0 ~ 70에 대해서 작업 후, k = 0 ~ 12에 대해서 작업
                stepInsertionSort(k, h);
            }
        }
        stepInsertionSort(0, 1); // 맨 마지막에 전체 삽입 정렬
    }

    private void stepInsertionSort(int k, int h) { // k: 작업 시작 인덱스, h: gap
        int j, insItem; // 아래 코드에서 j: 이미 지나온 인덱스, insItem: 현재 삽입해야할 원소가 됨

        for (int i = k + h; i <= array.length - 1; i += h) {
            insItem = array[i];
            for (j = i - h; j >= 0 && array[j] > insItem; j -= h) {
                array[j + h] = array[j]; // array[j]가 insItem보다 크면 j 인덱스의 원소를 j + h 인덱스로 보냄
            }
            array[j + h] = insItem;
            // 위 반복문 조건에서 j -= h 실행 후 튕겨져 나왔으므로, j + h 인덱스 위치에 둬야 한다.
            // j 위치에 두면, h를 한 번 더 뺀 상태가 됨.
            // ex. h = 7이고, 현재 j = 6으로 작업을 마친 후 다시 반복해야하는 상황을 생각해볼 것
            // => j = -1로 바뀌고 튕겨져 나감 => +7을 해줘야 생각했던 곳 j = 6에 위치하게 된다.
        }
    }
}
