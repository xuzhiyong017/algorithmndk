package com.sky.algorithmndk

import java.util.*
import kotlin.math.max
import kotlin.math.min

/**
 * @author: xuzhiyong
 * @date: 2021/8/19  下午4:48
 * @Email: 18971269648@163.com
 * @description:
 */

class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
 }
class ListNode(open var `val`: Int) {
        open var next: ListNode? = null
 }

fun main() {

}

object Solution {

    fun maxProfit(prices: IntArray): Int  {
        // write code here
        var minValue = prices[0];
        var maxValue = prices[0]
        for(i in prices.indices){
            minValue = min(minValue,prices[i])
            maxValue = max(prices[i] -  minValue,maxValue);
        }
        return maxValue
    }

    fun addInList(head1: ListNode?,head2: ListNode?): ListNode?  {
        // write code here
        val stack1 = Stack<Int>()
        val stack2 = Stack<Int>()
        var node1 = head1
        var node2 = head2
        while (node1 != null){
            stack1.push(node1.`val`)
            node1 = node1.next
        }

        while (node2 != null){
            stack2.push(node2.`val`)
            node2 = node2.next
        }

        var tem = 0;
        var newNode = ListNode(-1)
        while (!stack1.isEmpty() || !stack2.isEmpty()){
            var res = tem;

            if(!stack1.isEmpty()){
                res += stack1.pop()
            }

            if(!stack2.isEmpty()){
                res += stack2.pop()
            }

            tem = res / 10
            val temNode = ListNode(res % 10)
            temNode.next = newNode
            newNode = temNode
        }

        if(tem > 0){
            val temNode = ListNode(tem)
            temNode.next = newNode
            newNode = temNode
        }
        return newNode
    }


    @ExperimentalStdlibApi
    fun pathSum(root: TreeNode?, sum: Int): Array<IntArray>  {
        // write code here
        val result = mutableListOf<IntArray>()
        dfs(root,sum, mutableListOf<Int>(),result)
        return result.toTypedArray()
    }

    @ExperimentalStdlibApi
    private fun dfs(
        root: TreeNode?,
        sum: Int,
        mutableListOf: MutableList<Int>,
        result: MutableList<IntArray>
    ) {
        if(root == null) return
        mutableListOf.add(root.`val`)
        if(root.left == null && root.right == null){
            if(sum == root.`val`){
                result.add(mutableListOf.toIntArray())
            }
            mutableListOf.removeLast()
            return
        }

        dfs(root.left,sum - root.`val`,mutableListOf,result)
        dfs(root.right,sum - root.`val`,mutableListOf,result)
        mutableListOf.removeLast()
    }

}