//给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家
//只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。 
//
// 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。 
//
// 
//
// 示例 1： 
//
// 输入：[1, 5, 2]
//输出：False
//解释：一开始，玩家1可以从1和2中进行选择。
//如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）
//可选。
//所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
//因此，玩家 1 永远不会成为赢家，返回 False 。
// 
//
// 示例 2： 
//
// 输入：[1, 5, 233, 7]
//输出：True
//解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
//     最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= 给定的数组长度 <= 20. 
// 数组里所有分数都为非负数且不会大于 10000000 。 
// 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。 
// 
// Related Topics 极小化极大 动态规划 
// 👍 206 👎 0

package leetcode.editor.cn;

public class PredictTheWinner {
    public static void main(String[] args) {
        Solution solution = new PredictTheWinner().new Solution();
        int arr[] = {3606449, 6, 5, 9, 452429, 7, 9580316, 9857582, 8514433, 9, 6, 6614512, 753594, 5474165, 4, 2697293, 8, 7, 1};
        int[] arr1 = {2, 1, 5};
        System.out.println(solution.PredictTheWinner(arr));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean PredictTheWinner(int[] nums) {
            int len = nums.length;
            int[][] dp = new int[len][len];

            // dp[i][j]：作为先手，在区间 nums[i..j] 里进行选择可以获得的相对分数
            for (int i = 0; i < len; i++) {
                dp[i][i] = nums[i];
            }

            for (int j = 1; j < len; j++) {
                for (int i = j - 1; i >= 0; i--) {
                    dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
                }
            }
            return dp[0][len - 1] >= 0;
        }


        /*public boolean PredictTheWinner(int[] nums) {
            int left = 0;
            int right = nums.length - 1;
            boolean who = true;
            int sum1 = 0;
            int sum2 = 0;

            return recursive(nums, left, right, sum1, sum2, who);
        }*/

        public boolean recursive(int[] nums, int left, int right, int sum1, int sum2, boolean who) {
     //       if (nums.length % 2 == 0) return true;

            if (left == right) {
                if (who) {
                    sum1 = sum1 + nums[left];

                } else {
                    sum2 = sum2 + nums[right];
                }
                return sum1 >= sum2;
            }
            boolean flag = false;

            int comLeft=Math.min(nums[left] - nums[left + 1], nums[left] - nums[right]); ;
           /* if((nums[left] - nums[left + 1]+ nums[left] - nums[right])>=0){
                comLeft=Math.min(nums[left] - nums[left + 1], nums[left] - nums[right]);
            }else {
                comLeft=Math.min(nums[left] - nums[left + 1], nums[left] - nums[right]);
            }*/
            int comRight=Math.min(nums[right] - nums[right - 1], nums[right] - nums[left]);;
          /*  if((nums[right] - nums[right - 1]+ nums[right] - nums[left])>=0){
                comRight=Math.min(nums[right] - nums[right - 1], nums[right] - nums[left]);
            }else {
                comRight=Math.min(nums[right] - nums[right - 1], nums[right] - nums[left]);
            }
*/



            if (comLeft > comRight) {
                flag = who ? recursive(nums, left + 1, right, sum1 + nums[left], sum2, !who) : recursive(nums, left + 1, right, sum1, sum2 + nums[left], !who);
            } else if (comLeft == comRight) {
                if (nums[left] > nums[right]) {
                    flag = who ? recursive(nums, left+1, right, sum1 + nums[left], sum2, !who) : recursive(nums, left+1, right , sum1, sum2 + nums[left], !who);
                } else {
                    flag = who ? recursive(nums, left , right-1, sum1 + nums[right], sum2, !who) : recursive(nums, left, right-1, sum1, sum2 + nums[right], !who);
                }

            } else {
                flag = who?recursive(nums, left, right - 1, sum1 + nums[right], sum2, !who):recursive(nums, left, right - 1, sum1 , sum2+ nums[right], !who);
            }


            return flag;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
