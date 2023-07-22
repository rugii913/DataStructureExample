package sorting;

import list.LinkedList;

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
    
    // [알고리즘 9-12] 구현: 계수 정렬 - 정렬하고자 하는 원소들의 값이 -O(n) ~ O(n)의 범위에 있는 정수인 경우
    public int[] countingSort(int k) { // A[0 ... n-1] belong to integers 0 ~ k-1
        /*처음부터 봐야 함*/
        int[] cnt = new int[k];

        for (int i = 0; i < k; i++) {
            cnt[i] = 0;  // cnt 모든 값 0으로 초기화
        }

        // 알고리즘 9-12의 2번 부분: cnt[i]는 값이 i인 원소의 총 후
        for (int i = 0; i < array.length; i++) {
            cnt[array[i]]++;
        }

        // 알고리즘 9-12의 3번 부분: 누적합 계산
        cnt[0]--;
        // => p.308에서는 tempArray[cnt[array[j]] - 1] = array[j]로 되어있었는데, (cnt[i] - 1 위치에 i가 뒤에서 처음 등장)
        // tempArray[cnt[array[j]]] = array[j]로 바꿨다. (cnt[i] 위치에 i가 뒤에서 처음 등장)
        for (int i = 1; i < k; i++) {
            cnt[i] += cnt[i - 1];
        }

        // 알고리즘 9-12의 4번 부분: 원소들을 count에 맞춰 배치한 뒤 반환
        int[] tempArray = new int[array.length];
        // stable sorting을 만들기 위해 역순으로 순회
        // 지금은 단순한 기초 타입 int이지만 객체를 비교한다면, 비교 대상인 필드 외에 순서를 stable하게 유지하는 것이 의미있는 다른 정보가 있을 수도 있다.
        // 예를 들어 엑셀로 부서원 정보를 관리한다고 했을 때, 이름으로 오름차순 조회 후 부서로 다시 오름차순 조회하는 경우를 생각해보자.
        for (int j = array.length - 1; j >= 0; j--) {
            tempArray[cnt[array[j]]] = array[j];
            cnt[array[j]]--;
        }

        return tempArray;
    }
    
    // [알고리즘 9-13] 구현: 기수 정렬 - 상수 k개 이하의 자릿수를 가진 자연수와 같은 특수한 경우, 혹은 제한된 길이를 가진 알파벳들인 경우
    public void radixSort() { // A[0 ... n-1]은 최대 numDigits 자릿수의 양의 정수
        int[] cnt = new int[10], start = new int[10];
        int[] tempArray = new int[array.length];

        // 최대 자릿수 계산
        int max = -1;
        for (int i = 0; i < array.length; i++) { // 비교해서 가장 큰 수를 찾기  
            if (array[i] > max) {
                max = array[i];
            }
        }
        int numDigits = (int) Math.log10(max) + 1; // ex. max == 1234 라면 numDigits는 4

        for (int digit = 1; digit <= numDigits; digit++) { // for가 중첩되어 있지만 배열의 크기 n과 관련이 없다.
            for (int d = 0; d <= 9; d++) { // cnt: 각 자릿수에서 작업할 때 i가 몇 개 들어있는지를 카운트한 것
                cnt[d] = 0; // 각 digit에서의 작업마다 cnt의 모든 원소를 0으로 초기화
            }

            for (int i = 0; i < array.length; i++) { // n과 관련이 있는 부분 - n번 순회하며 해당 자릿수의 값이 몇 개인지 count
                cnt[(int)(array[i] / Math.pow(10, digit - 1)) % 10]++;
                // pow(a, b)는 a^b를 반환 => (int)(array[i] / Math.pow(10, digit - 1)) % 10는 자릿수의 값을 반환하게 되어있음
                /*
                * cnt[array[i] % (int) Math.pow(10, digit)]++;로 해도 동일하지 않을까? - x 아님, ex. 4324를 10^2로 나눈 나머지는 24
                * 해당 자릿수 아래 자릿수를 없애버리기 위해 이런 식으로 작업한 것
                * */
            }
            // ex. 10^0 자릿수 작업 - array = [14, 2345, 1234, 111]라면 cnt[1] = 1, cnt[4] = 2, cnt[5] = 1인 상태

            start[0] = 0;
            for (int d = 1; d <= 9; d++) {
                start[d] = start[d - 1] + cnt[d - 1];
            }
            // 계수 정렬에서 했던 것처럼 누적합과 유사
            // ex. 10^0 자릿수 작업 - array = [14, 2345, 1234, 111]라면
            // start[1] = 0, start[2] = 1, ..., start[4] = 1, start[5] = 3, start[6] = 4인 상태

            for (int i = 0; i < array.length; i++) {
                tempArray[start[(int) (array[i] / Math.pow(10, digit - 1)) % 10]++] = array[i];
            }
            // ex. 10^0 자릿수 작업 - array = [14, 2345, 1234, 111]라면
            // (int) (array[i] / Math.pow(10, digit - 1)) % 10: array[i]의 10^0 자릿수 값을 나타냄을 생각하면서
            // i = 0일 때, tempArray[start[4]++] = array[0] => tempArray[1] = array[0] 그리고 ++에 의해 start[4] = 2
            // i = 1일 때, tempArray[start[5]++] = array[1] => tempArray[3] = array[1] 그리고 ++에 의해 start[5] = 4
            // i = 2일 때, tempArray[start[4]++] = array[2] => tempArray[2] = array[2] 그리고 ++에 의해 start[4] = 3
            // i = 3일 때, tempArray[start[1]++] = array[3] => tempArray[0] = array[3] 그리고 ++에 의해 start[1] = 1
            // => tempArray = [111, 14, 1234, 2345]

            for (int i = 0; i < array.length; i++) {
                array[i] = tempArray[i];
            }
            // ex. 10^0 자릿수 작업 - array = [14, 2345, 1234, 111]였다면
            // => array = [111, 14, 1234, 2345]: 10^자릿수에 대해 정렬 완료
            // cf. stable sorting으로 순서 유지됨
            // 특정 자릿수에 대한 작업 끝, 다시 for문 앞으로 돌아가서 더 큰 자릿수에 대해 작업
        }
    }
    
    // [알고리즘 9-14] 구현: 버킷 정렬 - 균등 분포를 가정할 때
    public void bucketSort() {
        // 일단 이 구현 코드에서는 A[0 ... ]: 음이 아닌 정수 범위에서 균등 분포 배열인 경우 가정
        LinkedList<Integer> tempArray[];
        int numLists = array.length; // array의 length == 링크드리스트의 개수 == tempArray의 length
        tempArray = new LinkedList[numLists];

        for (int i = 0; i < numLists; i++) { // tempArray[] 각 원소를 빈 링크드리스트로 초기화
            tempArray[i] = new LinkedList();
        }

        int max;
        if (array[0] < array[1]) {
            max = 1;
        } else {
            max = 0;
        }

        for (int i = 2; i < array.length; i++) {
            if (array[max] < array[i]) {
                max = i;
            }
        }

        int band = array[max] + 1;
        int bucketId;
        for (int i = 0; i < array.length; i++) {
            bucketId = (int) ((float) (array[i] / band) * numLists);
            tempArray[bucketId].add(0, array[i]);
        }

        int finger = 0;
        int p, r = -1;
        for (int i = 0; i < numLists; i++) {
            for (int j = 0; j < tempArray[i].len(); j++) {
                array[finger++] = tempArray[i].getNode(j).item;
            }
            p = r + 1;
            r = finger -1;
            rangeInsertionSort(p, r);
        }
    }

    private void rangeInsertionSort(int p, int r) {
        for (int i = p + 1; i <= r; i++) {
            int loc = i - 1;
            int x = array[i];
            while (loc >= p && x < array[loc]) {
                array[loc + 1] = array[loc];
                loc--;
            }
            array[loc + 1] = x;
        }
    }
}
