//
// Created by sky on 20-12-25.
//

#ifndef ALGORITHMNDK_LINKEDLIST_HPP
#define ALGORITHMNDK_LINKEDLIST_HPP

#include <assert.h>

template <class E>
struct Node{
    Node<E>* next;
    Node<E>* prev;
    E value;

public:
    Node(E value,Node<E>* prev,Node<E>* next):value(value),next(next),prev(prev){

    }
};

template <class E>
class LinkedList{
    //头指针
    Node<E>* head = NULL;
    //尾节点
    Node<E>* tail = NULL;
    //集合的长度
    int len = 0;

public:
    ~LinkedList();

    void push(E e);
    int size();
    E get(int index);
    void insert(int index,E value);
    E remove(int index);
    void linkLast(E e);
    void linkBefore(Node<E>* cur,E e);
    E unlink(Node<E>* cur);
    Node<E>* node(int pos);
};

template <class E>
void LinkedList<E>::push(E e) {
    //添加一个数据
//    Node<E>* new_node = new Node<E>(e,NULL);
//    if(head){
////        Node<E>* cur = head;
////        while(cur){
////            if(cur->next == NULL){
////                break;
////            }
////            cur = cur->next;
////        }
////        cur->next = new_node;
//
//        //优化
////        Node<E>* last = node(len -1);
////        last->next = new_node;
//        tail->next = new_node;
//        tail = new_node;
//    }else{
//        head = new_node;
//        tail = new_node;
//    }
    linkLast(e);
    len++;
}


template <class E>
Node<E>* LinkedList<E>::node(int pos) {
    Node<E>* h = head;
    for (int i = 0; i < pos; ++i) {
        h = h->next;
    }
    return h;
}

template<class E>
int LinkedList<E>::size() {
    return len;
}

template<class E>
E LinkedList<E>::get(int index) {
    assert(index>=0 && index < len);
    return node(index)->value;
}

template<class E>
LinkedList<E>::~LinkedList() {
    //释放内存
    Node<E>* h = head;
    while (h){
        Node<E> * next = h->next;
        delete h;
        h = next;
    }
    //头指针，未指针置空
    head = NULL;
    tail = NULL;
}

template<class E>
void LinkedList<E>::insert(int index, E value) {
    assert(index >= 0 && index <= len);

    if(index == len){
        linkLast(value);
    }else{
        linkBefore(node(index),value);
    }

    //烤炉边界
//    Node<E>* new_node = new Node<E>(value,NULL);
//    if(index == 0){
//        Node<E>* h = head;
//        head = new_node;
//        new_node->next = h;
//    }else{
//        Node<E>* pre_node = node(index -1);
//        new_node->next = pre_node->next;
//        pre_node->next = new_node;
//    }
    len++;
}

template<class E>
E LinkedList<E>::remove(int index) {
    assert(index>=0 && index < len);


//    if(index == 0){
//        Node<E>* h = head;
//        head = head->next;
//        delete h;
//    }else{
//        Node<E>* pre_node = node(index -1);
//        Node<E>* remove_node = pre_node->next;
//        pre_node->next = pre_node->next->next;
//        delete remove_node;
//    }

    return unlink(node(index));
}

template<class E>
void LinkedList<E>::linkLast(E e) {
    Node<E>* l = tail;

    Node<E>* new_node = new Node<E>(e,l,NULL);

    tail = new_node;

    if(head){
        l->next = new_node;
    }else{
        head = new_node;
    }
}

template<class E>
void LinkedList<E>::linkBefore(Node<E>* cur, E e) {
    Node<E>* prev = cur->prev;
    Node<E>* new_node = new Node<E>(e,prev,cur);
    //当前的上一个节点 = 新增的节点
    cur->prev = new_node;
    //上一个节点的next -> 新增的节点
    if(prev){
        prev->next = new_node;
    }else{
        head = new_node;
    }

}

template<class E>
E LinkedList<E>::unlink(Node<E> *cur) {
    Node<E>* pre = cur->prev;
    Node<E>* next = cur->next;

    E value = cur->value;
    //有前后不为空的情况要可考虑
    if(pre){
        pre->next = next;
    }else{
        head = next;
    }

    if(next){
        next->prev = pre;
    }else{
        tail = pre;
    }
    len--;
    delete cur;
    return value;
}

#endif //ALGORITHMNDK_LINKEDLIST_HPP
