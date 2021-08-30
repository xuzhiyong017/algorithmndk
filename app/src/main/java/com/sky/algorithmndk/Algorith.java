package com.sky.algorithmndk;

import android.os.Build;
import android.util.SparseArray;
import android.util.SparseIntArray;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * @author: xuzhiyong
 * @date: 2021/8/20  上午11:18
 * @Email: 18971269648@163.com
 * @description:
 */
public class Algorith {
    public static void main(String[] args) {
        new Algorith().GetLeastNumbers_Solution(new int[]{4,5,1,6,2,7,3,8},2);
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (input == null || k <= 0 || k > input.length) {
            return res;
        }
        int[] nums = new int[k];
        for(int j = 0;j < k;j++){
            nums[j] = input[j];
        }
        for(int i = k / 2 - 1;i >=0; i--){
            adjustHeap(nums,i,k);
        }

        for(int j = k;j < input.length;j++){
            if(input[j] < nums[0]){
                nums[0] = input[j];
                adjustHeap(nums,0,k);
            }
        }
        return (ArrayList<Integer>) Arrays.stream(nums).boxed().collect(Collectors.toList());
    }

    public void adjustHeap(int[] nums,int i,int len){
        while(2*i + 1 < len ){
            int max = 2 * i + 1;
            if(max + 1 < len && nums[max] < nums[max + 1]){
                max = max + 1;
            }

            if(nums[i] > nums[max]){
                break;
            }
            int tem = nums[i];
            nums[i] = nums[max];
            nums[max] = tem;
            i = max;
        }
    }

    public int[][] threeOrders(TreeNode root) {
        // write code here
        ArrayList<Integer> preList = new ArrayList<>();
        preNode(root,preList);
        ArrayList<Integer> inList = new ArrayList<>();
        inNode(root,inList);
        ArrayList<Integer> afterList = new ArrayList<>();
        afterNode(root,afterList);
        ArrayList result = new ArrayList();
        result.add(preList);
        result.add(inList);
        result.add(afterList);
        return new int[][]{
                preList.stream().mapToInt(Integer::intValue).toArray(),
                inList.stream().mapToInt(Integer::intValue).toArray(),
                afterList.stream().mapToInt(Integer::intValue).toArray(),
        };
    }

    private void preNode(TreeNode root,ArrayList<Integer> integers) {
        if(root != null){
            integers.add(root.getVal());
            if(root.getLeft() != null){
                preNode(root.getLeft(),integers);
            }

            if(root.getRight() != null){
                preNode(root.getRight(),integers);
            }
        }
    }
    private void inNode(TreeNode root,ArrayList<Integer> integers) {
        if(root != null){

            if(root.getLeft() != null){
                preNode(root.getLeft(),integers);
            }

            integers.add(root.getVal());

            if(root.getRight() != null){
                preNode(root.getRight(),integers);
            }
        }
    }

    private void afterNode(TreeNode root,ArrayList<Integer> integers) {
        if(root != null){

            if(root.getLeft() != null){
                preNode(root.getLeft(),integers);
            }

            if(root.getRight() != null){
                preNode(root.getRight(),integers);
            }

            integers.add(root.getVal());
        }
    }

