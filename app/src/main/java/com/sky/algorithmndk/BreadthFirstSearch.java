package com.sky.algorithmndk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class BreadthFirstSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

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
}