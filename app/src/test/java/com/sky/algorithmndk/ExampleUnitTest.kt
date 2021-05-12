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
}
