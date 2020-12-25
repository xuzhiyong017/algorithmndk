//
// Created by sky on 20-12-25.
//

#ifndef ALGORITHMNDK_LINKEDLIST_HPP
#define ALGORITHMNDK_LINKEDLIST_HPP

#include <assert.h>

template <class E>
struct Node{
    Node<E>* next;
    E value;

public:
    Node(E value,Node<E>* next):value(value),next(next){

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
    void remove(int index);
    Node<E>* node(int pos);
};

template <class E>
void LinkedList<E>::push(E e) {
    //添加一个数据
    Node<E>* new_node = new Node<E>(e,NULL);
    if(head){
//        Node<E>* cur = head;
//        while(cur){
//            if(cur->next == NULL){
//                break;
//            }
//            cur = cur->next;
//        }
//        cur->next = new_node;

        //优化
//        Node<E>* last = node(len -1);
//        last->next = new_node;
        tail->next = new_node;
        tail = new_node;
    }else{
        head = new_node;
        tail = new_node;
    }
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

}

template<class E>
void LinkedList<E>::insert(int index, E value) {
    //烤炉边界
    Node<E>* new_node = new Node<E>(value,NULL);
    if(index == 0){
        Node<E>* h = head;
        head = new_node;
        new_node->next = h;
    }else{
        Node<E>* pre_node = node(index -1);
        new_node->next = pre_node->next;
        pre_node->next = new_node;
    }
    len++;
}

template<class E>
void LinkedList<E>::remove(int index) {
    assert(index>=0 && index < len);
    if(index == 0){
        Node<E>* h = head;
        head = head->next;
        delete h;
    }else{
        Node<E>* pre_node = node(index -1);
        Node<E>* remove_node = pre_node->next;
        pre_node->next = pre_node->next->next;
        delete remove_node;
    }

    len--;
}

#endif //ALGORITHMNDK_LINKEDLIST_HPP
