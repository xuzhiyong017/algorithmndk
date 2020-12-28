//
// Created by sky on 20-12-28.
//
#include <android/log.h>
#include <string>
namespace ArrayUtil{
    int * create_random_data(int len,int low,int high){
        int *arr = new int[len];
        srand(time(NULL));
        for (int i = 0; i < len; i++)
        {
            arr[i] = rand() % (high - low) + low;
        }
        return arr;
    }

    void sort_array(char* methodName,void(*callSortMethod)(int*,int),int* arr,int len){
        size_t start = clock();
        callSortMethod(arr,len);
        size_t end = clock();
        double  time = (double)(end -start)/CLOCKS_PER_SEC;
        __android_log_print(ANDROID_LOG_ERROR,"TAG","%s执行时间%lf",methodName,time);
        for (int i = 0; i < len; i++)
        {
            __android_log_print(ANDROID_LOG_ERROR,"TAG","%d ",arr[i]);
        }
    }
}
