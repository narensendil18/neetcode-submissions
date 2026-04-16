/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution 
{

    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) 
    {
        dfs(root);
        return max;
    }
        public int dfs(TreeNode curr)
        {
            if(curr == null)
            return 0;

            int left = dfs(curr.left);
            int right = dfs(curr.right);

            max = Math.max(max,left + right);
            return 1 + Math.max(left,right);
        }
}
