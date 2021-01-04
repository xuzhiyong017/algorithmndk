#include <jni.h>
#include <string>
#include <android/log.h>
#include "LinkedList.hpp"
#include "ArrayUtil.hpp"
#include "SortUtil.hpp"
#include "structdata/ArrayStack.hpp"
#include "structdata/LinkStack.hpp"
#include "question/hannuota.hpp"
#include <queue>
#include "tree/TreeNode.hpp"
#include "tree/PriorityQueue.hpp"
#include "tree/BST.hpp"
#include "tree/AVL.hpp"
#include "tree/map.hpp"

void testTreeNode();

template <typename T>
void preOrderTraverse(TreeNode<T> *pNode,void(*visit)(T));

template <typename T>
void midOrderTraverse(TreeNode<char> *pNode,void(*visit)(T));

template <typename T>
void lastOrderTraverse(TreeNode<T> *pNode, void (*visit)(T));

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

void testPriorityQueue() {
    int n = 10;
    PriorityQueue<int> pQueue(n);
    srand(time(NULL));
    for (int i = 0; i < n; ++i) {
        pQueue.push(rand() % 100);
    }
    LOGE("queue pop-------------");
    while (!pQueue.isEmpty()){
        LOGE("%d",pQueue.pop());
    }
}

void testHeadSort() {
    int len = 10;
    int* arr = ArrayUtil::create_random_data(len,0,10);
    ArrayUtil::sort_array("headSort",SortUtil::headSort,arr, len);
}

void visit(int key,int value){
    LOGE("key=%d,value=%d",key,value);
}

void visitMapTree(int key,int value,bool isRed){
    if(isRed){
        LOGE("key=%d,value=%d,color=红",key,value);
    }else{
        LOGE("key=%d,value=%d,color=黑",key,value);
    }
}

void testBSTree() {

    BST<int,int>* bst = new BST<int,int>();

    bst->put(2,2);
    bst->put(-11,-11);
    bst->put(-13,-13);
    bst->put(0,0);
    bst->put(8,8);
    bst->put(3,3);
    bst->put(7,7);

    bst->remove(2);
//    bst->remove(0);
    bst->remove(10);

    bst->inOrderTraverse(visit);


//    LOGE("find data value=%d",bst->get(3));

}

void testAVLTree() {
    AVL<int,int>* bst = new AVL<int,int>();

    bst->put(3,3);
    bst->put(1,1);
    bst->put(2,2);
    bst->put(4,4);
    bst->put(5,5);
    bst->put(6,6);
    bst->put(7,7);
    bst->put(10,10);
    bst->put(9,9);
    bst->put(8,8);

    bst->remove(4);
    bst->remove(5);
    bst->remove(2);
//    bst->remove(1);

    bst->levelOrderTraverse(visit);
}

void testMap() {
    //测试红黑树
    map<int,int>* bst = new map<int,int>();

    bst->insert(3,3);
    bst->insert(2,2);
    bst->insert(1,1);
    bst->insert(4,4);
    bst->insert(5,5);
    bst->insert(-5,-5);
    bst->insert(-15,-15);
    bst->insert(-10,-10);
    bst->insert(6,6);
    bst->insert(7,7);

    bst->remove(2);
    LOGE("红黑树插入");
    bst->levelOrderTraverse(visitMapTree);

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
//     testHannuota();
//        testTreeNode();
//        testPriorityQueue();
//        testHeadSort();
//        testBSTree();
//        testAVLTree();

        testMap();

    return env->NewStringUTF(hello.c_str());
}

void visitPNode(char data){
    LOGE("%c",data);
}

template <typename T>
void levelOrderTraverse(TreeNode<T> *pNode, void (*visit)(T)) {
    if(pNode == NULL){
        return;
    }
    std::queue<TreeNode<T>*> nodeQ;
    nodeQ.push(pNode);
    while (!nodeQ.empty()){
        TreeNode<T> *front = nodeQ.front();
        nodeQ.pop();
        visit(front->data);

        if(front->left){
            nodeQ.push(front->left);
        }
        if(front->right){
            nodeQ.push(front->right);
        }
    }

}

int getTreeDepth(TreeNode<char> *pNode) {
    if(pNode == NULL){
        return 0;
    }

    int left = getTreeDepth(pNode->left);
    int right = getTreeDepth(pNode->right);
    return std::max(left,right) + 1;
}

bool isBalanceTree(TreeNode<char> *pNode) {
    if(pNode == NULL){
        return true;
    }

    int left = getTreeDepth(pNode->left);
    int right = getTreeDepth(pNode->right);
    return std::abs(left - right) <= 1 && isBalanceTree(pNode->left) && isBalanceTree(pNode->right);
}

TreeNode<char> * seralizeTree(char** tree) {
    LOGE("字符串=%c",**tree);
    if(**tree == '#'){
        *tree+=1;
        return NULL;
    }
    char * temp = *tree;
    *tree += 1;
    TreeNode<char> * node = new TreeNode<char>(*temp,seralizeTree(tree),seralizeTree(tree));
    return node;
}


void testTreeNode() {
    TreeNode<char>* F = new TreeNode<char>('F',NULL,NULL);
    TreeNode<char>* E = new TreeNode<char>('E',NULL,NULL);
    TreeNode<char>* D = new TreeNode<char>('D',NULL,NULL);
    TreeNode<char>* C = new TreeNode<char>('C',NULL,F);
    TreeNode<char>* B = new TreeNode<char>('B',D,E);
    TreeNode<char>* A = new TreeNode<char>('A',B,C);

    LOGE("----------前序遍历-------------");
    preOrderTraverse(A,visitPNode);
    LOGE("----------中序遍历-------------");
    midOrderTraverse(A,visitPNode);
    LOGE("----------后序遍历-------------");
    lastOrderTraverse(A,visitPNode);

    LOGE("----------层次遍历-------------");
    levelOrderTraverse(A,visitPNode);
    LOGE("----------获取树的深度 %d -------------",getTreeDepth(A));
    LOGE("----------是否是平衡二叉树 %d -------------",isBalanceTree(A));

    char * strTree = "ABD##E##C#F##";
    seralizeTree(&strTree);
}



template <typename T>
void lastOrderTraverse(TreeNode<T> *pNode, void (*visit)(T)) {
    //首先
    if(pNode){
        //首先访问左节点
        lastOrderTraverse(pNode->left,visit);
        //然后访问右节点
        lastOrderTraverse(pNode->right,visit);
        //最后访问根节点
        visit(pNode->data);
    }
}

//前序遍历  ABDECF
template <typename T>
void preOrderTraverse(TreeNode<T> *pNode,void(*visit)(T)) {
    //首先
    if(pNode){
        //首先访问根节点
        visit(pNode->data);
        //然后访问左节点
        preOrderTraverse(pNode->left,visit);
        //最后访问右节点
        preOrderTraverse(pNode->right,visit);
    }
}

//中序遍历  DBEACF
template <typename T>
void midOrderTraverse(TreeNode<char> *pNode,void(*visit)(T)) {
//首先
    if(pNode){
        //首先访问左节点
        midOrderTraverse(pNode->left,visit);
        //然后访问根节点
        visit(pNode->data);
        //最后访问右节点
        midOrderTraverse(pNode->right,visit);
    }
}




