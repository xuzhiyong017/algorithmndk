package com.sky.algorithmndk

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testStringToInteger(){
        System.out.println(Algorithm().myAtoi(""))
    }


    @Test
    fun testIsValid(){
        System.out.println(Algorithm().isValid("()[]{}"))
    }

   @Test
    fun testMaxSum(){
       var a = intArrayOf(-4,4,-3,3,-4,-1,8,-7,-7);
       var k = 3
        System.out.println(Algorithm().largestSumAfterKNegations(a,k))
    }

    @Test
    fun testPartitionLabels(){
        System.out.println(Algorithm().partitionLabels("vhaagbqkaq"))
    }

    @Test
    fun testMaxValue(){
        Algorithm().maxValue
    }

    @Test
    fun removeNthFromEnd(){
        System.out.println(Algorithm().removeNthFromEnd(ListNode(1),1));
    }

    @Test
    fun isValidKuohao(){
        System.out.println(Algorithm().isValidKuohao("))"))
        System.out.println(Algorithm().isValidKuohao("}"))
    }

    @Test
    fun removeElement(){
        System.out.println(Algorithm().removeElement(intArrayOf(0,1,2,2,3,0,4,2),2))
    }

    @Test
    fun combine(){
        System.out.println(Algorithm().combine(4,2))
    }

    @Test
    fun solveNQueens(){
        System.out.println(Algorithm().solveNQueens(4))
    }

    @Test
    fun evalRPN(){
        System.out.println(Algorithm().evalRPN(arrayOf("4","13","5","/","+")))
    }

    @Test
    fun maxSlidingWindow(){

    }

    @Test
    fun intersection(){
        System.out.println(Algorithm().intersection(intArrayOf(1,2,2,3), intArrayOf(2,2)))
    }

     @Test
    fun change(){
        System.out.println(Algorithm().change(5,intArrayOf(1,2,5)))
    }

    @Test
    fun restoreIpAddresses(){
        System.out.println(Algorithm().restoreIpAddresses("25525511135"))
        System.out.println(Algorithm().restoreIpAddresses("0000"))
        System.out.println(Algorithm().restoreIpAddresses("1111"))
        System.out.println(Algorithm().restoreIpAddresses("010010"))
        System.out.println(Algorithm().restoreIpAddresses("101023"))
    }

    @Test
    fun removeList(){
        Algorithm().test()
    }

}
