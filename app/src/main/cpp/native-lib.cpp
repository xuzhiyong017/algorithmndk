#include <jni.h>
#include <string>
#include <android/log.h>
#include "LinkedList.hpp"
#include "ArrayUtil.hpp"
#include "SortUtil.hpp"

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

extern "C" JNIEXPORT jstring JNICALL
Java_com_sky_algorithmndk_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";

    int len = 10;
    int* arr = ArrayUtil::create_random_data(len,0,10);
    int* arr1 = ArrayUtil::copy_array_data(arr,len);
    ArrayUtil::sort_array("shellInsertSort",SortUtil::shellInsertSort,arr, len);
    ArrayUtil::sort_array("bubbleSort",SortUtil::bubbleSort,arr1, len);

//    testLinkedList();
    return env->NewStringUTF(hello.c_str());
}


