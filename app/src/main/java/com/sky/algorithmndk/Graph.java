package com.sky.algorithmndk;

import android.util.Log;

import java.util.ArrayDeque;
import java.util.Arrays;

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
    public final static int MAX_WEIGHT = 65536;
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

    public void breadthFirstSearch() {
        //记录是否访问过
        boolean[] visited = new boolean[vertexSize];
        int[] dis = new int[vertexSize];
        int[] path = new int[vertexSize];
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        //从节点3开始广度遍历
        deque.push(vertexes[3]);
        for (int i = 0; i < vertexSize; i++) {
            if(i != 3){
                dis[i] = path[i] = -1;
            }
        }
        while (!deque.isEmpty()){
            //访问节点
            int element = deque.removeFirst();
            //这里节点值跟数组下标一样就不用查找了
            int index = element;
            for (int i = 0; i < vertexSize; i++) {
                if(dis[i] == -1 && matrix[index][i] == 1){
                    dis[i] = dis[index] + 1;
                    path[i] = index;
                    deque.addLast(vertexes[i]);
                }
            }

//            if(!visited[index]){
//                System.out.println("Graph "+ element+"");
//                visited[index] = true;
//
//                for (int i = 0; i < vertexSize; i++) {
//                    if(matrix[index][i] == 1 && !visited[i]){
//                        deque.addLast(vertexes[i]);
//                    }
//                }
//
//            }
        }

        //dis该算法的本质是BFS，以源点为起始点不断向外“渗透”。
        System.out.println(Arrays.toString(path));
    }

    //普利姆算法

    /**
     * 普里姆算法（Prim算法），图论中的一种算法，可在加权连通图里搜索最小生成树。
     * 意即由此算法搜索到的边子集所构成的树中，不但包括了连通图里的所有顶点（英语：Vertex (graph theory)）
     * ，且其所有边的权值之和亦为最小。该算法于1930年由捷克数学家沃伊捷赫·亚尔尼克（英语：Vojtěch Jarník）
     * 发现；并在1957年由美国计算机科学家罗伯特·普里姆（英语：Robert C. Prim）独立发现；1959年，艾兹格·迪科斯彻再次发现了该算法。
     * 因此，在某些场合，普里姆算法又被称为DJP算法、亚尔尼克算法或普里姆－亚尔尼克算法。
     */
    public void primMinTree() {
        //定义一个数组，当前修好的村庄 lowcost = 0 代表路已经修了
        int[] lowcost = new int[vertexSize];

        //第一列数据 先放到lowcost
        for (int i = 0; i < vertexSize; i++) {
            lowcost[i] = matrix[0][i];
        }

        StringBuilder builder = new StringBuilder();
        for (int j = 0; j < vertexSize; j++) {
            builder.append(lowcost[j]).append(",");
        }
        System.out.println("primMinTree: 第一次的比较"+builder.toString());

        //
        int sum = 0;
        for (int i = 1; i < vertexSize; i++) {
            int min = MAX_WEIGHT;
            int minId = 0;

            for (int j = 1; j < vertexSize; j++) {
                if(lowcost[j] < min && lowcost[j] != 0){
                    min = lowcost[j];
                    minId = j;
                }
            }

            System.out.println("primMinTree: 找到村庄"+vertexes[minId]+",修路距离="+min);
            lowcost[minId] = 0;
            sum += min;

            for (int j = 0; j < vertexSize; j++) {
                if(matrix[minId][j] < lowcost[j] && lowcost[j] > 0){
                    lowcost[j] = matrix[minId][j];
                }
            }


            StringBuilder builder1 = new StringBuilder();
            for (int j = 0; j < vertexSize; j++) {
                builder1.append(lowcost[j]).append(",");
            }
            System.out.println("primMinTree: 每一次的比较"+builder1.toString());

        }
        System.out.println("primMinTree: 最短的路径是sum="+sum);
    }

    /**
     * 迪杰斯特拉算法(Dijkstra)是由荷兰计算机科学家狄克斯特拉于1959 年提出的，因此又叫狄克斯特拉算法。
     * 是从一个顶点到其余各顶点的最短路径算法，解决的是有权图中最短路径问题。
     * 迪杰斯特拉算法主要特点是从起始点开始，采用贪心算法的策略，
     * 每次遍历到始点距离最近且未访问过的顶点的邻接节点，直到扩展到终点为止
     */
    public void djstlaMinTree() {
        boolean[] isPath = new boolean[vertexSize];
        int[] shortPath = new int[vertexSize];


        //第一列数据 先放到lowcost
        for (int i = 0; i < vertexSize; i++) {
            shortPath[i] = matrix[0][i];
        }


        //起始点为0
        isPath[0] = true;
        shortPath[0] = 0;

        int minId = 0;

        for (int i = 1; i < vertexSize; i++) {
            int min = MAX_WEIGHT;

            for (int j = 0; j < vertexSize; j++) {
                if(!isPath[j] && shortPath[j] < min){
                     min = shortPath[j];
                     minId = j;
                }
            }

            isPath[minId] = true;
            Log.d("TAG", "djstlaMinTree: 找到顶点="+minId+"最短路径长度="+shortPath[minId]);

            for (int k = 0; k < vertexSize; k++) {
                if(!isPath[k] && (min+matrix[minId][k]) < shortPath[k]){
                    shortPath[k] = min + matrix[minId][k];
                }
            }

            StringBuilder builder1 = new StringBuilder();
            for (int k = 0; k < vertexSize; k++) {
                builder1.append(shortPath[k]).append(",");
            }
            Log.d("TAG", "djstlaMinTree: 每一次的比较"+builder1.toString());
        }

    }
}
