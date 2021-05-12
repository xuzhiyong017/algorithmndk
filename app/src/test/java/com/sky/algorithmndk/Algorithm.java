package com.sky.algorithmndk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * @author: xuzhiyong
 * @date: 21-1-5  下午3:48
 * @Email: 18971269648@163.com
 * @description:
 */
public class Algorithm {

    public int myAtoi(String s) {
        int index = 0;
        int len = s.length();
        long res = 0;

        if(index == len){
            return 0;
        }

        while( index < len && s.charAt(index) == ' '){
            index++;
        }

        if(index == len){
            return 0;
        }
        int negative = 1;

        if(s.charAt(index) == '-'){
            index++;
            negative = -1;
        }else if(s.charAt(index) == '+'){
            index++;
        }

        if(index == len){
            return 0;
        }

        while( index < len){
            char c = s.charAt(index++);
            if(c < '0' || c > '9'){
                break;
            }
            res = res * 10 + (c - '0');

            if(res* negative > Integer.MAX_VALUE){
                return Integer.MAX_VALUE;
            }else if(res * negative < Integer.MIN_VALUE){
                return Integer.MIN_VALUE;
            }
        }
        return (int)res*negative;
    }

    public boolean isValid(String s) {
        Stack<Character> path = new Stack<>();
        for(int i = 0 ;i < s.length();i++){
            if(s.charAt(i) == '{'){
                path.push('}');
            }else if(s.charAt(i) == '['){
                path.push(']');
            }else if(s.charAt(i) == '('){
                path.push(')');
            }else if(path.isEmpty() || ((char) path.peek()) != s.charAt(i)){
                return false;
            }else{
                path.pop();
            }
        }
        return path.isEmpty();

    }

    public int largestSumAfterKNegations(int[] A, int K) {

        for(int i= 0 ; i < A.length - 1; i++){
            for(int j= 0;j< A.length -i- 1;j++){
                if(Math.abs(A[j]) < Math.abs(A[j+1])){
                    int tem = A[j];
                    A[j] = A[j+1];
                    A[j+1] = tem;
                }
            }
        }

        for(int i =0 ;i < A.length;i++){
            if(K > 0 && A[i] < 0){
                A[i] *= -1;
                K--;
            }
        }

        while(K > 0) {
            A[A.length - 1] *= -1;
            K--;
        }
        int result = 0;
        for(int i =0;i < A.length; i++){
            result+= A[i];
        }
        return result;

    }


    public List<Integer> partitionLabels(String S) {
        int[] index = new int[27];
        for(int i = 0; i < S.length();i++){
            index[S.charAt(i) - 'a'] = i;
        }

        int right = 0;
        int left = 0;
        List<Integer> list  = new ArrayList<>();
        for(int i = 0; i < S.length();i++){
            right = Math.max(index[S.charAt(i) - 'a'],right);
            if(right == i){
                list.add(right -left +1);
                left = i + 1;
            }
        }

        return list;
    }

    public int getMaxValue(){
        int[] weight = {1,2,3,4};
        int[] value = {3,4,5,6};
        int bagWeight = 5;
        int[][] dp = new int[bagWeight + 1][weight.length];
        for (int i = 0; i < weight.length; i++) {
            dp[0][i] = 0;
        }
        for (int j = bagWeight ; j >=weight[0] ; j--) {
            dp[j][0] = dp[j-weight[0]][0] + value[0];
        }

        // i代表容量，J代表物品
        // dp[i][j] = MAX(dp[i-weight[j]][j] + value[j],dp[i][j-1])

       for (int i =0;i <= bagWeight; i++) {
            for (int j = 1; j < weight.length ; j++) {
                if(weight[j] > i){
                   dp[i][j] = dp[i][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-weight[j]][j-1]+value[j],dp[i][j-1]);
                }
            }
        }

        for (int i = 0; i <= bagWeight; i++) {
            for (int j = 0; j < weight.length; j++) {
                System.out.print(dp[i][j] +"   ");
            }
            System.out.println();
        }


        return 0;
    }
}
