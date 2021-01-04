package com.sky.algorithmndk;

import android.util.Log;

import java.util.ArrayDeque;

/**
 * @author: xuzhiyong
 * @date: 21-1-4  下午3:30
 * @Email: 18971269648@163.com
 * @description: 图.png，3到6的权值改为1
 *
 *                                         3
 *                                               +
 *                             1                        6
 *                                                           +
 *                    0                     4                     8
 *
 *                              2                       7
 *
 *                                          5
 *
 *
 */
public class Graph {
    //顶点个数
    int vertexSize;
    //顶点的集合
    int[] vertexes;
    //边的集合
    int[][] matrix;

    public Graph(int vertexSize, int[] vertexes, int[][] matrix) {
        this.vertexSize = vertexSize;
        this.vertexes = vertexes;
        this.matrix = matrix;
    }

    public void breachSearch() {
        //记录是否访问过
        boolean[] visited = new boolean[vertexSize];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        //从节点3开始广度遍历
        deque.push(vertexes[3]);
        while (!deque.isEmpty()){
            //访问节点
            int element = deque.removeFirst();
            //这里节点值跟数组下标一样就不用查找了
            int index = element;
            if(!visited[index]){
                Log.d("Graph",element+"");
                visited[index] = true;

                for (int i = 0; i < vertexSize; i++) {
                    if(matrix[index][i] == 1 && !visited[i]){
                        deque.addLast(vertexes[i]);
                    }
                }

            }
        }

    }
}
