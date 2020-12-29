#include <jni.h>
#include <string>
#include <android/log.h>
#include "LinkedList.hpp"
#include "ArrayUtil.hpp"
#include "SortUtil.hpp"
#include "structdata/ArrayStack.hpp"
#include "structdata/LinkStack.hpp"
#include "question/hannuota.hpp"

void testArrayStack(){
    ArrayStack<int> stack;
    for (int i = 0; i < 11; ++i) {
        stack.push(i);
    }

    while (!stack.isEmpty()){
        __android_log_print(ANDROID_LOG_ERROR,"TAG","stack %d",stack.pop());
    }
}

void testLinkStack(){
    LinkStack<int> stack;

    stack.push(0);
    stack.push(1);
    stack.push(2);
    stack.push(3);


    stack.pop();
    stack.pop();
    stack.push(4);
    stack.push(5);

    stack.pop();

//    for (int i = 0; i < 11; ++i) {
//        stack.push(i);
//    }

    while (!stack.isEmpty()){
        __android_log_print(ANDROID_LOG_ERROR,"TAG","stack %d",stack.pop());
    }
}

void testLinkedList(){
    //测试代码
    LinkedList<int> linkedList;
    linkedList.push(0);
    linkedList.push(1);
    linkedList.push(2);

    linkedList.insert(0,-1);
    linkedList.insert(linkedList.size(),3);
    linkedList.insert(2,22);

    linkedList.remove(0);
//
//    time_t start = clock();
//    for (int i = 0; i < 50000; ++i) {
//        linkedList.linkLast(i);
//    }
//    time_t end = clock();
//
//    __android_log_print(ANDROID_LOG_ERROR,"TAG","添加耗时%d秒",(end - start)/CLOCKS_PER_SEC);
    for (int i = 0; i < linkedList.size(); ++i) {
        __android_log_print(ANDROID_LOG_ERROR,"TAG","%d",linkedList.get(i));
    }
}

void testSortMethod(){
    int len = 10;
    int* arr = ArrayUtil::create_random_data(len,0,10);
    int* arr1 = ArrayUtil::copy_array_data(arr,len);
    int* arr2 = ArrayUtil::copy_array_data(arr,len);
    int* arr3 = ArrayUtil::copy_array_data(arr,len);
    int* arr4 = ArrayUtil::copy_array_data(arr,len);
    int* arr5 = ArrayUtil::copy_array_data(arr,len);
    ArrayUtil::sort_array("shellInsertSort",SortUtil::shellInsertSort,arr, len);
    ArrayUtil::sort_array("bubbleSort",SortUtil::bubbleSort,arr1, len);
    ArrayUtil::sort_array("selectSort",SortUtil::selectSort,arr2, len);
    ArrayUtil::sort_array("insertSort",SortUtil::insertSort,arr3, len);
    ArrayUtil::sort_array("insertSort",SortUtil::insertOptSort,arr4, len);
    ArrayUtil::sort_array("QuickSort",SortUtil::QuickSort,arr5, len);


    delete []arr;
    delete []arr1;
    delete []arr2;
    delete []arr3;
    delete []arr4;
    delete []arr5;
}

void testHannuota() {
    hannuota(3,'A','B','C');
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sky_algorithmndk_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
//    testLinkedList();
//    testSortMethod();
//    testArrayStack();
//    testLinkStack();
     testHannuota();
    return env->NewStringUTF(hello.c_str());
}


