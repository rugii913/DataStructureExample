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

    // [알고리즘 9-2] 구현: 버블 정렬
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

    // [알고리즘 9-3] 구현: 삽입 정렬
    public void insertionSort() {
        for (int i = 1; i <= array.length - 1; i++) {
            int loc = i - 1; // 인덱스 i - 1까지는 정렬이 완료되있는 상태임을 변수 loc에 표시해둠 // loc: location?
            int newItem = array[i];

            while (loc >= 0 && newItem < array[loc]) {
                array[loc + 1] = array[loc];
                loc--;
            }
            array[loc + 1] = newItem;
        }
    }

    /*
    * insertion sort (vs. selection sort vs. bubble sort)
    * 삽입 정렬은 배열이 거의 정렬된 상태로 입력된다면 가장 매력적인 알고리즘이 됨.
    * (cf. 선택 정렬과 버블 정렬은 정렬되지 않은 배열의 크기를 줄인다 - 삽입 정렬은 정렬된 배열의 크기를 늘린다. > 선택 정렬과 버블 정렬은 거의 유사)
    * 다른 정렬을 사용하는 경우에도 상황에 따라 삽입 정렬을 섞어서 쓰면 효율적이다.
    * */
}
