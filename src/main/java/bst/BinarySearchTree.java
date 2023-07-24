package bst;

public class BinarySearchTree implements IndexInterface<TreeNode> {

    private TreeNode root;

    public BinarySearchTree() { // 생성자
        root = null;
    }

    // [알고리즘 10-1] 구현: 검색
    @Override
    public TreeNode search(Comparable searchKey) {
        return searchItem(root, searchKey);
    }

    private TreeNode searchItem(TreeNode tNode, Comparable searchKey) {
        if (tNode == null) {
            return null; // 검색 실패
        } else if (searchKey.compareTo(tNode.key) == 0) {
            return tNode;
        } else if (searchKey.compareTo(tNode.key) < 0) {
            return searchItem(tNode.left, searchKey);
        } else {
            return searchItem(tNode.right, searchKey);
        }
    }

    // [알고리즘 10-3] 구현: 삽입
    @Override
    public void insert(Comparable newKey) {
        root = insertItem(root, newKey);
    }

    private TreeNode insertItem(TreeNode tNode, Comparable newItem) {
        if (tNode == null) { // inert after a leaf (or into an empty tree)
            tNode = new TreeNode(newItem);
        } else if (newItem.compareTo(tNode.key) < 0) { // branch left
            tNode.left = insertItem(tNode.left, newItem);
        } else { // branch right
            tNode.right = insertItem(tNode.right, newItem);
        } // 이 구현에서 주의사항: 중복 key가 존재하는 경우에 대한 처리는 없음, right에 집어 넣고 있다.
        return tNode;
    }

    // [알고리즘 10-7] 구현: 삭제
    @Override
    public void delete(Comparable searchKey) {
        root = deleteItem(root, searchKey);
        // cf. 현재 구현에서, 없는 키 값을 삭제하려고 시도할 때 처리가 없음
    }

    private TreeNode deleteItem(TreeNode tNode, Comparable searchKey) { // 삭제할 노드를 찾는 작업
        if (tNode == null) { // key not found!
            return null;
        } else {
            if (searchKey == tNode.key) { // key found at tNode
                tNode = deleteNode(tNode); // 실제 삭제는 여기서 - 자식이 있을 경우 적절한 자식 노드를 땡겨서 참조를 바꿈
            } else if (searchKey.compareTo(tNode.key) < 0) {
                tNode.left = deleteItem(tNode.left, searchKey);
                // => tNode.left가 참조하는 것이 deleteItem(~) 결과 반환된 노드로 바뀜
                // (바로 아래 자식이 삭제 대상이 아니라면 참조가 바뀌지는 않음)
            } else {
                tNode.right = deleteItem(tNode.right, searchKey);
                // => tNode.right가 참조하는 것이 deleteItem(~) 결과 반환된 노드로 바뀜
                // (바로 아래 자식이 삭제 대상이 아니라면 참조가 바뀌지는 않음)
            }
            return tNode;
        }
    }

    private TreeNode deleteNode(TreeNode tNode) { // 실제로 삭제하는 작업
        /*
        * 3가지 case
        *   1. tNode가 리프 노드
        *   2. tNode의 자식이 하나
        *   3. tNode의 자식이 둘 => ***** 복잡해지는 경우
        * */
        if ((tNode.left == null) && (tNode.right == null)) { // case 1(자식이 없음)
            return null; // 반환값에 의해 부모 노드의 left 혹은 right 참조가 바뀜
        } else if (tNode.left == null) { // case 2(오른자식 뿐)
            return tNode.right;
        } else if (tNode.right == null) { // case 2(왼자식 뿐)
            return tNode.left;
        } else { // case 3(두 자식이 다 있음)
            ReturnPair returnPair = deleteMinItem(tNode.right); // 오른 서브트리에서 최소 key 값과, right로 이어줄 노드를 가져옴
            tNode.key = returnPair.key; // 오른 서브트리의 최소 key 값
            tNode.right = returnPair.node; // right로 이어줄 노드 가져온 것을 이어줌
            return tNode;
            // => 오른 서브트리에서 최소 key 값 가져 오면서, 서브트리를 정리해주는 작업
        }
    }

    private ReturnPair deleteMinItem(TreeNode tNode) {
        if (tNode.left == null) { // tNode.left == null이면 tNode 자체가 오른 서브트리에서 최솟값이므로 간단
            return new ReturnPair(tNode.key, tNode.right); // 최솟값인 tNode의 key와 땡겨올 바로 right 노드를 전달
        } else {
            ReturnPair returnPair = deleteMinItem(tNode.left); // 오른 서브트리 내에서 최소 key 값을 찾아나가기
            tNode.left = returnPair.node;
            // => 위 deleteMinItem(~)에서 tNode.left == null이었다면, 땡겨온 노드를 tNode.left 참조로 바꿔치기
            // => null이 아니라서 최소 key를 찾아 더 내려갔다면, 참조는 같은 노드로 유지됨
            returnPair.node = tNode; // returnPair의 node를 현재 tNode로 바꾼 뒤에 반환함
            return returnPair;
        }
    }

    private class ReturnPair {
        private Comparable key;
        private TreeNode node;
        private ReturnPair(Comparable it, TreeNode nd) {
            key = it;
            node = nd;
        }
    }

    // 기타
    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }
}
