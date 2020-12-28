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

    //冒泡排序
    void bubbleSort(int *arr,int len){
        for (int i = 0; i < len - 1; ++i) {
            for (int j = 0; j < len - i - 1; ++j) {
                if(arr[j] > arr[j+1]){
                    std::swap(arr[j],arr[j+1]);
                }
            }
        }
    }
}

#endif //ALGORITHMNDK_SORTUTIL_HPP
