package list;

public class ArrayList<E> implements ListInterface<E> {

    private E item[]; // -> 표현이 헷갈린다... E[] item;으로도 가능하다. // 선언 String array[]; 생각해볼 것

    private int numItems;

    private static final int DEFAULT_CAPACITY = 64;

    public ArrayList() { // 생성자 1
        item = (E[]) new Object[DEFAULT_CAPACITY]; // 컴파일러가 경고를 주지만 ok
        numItems = 0;
    }

    public ArrayList(int n) { // 생성자 2
        item = (E[]) new Object[n]; // 컴파일러가 경고를 주지만 ok
        numItems = 0;
    }

    // [알고리즘 5-1] 구현: 배열 리스트의 k번째 자리에 원소 x 삽입하기
    // -> 참고사항: 여기서는 구현하지 않았지만,
    // -> ArrayList의 공간이 다 차면,
    // -> 더 큰 배열을 할당해서 원소들을 모두 옮겨주는 작업을 한다.
    @Override
    public void add(int index, E x) {
        if (numItems >= item.length || index < 0 || index > numItems) {
            /* 에러 처리 */
        } else {
            for (int i = numItems - 1; i >= index; i--) {
                item[i + 1] = item[i]; // 우시프트
            }
            item[index] = x;
            numItems++;
        }
    }

    // [알고리즘 5-2] 구현: 배열 리스트의 맨 뒤에 원소 추가하기
    @Override
    public void append(E x) {
        if (numItems >= item.length) {
            /* 에러 처리 */
        } else {
            item[numItems++] = x; //x를 item[numItems] 자리에 대입한 뒤에 numItems += numItems
        }
    }

    // [알고리즘 5-3] 구현: 배열 리스트의 k번째 원소 삭제하기
    @Override
    public E remove(int index) {
        if (isEmpty() || index < 0 || index > numItems - 1) {
            return null;
        } else {
            E tmp = item[index];
            for (int i = index; i <= numItems - 2; i++) {
                item[i] = item[i + 1]; // 좌시프트
            }
            numItems--;
            return tmp;
        }
    }

    // [알고리즘 5-4] 구현: 배열 리스트에서 원소 x 삭제하기
    @Override
    public boolean removeItem(E x) {
        int k = 0;
        while (k < numItems && ((Comparable)item[k]).compareTo(x) != 0) {
            k++;
        }
        if (k == numItems) { // -> k < numItems 조건을 만족하지 못해서 빠져나온 경우
            return false;
        } else { // -> item[k].compareTo(x) == 0 때문에 빠져나온 경우
            for (int i = k; i <= numItems - 2; i++) {
                item[i] = item[i+1]; // 좌시프트
            }
            numItems--;
            return true;
        }
    }

    // [알고리즘 5-5] 구현: 배열 리스트의 i번째 원소 알려주기
    @Override
    public E get(int index) { // 첫 번째 원소를 0번째 원소로 표기
        if (index >= 0 && index <= numItems - 1) {
            return item[index];
        } else {
            return null;
        }
    }

    // [알고리즘 5-6] 구현: 배열 리스트의 i번째 원소를 x로 대체하기
    @Override
    public void set(int index, E x) {
        if (index >= 0 && index <= numItems - 1) {
            item[index] = x;
        } else {
            /* 에러 처리 */
        }
    }

    // [알고리즘 5-7] 구현: 원소 x가 배열 리스트의 몇 번째 원소인지 알려주기
    private final int NOT_FOUND = -12345;
    @Override
    public int indexOf(E x) {
        int i = 0;
        for (i = 0; i < numItems; i++) {
            if (((Comparable)item[i]).compareTo(x) == 0) {
                return i;
            }
        }
        return NOT_FOUND; // not exist
    }

    // [알고리즘 5-8(1)] 구현: 배열 리스트의 총 원소 수 알려주기
    @Override
    public int len() {
        return numItems;
    }

    // [알고리즘 5-8(2)] 구현: 배열 리스트가 비었는지 알려주기
    @Override
    public boolean isEmpty() {
        return numItems == 0;
    }

    // [알고리즘 5-8(3)] 구현: 배열 리스트 깨끗이 청소하기
    @Override
    public void clear() {
        // item = new E[item.length]; -> 타입 매개변수 'E'를 직접 인스턴스화할 수 없습니다.
        item = (E[]) new Object[item.length];
        numItems = 0;
    }
}