    void printTreeDfs(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            System.out.println(node.getVal());
            if (node.getRight() != null) {
                node.getRight().setVal(node.getRight().getVal() + node.getVal());
                stack.push(node.getRight());
            }
            if (node.getLeft() != null) {
                node.getLeft().setVal(node.getLeft().getVal() + node.getVal());
                stack.push(node.getLeft());
            }
        }
    }

    public long maxWater (int[] arr) {
        // write code here
        int[] leftMaxArray = new int[arr.length];
        int[] rightMaxArray = new int[arr.length];
        int max  = 0;

        for(int i = 0 ; i< arr.length ;i++){
            max = Math.max(max,arr[i]);
            leftMaxArray[i] = max;
        }

        max = 0;
        for(int i =arr.length -1; i >= 0 ;i--){
            max = Math.max(max,arr[i]);
            rightMaxArray[i] = max;
        }

        int res = 0;
        for (int i = 0; i < arr.length; i++) {
            res += (Math.min(leftMaxArray[i],rightMaxArray[i]) - arr[i]);
        }

        System.out.println("");
        return 0;
    }

    public ListNode reverseKGroup (ListNode head, int k) {
        ListNode dumpHead = new ListNode(-1);
        dumpHead.setNext(head);
        ListNode prev = dumpHead;
        while (head != null){
            ListNode tail = prev;
            for (int i = 0; i < k; i++) {
                tail = tail.getNext();
                if(tail == null){
                    return dumpHead.getNext();
                }
            }
            ListNode nexNode = tail.getNext();
            ListNode[] groupList = reverseListNode(head,tail);
            head = groupList[0];
            tail = groupList[1];
            prev.setNext(head);
            tail.setNext(nexNode);
            prev = tail;
            head = tail.getNext();
        }
        return dumpHead.getNext();

    }

    private ListNode[] reverseListNode(ListNode head, ListNode tail) {
        ListNode prev = tail.getNext();
        ListNode cur = head;
        while (prev != tail){
            ListNode node = cur.getNext();
            cur.setNext(prev);
            prev = cur;
            cur = node;
        }

        return new ListNode[]{tail,head};
    }

    public int getMaxSequenceLength(int[] arr){
        int[] dp = new int[arr.length + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = 1;
        }
        //arr[i] >= arr[j] dp[i] = max(dp[i],dp[j] + 1)l
        int result = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
                if(dp[i] > result) result = dp[i];
            }
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void test(){
        int[] res = new int[2];
        Collections.sort(new ArrayList<String>(), (o1, o2) -> 0);
        Arrays.stream(res).boxed().toArray(Integer[]::new);
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        int[] res = new int[num.length - size + 1];
        int right = 0;
        int index = 0;
        LinkedList<Integer> list = new LinkedList<Integer>();
        while(right < num.length){
            while(!list.isEmpty() && list.peekLast() == num[right]) {
                list.removeLast();
            }
            list.addLast(num[right]);
            right++;
            if(right >= size){
                res[index++] = list.getFirst();
                if(list.getFirst() == num[right - size]){
                    list.removeFirst();
                }
            }
        }
        return (ArrayList<Integer>) Arrays.stream(res).boxed().collect(Collectors.toList());
    }

    public void testBreadThFirstSearch(){
        int vertexSize = 9;
        int[] vertexes = new int[9];
        for (int i = 0; i < vertexSize; i++) {
            vertexes[i] = i;
        }

        int[][] matrix = new int[vertexSize][vertexSize];

        //顶点0
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][3] = 0;
        matrix[0][4] = 0;
        matrix[0][5] = 0;
        matrix[0][6] = 0;
        matrix[0][7] = 0;
        matrix[0][8] = 0;

        //顶点1
        matrix[1][0] = 1;
        matrix[1][1] = 0;
        matrix[1][2] = 1;
        matrix[1][3] = 1;
        matrix[1][4] = 1;
        matrix[1][5] = 0;
        matrix[1][6] = 0;
        matrix[1][7] = 0;
        matrix[1][8] = 0;

        //顶点2
        matrix[2][0] = 1;
        matrix[2][1] = 1;
        matrix[2][2] = 0;
        matrix[2][3] = 0;
        matrix[2][4] = 1;
        matrix[2][5] = 1;
        matrix[2][6] = 0;
        matrix[2][7] = 0;
        matrix[2][8] = 0;

        //顶点3
        matrix[3][0] = 0;
        matrix[3][1] = 1;
        matrix[3][2] = 0;
        matrix[3][3] = 0;
        matrix[3][4] = 1;
        matrix[3][5] = 0;
        matrix[3][6] = 1;
        matrix[3][7] = 0;
        matrix[3][8] = 0;

        //顶点4
        matrix[4][0] = 0;
        matrix[4][1] = 1;
        matrix[4][2] = 1;
        matrix[4][3] = 1;
        matrix[4][4] = 0;
        matrix[4][5] = 1;
        matrix[4][6] = 1;
        matrix[4][7] = 1;
        matrix[4][8] = 0;

        //顶点5
        matrix[5][0] = 0;
        matrix[5][1] = 0;
        matrix[5][2] = 1;
        matrix[5][3] = 0;
        matrix[5][4] = 1;
        matrix[5][5] = 0;
        matrix[5][6] = 0;
        matrix[5][7] = 1;
        matrix[5][8] = 0;

        //顶点6
        matrix[6][0] = 0;
        matrix[6][1] = 0;
        matrix[6][2] = 0;
        matrix[6][3] = 1;
        matrix[6][4] = 1;
        matrix[6][5] = 0;
        matrix[6][6] = 0;
        matrix[6][7] = 1;
        matrix[6][8] = 1;

        //顶点7
        matrix[7][0] = 0;
        matrix[7][1] = 0;
        matrix[7][2] = 0;
        matrix[7][3] = 0;
        matrix[7][4] = 1;
        matrix[7][5] = 1;
        matrix[7][6] = 1;
        matrix[7][7] = 0;
        matrix[7][8] = 1;

        //顶点7
        matrix[8][0] = 0;
        matrix[8][1] = 0;
        matrix[8][2] = 0;
        matrix[8][3] = 0;
        matrix[8][4] = 0;
        matrix[8][5] = 0;
        matrix[8][6] = 1;
        matrix[8][7] = 1;
        matrix[8][8] = 0;

        Graph graph = new Graph(vertexSize,vertexes,matrix);
        graph.breadthFirstSearch();
    }

    public void testPrimMinTree(){
        int vertexSize = 9;
        int[] vertexes = new int[9];
        for (int i = 0; i < vertexSize; i++) {
            vertexes[i] = i;
        }

        int[][] matrix = new int[vertexSize][vertexSize];

        //顶点0
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 5;
        matrix[0][3] = Graph.MAX_WEIGHT;
        matrix[0][4] = Graph.MAX_WEIGHT;
        matrix[0][5] = Graph.MAX_WEIGHT;
        matrix[0][6] = Graph.MAX_WEIGHT;
        matrix[0][7] = Graph.MAX_WEIGHT;
        matrix[0][8] = Graph.MAX_WEIGHT;

        //顶点1
        matrix[1][0] = 1;
        matrix[1][1] = 0;
        matrix[1][2] = 3;
        matrix[1][3] = 7;
        matrix[1][4] = 5;
        matrix[1][5] = Graph.MAX_WEIGHT;
        matrix[1][6] = Graph.MAX_WEIGHT;
        matrix[1][7] = Graph.MAX_WEIGHT;
        matrix[1][8] = Graph.MAX_WEIGHT;

        //顶点2
        matrix[2][0] = 5;
        matrix[2][1] = 3;
        matrix[2][2] = 0;
        matrix[2][3] = Graph.MAX_WEIGHT;
        matrix[2][4] = 1;
        matrix[2][5] = 7;
        matrix[2][6] = Graph.MAX_WEIGHT;
        matrix[2][7] = Graph.MAX_WEIGHT;
        matrix[2][8] = Graph.MAX_WEIGHT;

        //顶点3
        matrix[3][0] = Graph.MAX_WEIGHT;
        matrix[3][1] = 7;
        matrix[3][2] = Graph.MAX_WEIGHT;
        matrix[3][3] = 0;
        matrix[3][4] = 2;
        matrix[3][5] = Graph.MAX_WEIGHT;
        matrix[3][6] = 1;
        matrix[3][7] = Graph.MAX_WEIGHT;
        matrix[3][8] = Graph.MAX_WEIGHT;

        //顶点4
        matrix[4][0] = Graph.MAX_WEIGHT;
        matrix[4][1] = 5;
        matrix[4][2] = 1;
        matrix[4][3] = 2;
        matrix[4][4] = 0;
        matrix[4][5] = 3;
        matrix[4][6] = 6;
        matrix[4][7] = 9;
        matrix[4][8] = Graph.MAX_WEIGHT;

        //顶点5
        matrix[5][0] = Graph.MAX_WEIGHT;
        matrix[5][1] = Graph.MAX_WEIGHT;
        matrix[5][2] = 7;
        matrix[5][3] = Graph.MAX_WEIGHT;
        matrix[5][4] = 3;
        matrix[5][5] = 0;
        matrix[5][6] = Graph.MAX_WEIGHT;
        matrix[5][7] = 5;
        matrix[5][8] = Graph.MAX_WEIGHT;

        //顶点6
        matrix[6][0] = Graph.MAX_WEIGHT;
        matrix[6][1] = Graph.MAX_WEIGHT;
        matrix[6][2] = Graph.MAX_WEIGHT;
        matrix[6][3] = 1;
        matrix[6][4] = 6;
        matrix[6][5] = Graph.MAX_WEIGHT;
        matrix[6][6] = 0;
        matrix[6][7] = 2;
        matrix[6][8] = 7;

        //顶点7
        matrix[7][0] = Graph.MAX_WEIGHT;
        matrix[7][1] = Graph.MAX_WEIGHT;
        matrix[7][2] = Graph.MAX_WEIGHT;
        matrix[7][3] = Graph.MAX_WEIGHT;
        matrix[7][4] = 9;
        matrix[7][5] = 5;
        matrix[7][6] = 2;
        matrix[7][7] = 0;
        matrix[7][8] = 4;

        //顶点7
        matrix[8][0] = Graph.MAX_WEIGHT;
        matrix[8][1] = Graph.MAX_WEIGHT;
        matrix[8][2] = Graph.MAX_WEIGHT;
        matrix[8][3] = Graph.MAX_WEIGHT;
        matrix[8][4] = Graph.MAX_WEIGHT;
        matrix[8][5] = Graph.MAX_WEIGHT;
        matrix[8][6] = 7;
        matrix[8][7] = 4;
        matrix[8][8] = 0;

        Graph graph = new Graph(vertexSize,vertexes,matrix);
//        graph.breadthFirstSearch();

        //普利姆算法
        graph.primMinTree();
        //迪杰思特拉算法
//        graph.djstlaMinTree();
    }
}
