package stack;

public class ArrayStack<E> implements StackInterface<E>{
    
    private E stack[];
    private int topIndex; // 스택의 탑 인덱스
    private static final int DEFAULT_CAPACITY = 64;
    private final E ERROR = null; // 임의의 에러 값

    public ArrayStack() { // 생성자 1
        stack = (E[]) new Object[DEFAULT_CAPACITY];
        // p.188 관련) new E[DEFAULT_CAPACITY]로 만드려고 하면 "타입 매개변수 'E'을(를) 직접 인스턴스화할 수 없습니다."라고 컴파일 에러
        // new Object[DEFAULT_CAPACITY]로 만들고 (E[])로 타입 캐스팅 // "확인되지 않은 형변환"으로 경고가 뜨긴 함
        topIndex = -1;
    }

    public ArrayStack(int n) { // 생성자 2
        stack = (E[]) new Object[n];
        topIndex = -1;
    }

    // [알고리즘 6-1] 구현: 스택에 원소 x 삽입하기
    @Override
    public void push(E newItem) {
        if (isFull()) {
            /*에러처리*/
        } else {
            stack[++topIndex] = newItem;
        }
    }

    // [알고리즘 6-2] 구현: 스택에서 원소 삭제하기
    @Override
    public E pop() {
        if (isEmpty()) {
            return ERROR;
        } else {
            return stack[topIndex--];
        }
    }

    // [알고리즘 6-3] 구현: 스택 탑 원소 알려주기
    @Override
    public E top() {
        if (isEmpty()) {
            return ERROR;
        } else {
            return stack[topIndex];
        }
    }

    // [알고리즘 6-4] 구현: 스택이 비었는지 확인하기
    @Override
    public boolean isEmpty() {
        return (topIndex < 0);
    }

    // [알고리즘 6-5] 구현: 스택이 꽉 찼는지 확인하기
    // 일부러 그런 건지는 모르겠지만 Interface에 isFull()은 선언하지 않았음
    public boolean isFull() {
        return (topIndex == stack.length -1);
    }

    // [알고리즘 6-6] 구현: 스택 비우기
    @Override
    public void popAll() {
        stack = (E[]) new Object[stack.length];
        topIndex = -1;
    }
}
