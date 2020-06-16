import java.util.LinkedList;
import java.util.Queue;

public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        while (queue.isEmpty() != true) {
            TreeNode tmp = queue.poll();
            if (tmp != null) {
                stringBuilder.append(tmp.val).append(",");
                queue.offer(tmp.left);
                queue.offer(tmp.right);
            } else {
                stringBuilder.append("null,");
            }

        }


        return stringBuilder.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {


        Queue<TreeNode> queue = new LinkedList<TreeNode>();

        String[] arrs = data.split(",");

        int index = 1;
        if (arrs[0].equals("null") || data.length() == 0) {
            return null;
        }
        TreeNode head = new TreeNode(Integer.valueOf(arrs[0]));

        queue.offer(head);
        while (queue.isEmpty() != true && index < (arrs.length - 1)) {
            boolean flag = false;
            TreeNode tmp = queue.poll();
            if (tmp == null) {

            } else {
                if (arrs[index].equals("null")) {
                    tmp.left = null;
                    queue.offer(null);
                } else {
                    tmp.left = new TreeNode(Integer.valueOf(arrs[index]));
                    queue.offer(tmp.left);
                }
                index++;
                if (arrs[index].equals("null")) {
                    tmp.right = null;
                    queue.offer(null);
                } else {
                    tmp.right = new TreeNode(Integer.valueOf(arrs[index]));
                    queue.offer(tmp.right);
                }
                index++;
            }
        }


        return head;
    }


}