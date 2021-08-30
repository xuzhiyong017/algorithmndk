package com.sky.algorithmndk

import java.util.*

/**
 * @author: xuzhiyong
 * @date: 2021/8/12  上午9:12
 * @Email: 18971269648@163.com
 * @description:
 */

fun main() {
//    println(TestKotlin().solve("((10+2)*10-(100-(10+20*10-(2*3)))*10*1*2)-2"))
//    Algorith().GetLeastNumbers_Solution(intArrayOf(4, 5, 1, 6, 2, 7, 3, 8), 2)


    val rootNode = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4).apply {
                left = TreeNode(8)
                right = TreeNode(9)
            }
            right = TreeNode(5)
        }
        right = TreeNode(3).apply {
            left = TreeNode(6)
            right = TreeNode(7)
        }
    }

    val listNode = ListNode(1).apply {
        next = ListNode(2).apply {
            next = ListNode(3).apply {
                next = ListNode(4).apply {
                    next = ListNode(5)
                }
            }
        }
    }

//    Algorith().threeOrders(rootNode)

//    Algorith().printTreeDfs(rootNode)

//    Algorith().maxWater(intArrayOf(4,5,1,3,2))

//    Algorith().reverseKGroup(listNode,3)
//    Algorith().testBreadThFirstSearch()
    Algorith().testPrimMinTree()


}

class TestKotlin {



    suspend fun test(){
        val test = test1()
        val i =  test2()
        println(test + i)
    }

    suspend fun test1():String {
        println("test1")
        return ""
    }

    suspend fun test2():Int{
        println("test2")
        return 1
    }

    fun solve(s:String):Int{

        val map: Map<Char, Int> = object : HashMap<Char, Int>() {
            init {
                put('-', 1)
                put('+', 1)
                put('*', 2)
            }
        }

        val array = s.toCharArray();
        val nums = Stack<Int>()
        val opts = Stack<Char>()
        var index = 0
        while(index < array.size){
            if(array[index] == ' '){
                index++
                continue
            }else if(array[index] == '('){
                opts.push(array[index])
                index++
            }else if(isNums(array[index])){
                var j = index
                var num = 0;
                while (j < array.size && isNums(array[j])){
                    num = num * 10 + (array[j] - '0')
                    j++;
                }
                nums.push(num)
                index = j
            }else if(array[index] == ')'){
                index++
                while (!opts.isEmpty()){
                    if(opts.peek() != '('){
                        cal(nums,opts)
                    }else{
                        opts.pop()
                        break
                    }
                }
            }else{
                // 有一个新操作要入栈时，先把栈内可以算的都算了
                // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                while (!opts.isEmpty() && opts.peek() != '(') {
                    val prev: Char = opts.peek()
                    if (map[prev]!! >= map[array[index]]!!) {
                        cal(nums, opts)
                    } else {
                        break
                    }
                }
                opts.push(array[index++])
            }
        }
        while (!opts.isEmpty()){
            cal(nums,opts)
        }
        return nums.pop();
    }

    private fun cal(nums: Stack<Int>, ops: Stack<Char>) {
        if (nums.isEmpty() || nums.size < 2) return
        if (ops.isEmpty()) return
        val b: Int = nums.pop()
        val a: Int = nums.pop()
        val op: Char = ops.pop()
        var ans = 0
        if (op == '+') ans = a + b else if (op == '-') ans = a - b else if (op == '*') ans = a * b
        nums.push(ans)
    }

    private fun isNums(c: Char): Boolean {
        return c in '0'..'9'
    }
}