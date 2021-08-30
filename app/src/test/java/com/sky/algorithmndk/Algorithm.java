package com.sky.algorithmndk;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import kotlin.collections.ArrayDeque;

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

    void backtrack(String digits,int depth,StringBuilder result,List<String> res,String[] letterMap){
        if(depth == digits.length()){
            res.add(result.toString());
            return;
        }
        int index = digits.charAt(depth) - '0';
        String letters = letterMap[index];
        for(int i = 0;i < letters.length();i++){
            result.append(letters.charAt(i));
            backtrack(digits,depth+1,result,res,letterMap);
            result.deleteCharAt(result.length() -1);
        }
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head == null){
            return null;
        }
        ListNode fakeNode = new ListNode(-1);
        fakeNode.next = head;
        ListNode slow = fakeNode;
        ListNode fast = fakeNode;
        while(fast != null && (n-- > 0)){
            fast = fast.next;
        }
        if(fast != null) fast =fast.next;
        while(fast!= null){
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return fakeNode.next;
    }

    public boolean isValidKuohao(String s) {
        if(s == null || s.length() == 0){
            return false;
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '{'){
                stack.push('}');
            }else if(chars[i] == '('){
                stack.push(')');
            }else if(chars[i] == '['){
                stack.push(']');
            }else if(stack.isEmpty() || stack.peek() != chars[i]){
                return false;
            }else {
                stack.pop();
            }
        }
        return stack.empty();
    }

    public int removeElement(int[] nums, int val) {
        if(nums == null){
            return 0;
        }

        int len = nums.length;
        for(int i = 0; i < len; i++){
            if(nums[i] == val){

                for(int j = i+1 ;j < len; j++){
                    nums[j-1] = nums[j];
                }
                len--;
                i--;
            }

        }
        return len;

        //// 快慢指针
        // int slow = 0;
//        for(int i = 0; i < nums.length; i++){
//            if(val != nums[i]){
//                nums[slow++] = nums[i];
//            }
//
//        }
//        return slow;
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subRes = new ArrayList<>();
        backtrack(n,1,k,subRes,res);
        return res;
    }

    public void backtrack(int n,int depth,int k,List<Integer> subRes,List<List<Integer>> res){
        if(subRes.size() == k){
            res.add(new ArrayList(subRes));
            return;
        }

        for(int i = depth ;i <= n ;i++){
            subRes.add(i);
            backtrack(n,depth+1,k,subRes,res);
            subRes.remove(subRes.size()-1);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        int[] record = new int[n];
        int[] record1 = new int[n];
        System.out.println("ongsh "+ process1(0,record1,n));
        List<int[]> records = new ArrayList<>();
        process(0,record,n,records);
        for (int i = 0; i < records.size(); i++) {
            System.out.println("queue2 = "+ Arrays.toString(records.get(i)));
        }

        return getQueueList(records);
    }

    private List<List<String>> getQueueList(List<int[]> records) {
        List<List<String>> res = new ArrayList<>();
        if(records == null || records.size() == 0){
            return  res;
        }

        for (int i = 0; i < records.size(); i++) {
            int[] queueTable = records.get(i);
            List<String> listString = new ArrayList<>();
            for (int k = 0; k < queueTable.length; k++) {
                StringBuilder builder = new StringBuilder();
                for (int j = 0; j < queueTable.length; j++) {
                    if(queueTable[k] == j){
                        builder.append("Q");
                    }else{
                        builder.append(".");
                    }
                }
                listString.add(builder.toString());
            }
            res.add(listString);
        }
        return res;
    }

    public void process(int index,int[] record,int n, List<int[]> records){
        if(index == n){
            int[] newRecord = new int[n];
            System.arraycopy(record,0,newRecord,0,n);
            records.add(newRecord);
            System.out.println("queue = "+ Arrays.toString(record));
            return ;
        }

        for(int j = 0 ;j < n ;j ++){
            if(isValid(record,index,j)){
                record[index] = j;
                process(index + 1,record,n,records);
            }
        }
    }

    public int process1(int index,int[] record,int n){
        if(index == n){
            return 1;
        }

        int res = 0;
        for(int j = 0 ;j < n ;j ++){
            if(isValid(record,index,j)){
                record[index] = j;
                res += process1(index + 1,record,n);
            }
        }

        return res;
    }

    public boolean isValid(int[] record,int i,int j){
        for (int k = 0; k < i; k++) {
            if(record[k] == j || Math.abs(k - i) == Math.abs(record[k] - j)){
                return false;
            }
        }

        return true;
    }


    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        char[] sourceChars = s.toCharArray();
        List<String> builder = new ArrayList<>();
        backtrack(s,0,0,builder,result);
        return result;
    }

    void backtrack(String source,int startIndex,int pointNums,List<String> builder,List<String> result){
        if(pointNums == 3){
            if(isValid(source,startIndex,source.length() -1)){
                builder.add(source.substring(startIndex,source.length()));
                result.add(getIpString(builder));
                builder.remove(builder.size() -1);
            }
            return;
        }

        for(int i = startIndex; i < source.length(); i++){
            if(isValid(source,startIndex,i)){
                String curStr = source.substring(startIndex,i + 1);
                builder.add(curStr);
                pointNums++;
                backtrack(source,i + 1,pointNums,builder,result);
                pointNums--;
                builder.remove(builder.size()-1);
            }else{
                break;
            }
        }
    }

    boolean isValid(String s,int start,int end){
        if(start > end){
            return false;
        }

        if(s.charAt(start) == '0' && start != end){
            return false;
        }

        int num = 0;
        for(int i = start;i <= end ; i++){
            if(s.charAt(i) > '9' || s.charAt(i) < '0'){
                return false;
            }

            num = num * 10 + (s.charAt(i) - '0');
            if(num > 255){
                return false;
            }
        }

        return true;
    }

    String getIpString(List<String> list){
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < list.size() ; i++){
            builder.append(list.get(i));
            builder.append('.');
        }

        if(builder.length() > 1){
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }

    public int evalRPN(String[] tokens) {
        Stack<Integer> queue = new Stack<>();
        for(int i = 0 ; i < tokens.length ; i++){
            char c = tokens[i].charAt(0);
            if(!isLogin(tokens[i])){
                queue.push(Integer.valueOf(tokens[i]));
            }else if( c == '+'){
                queue.push(queue.pop() + queue.pop());
            }else if( c == '-'){
                queue.push(-queue.pop() + queue.pop());
            }else if( c == '*'){
                queue.push(queue.pop() * queue.pop());
            }else if( c == '/'){
                int num1 = queue.pop();
                int num2 = queue.pop();
                queue.push(num2 / num1);
            }
        }

        return queue.pop();
    }

    private boolean isLogin(String s){
        return s.length() == 1 && s.charAt(0) < '0' || s.charAt(0) > '9';
    }

    public boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while(true){
            int sum = getSum(n);
            if(sum == 1){
                return true;
            }

            if(set.contains(sum)){
                return false;
            }else{
                set.add(sum);
            }
            n = sum;
        }
    }

    public int getSum(int n){
        int result = 0;
        while( n > 0){
            result += ( n % 10) * ( n % 10);
            n /= 10;
        }

        return result;
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> map = new HashSet<>();
        for(int i = 0;i < nums1.length; i++){
            map.add(nums1[i]);
        }
        HashSet<Integer> resultSet = new HashSet<>();
        for(int j = 0; j < nums2.length; j++){
            if(map.contains(nums2[j])){
                resultSet.add(nums2[j]);
            }
        }

        int[] result = new int[resultSet.size()];
        int index = 0;
        for(Integer num: resultSet){
            result[index++] = num;
        }
        return result;
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals,new Comparator<int[]>(){
            public int compare(int[] p1,int[] p2){
                return p1[1] - p2[1];
            }
        });

        int count = 0;
        int end = intervals[0][1];
        for(int i = 0; i < intervals.length ; i++ ){
            if(end <= intervals[i][0]){
                count++;
                end = intervals[i][1];
            }
        }

        return intervals.length - count;
    }

    //change money

    /**
     *
     *   [1,2,5]  5

         [1, 1, 1, 1, 1, 1]
         [1, 1, 2, 2, 3, 3]
         [1, 1, 2, 2, 3, 4]
     *
     *
     */
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for(int i = 0; i < coins.length ; i++){
            for(int j = coins[i] ;j <= amount ; j++){
                dp[j] += dp[j-coins[i]];
            }
            System.out.println(Arrays.toString(dp));
        }
//        for(int j = 0 ;j <= amount ; j++){
//            for(int i = 0; i < coins.length ; i++){
//                if(j >= coins[i]){
//                    dp[j] += dp[j-coins[i]];
//                }
//            }
//            System.out.println(Arrays.toString(dp));
//        }
        return dp[amount];
    }

  public void test(){
      List<String> list = new ArrayList<>();
      list.add("w");
      list.add("abc");
      list.add("abd");
      list.add("abc");
      list.add("aba");
      list.add("wbbb");
      Iterator it = list.iterator();
      for (String str:list) {
          list.remove(str);
      }
    }
}