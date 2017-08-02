/* BST Iterator */
/*
 * Implement an iterator over a binary search tree (BST). 
 * Your iterator will be initialized with the root node of a BST.
 *
 * The first call to next() will return the smallest number in BST. 
 * Calling next() again will return the next smallest number in the BST, 
 * and so on.
 */

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class Solution {

    Stack<TreeNode> parents;

    public Solution(TreeNode root) {
        parents = new Stack<>();
        pushAllLeft(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !parents.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode current = parents.pop();

        pushAllLeft(current.right);

        return current.val;
    }

    private void pushAllLeft(TreeNode node) {
        while (node != null) {
            parents.push(node);
            node = node.left;
        }
    }
}

/**
 *  * Your Solution will be called like this:
 *   * Solution i = new Solution(root);
 *    * while (i.hasNext()) System.out.print(i.next());
 *     */

