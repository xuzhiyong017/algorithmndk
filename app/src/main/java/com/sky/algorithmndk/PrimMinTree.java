package com.sky.algorithmndk;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class PrimMinTree extends AppCompatActivity {


    @BindView(R.id.text1)
    TextView textView;

    @BindView(R.id.text2)
    TextView text2;

    @OnClick(R.id.text2)
    void start(){

    }


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
//        graph.primMinTree();
        //迪杰思特拉算法
        graph.djstlaMinTree();
    }
}