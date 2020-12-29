//
// Created by sky on 20-12-28.
//

#ifndef ALGORITHMNDK_SORTUTIL_HPP
#define ALGORITHMNDK_SORTUTIL_HPP

namespace SortUtil{
    //希尔排序，子排序也就是插入排序
    void shellInsertSort(int arr[],int len){
        //定义步长
        int increment  = len / 2;
        int i,j;
        while (increment > 0){
            for (i = 0; i < len; i+=increment) {
                 int tmp = arr[i];
                for (j = i; j > 0 && arr[j-increment] > tmp; j-= increment) {
                    arr[j] = arr[j-increment];
                }
                arr[j] = tmp;
            }
            increment /= 2;
        }
    }

    //冒泡排序 相邻元素直接进行交换
    void bubbleSort(int *arr,int len){
        for (int i = 0; i < len - 1; ++i) {
            for (int j = 0; j < len - i - 1; ++j) {
                if(arr[j] > arr[j+1]){
                    std::swap(arr[j],arr[j+1]);
                }
            }
        }
    }

    //选择排序 思想：遍历找出最小的位置,然后与第一个位置进行交换
    void selectSort(int * arr,int len){
        for (int i = 0; i < len; ++i) {
            int min = i;
            for (int j = i + 1; j < len; ++j) {
                if(arr[min] > arr[j]){
                    min = j;
                }
            }
            std::swap(arr[i],arr[min]);
        }
    }

    //插入排序-前身
    void insertSort(int* arr,int len){
        for (int i = 1; i < len; ++i) {
            for (int j = i; j > 0; --j) {
                if(arr[j] < arr[j-1]){
                    std::swap(arr[j],arr[j-1]);
                }else{
                    break;
                }
            }
        }
    }

    //插入排序-优化
    void insertOptSort(int* arr,int len){
        int j;
        for (int i = 1; i < len; ++i) {
            int tmp = arr[i];
            for (j = i; j > 0 && arr[j-1] > tmp; --j) {
                arr[j] = arr[j-1];
            }
            arr[j] = tmp;
        }
    }

    //quickSort //快速排序，每次找到当前值的位置，然后递归执行
    int indexArrayPos(int* arr,int low,int high){
       int tem = arr[low];
       while (low < high){
           while (arr[high] > tem && low < high){
               high--;
           }

           if(low < high){
               arr[low] = arr[high];
               low++;
           }
           while (arr[low] < tem && low < high){
               low++;
           }

           if(low < high){
               arr[high] = arr[low];
               high--;
           }
       }
       arr[high] = tem;
        return high;
    }

    void QuickSort_(int* arr,int low,int high){
        if(low < high)
        {
            int center = indexArrayPos(arr,low,high);
            QuickSort_(arr,low,center -1);
            QuickSort_(arr,center+1,high);
        }

    }

    void QuickSort(int* arr,int len){
        QuickSort_(arr,0,len -1);
    }





    //归并排序


}

#endif //ALGORITHMNDK_SORTUTIL_HPP
