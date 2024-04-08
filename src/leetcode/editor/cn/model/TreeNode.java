package leetcode.editor.cn.model;

/**
 * @author Yolen
 * @date 2024/2/7
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 根据层序遍历的结果数组，构建二叉树
     *
     * @param values 层序遍历的结果数组
     * @param i      开始索引
     * @param n      结束索引
     * @return 二叉树
     */
    public static TreeNode buildTree(Integer[] values, int i, int n) {
        if (i >= n || values[i] == null) {
            return null;
        }
        // 首节点是根节点
        TreeNode root = new TreeNode(values[i]);
        // 然后接下来每次取出两个节点，依次作为根节点的子节点
        root.left = buildTree(values, 2 * i + 1, n);
        root.right = buildTree(values, 2 * i + 2, n);

        return root;
    }

    public static void main(String[] args) {
        Integer[] values = new Integer[] {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1};
        TreeNode root = buildTree(values, 0, values.length);
        System.out.println(root.val);
        System.out.println(root.left.val);
        System.out.println(root.right.val);
    }
}
