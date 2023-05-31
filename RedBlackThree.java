
public class RedBlackThree {
    private TreeNode head;
    private class TreeNode{
        private int value;
        private Color color;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;
    }
    private enum Color{
        RED, BLACK
    }
    public boolean find(int value){
        TreeNode currentNode = head;
        while (currentNode != null){
            if(currentNode.value == value)return true;
            else if(value > currentNode.value){
                currentNode = currentNode.right;
            }
            else{
                currentNode = currentNode.left;
            }
        }
        return false;
    }
    public boolean add(int value){
        if(head != null){
            boolean result = addNode(head, value);
            head = rebalans(head);
            head.color = Color.BLACK;
            return result;
        } else {
            head = new TreeNode();
            head.color = Color.BLACK;
            head.value = value;
            return true;
        }
    }
    private boolean addNode(TreeNode node, int value){
        if (node.value == value) return false;
        else {
            if(node.value > value){
                if(node.left != null){
                    boolean result = addNode(node.left, value);
                    node.left = rebalans(node.left);
                    return result;
                } else {
                    node.left = new TreeNode();
                    node.left.color = Color.RED;
                    node.left.value = value;
                    return true;
                }
            } else {
                if(node.right != null){
                    boolean result = addNode(node.right, value);
                    node.right = rebalans(node.right);
                    return result;
                } else {
                    node.right = new TreeNode();
                    node.right.color = Color.RED;
                    node.right.value = value;
                    return true;
                }
            }
        }
    }
    private TreeNode rebalans(TreeNode node){
        TreeNode result = node;
        boolean needRebalans;
        do {
            needRebalans = false;
            if(result.right != null && result.right.color == Color.RED &&
                    (result.left == null || result.left.color == Color.BLACK)){
                needRebalans = true;
                result = rightChang(result);
            }
            if(result.left != null && result.left.color == Color.RED &&
                    (result.left.left != null && result.left.left.color == Color.RED)){
                needRebalans = true;
                result = leftChang(result);
            }
            if(result.left != null && result.left.color == Color.RED &&
                    (result.right != null && result.right.color == Color.RED)){
                needRebalans = true;
                colorChang(result);
            }
        }
        while (needRebalans);
        return result;
    }
    private TreeNode rightChang(TreeNode node){
        TreeNode right = node.right;
        TreeNode between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = Color.RED;
        return right;
    }
    private TreeNode leftChang(TreeNode node){
        TreeNode left = node.left;
        TreeNode between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = Color.RED;
        return left;
    }
    private void colorChang(TreeNode node){
        node.right.color = Color.BLACK;
        node.left.color = Color.BLACK;
        node.color = Color.RED;
    }
}

