//
// Created by sky on 20-12-30.
//

#ifndef ALGORITHMNDK_PRIORITYQUEUE_HPP
#define ALGORITHMNDK_PRIORITYQUEUE_HPP

#include <iostream>
#include "jlog.h"
using namespace std;

template <typename T>
class PriorityQueue{
    int count = 0;//数组大小
    int index = 0;//当前数据的角标位置
    T* array = NULL;
private:
    void shiftUp(int index){
        if(index > 1 && array[index] > array[index / 2]){
            swap(array[index],array[index/2]);
            shiftUp(index/2);
        }
    }
public:
    PriorityQueue(int count){
        this->count = count;
        array = new T[count+1];
    }

    bool isEmpty(){
        return index == 0;
    }

    void shiftDown(int k) {
        while (k * 2 <= index){
            int max = 2 * k;
            if(max + 1 < index && array[max + 1] > array[max]){
                max = max + 1;
            }
            if(array[k] > array[max]){
                break;
            }

            swap(array[k],array[max]);
            k = max;
        }
    }

    T pop(){
        T max = array[1];
        array[1] = array[index];
        index--;
        shiftDown(1);
        return max;

    }

    void push(T t){
        array[index+1] = t;
        index++;

        //不断的调整堆
        shiftUp(index);

        for (int i = 0; i < index; ++i) {
            LOGE("%d",array[i+1]);
        }
        LOGE("----------------------------");
    }
};
#endif //ALGORITHMNDK_PRIORITYQUEUE_HPP
