//
// Created by sky on 20-12-29.
//

#ifndef ALGORITHMNDK_LINKSTACK_HPP
#define ALGORITHMNDK_LINKSTACK_HPP
template <typename E>
class LinkStack{
public:
    LinkStack();
    ~LinkStack();

    /**
     *  栈是否为空
     * @return
     */
    bool isEmpty();

    /**
     *  弹出栈顶元素
     * @return
     */
    E pop();

    /**
     *  获取栈顶元素但是不弹出
     * @return
     */
    E peek();
    /**
     *  压入栈元素
     * @param value
     */
    void push(E value);
private:
    Node<E>* head = NULL;
    Node<E>* top = NULL;
    int size = 0;

};

template<typename E>
void LinkStack<E>::push(E value) {
    Node<E>* l = top;
    Node<E> * new_node = new Node<E>(value,l,NULL);
    if(top){
        top->next = new_node;
    }else{
        head = new_node;
        top = new_node;
    }
    top = new_node;
    size++;
}

template<typename E>
E LinkStack<E>::pop() {
    if(top == NULL){
        return NULL;
    }
    Node<E> * prev_node = top->prev;
    if(prev_node){
        prev_node->next = NULL;
    }
    int value = top->value;
    delete top;
    top = prev_node;
    size--;
    return value;
}

template<typename E>
LinkStack<E>::~LinkStack() {
    //释放内存
    Node<E>* h = head;
    while (h){
        Node<E> * next = h->next;
        delete h;
        h = next;
    }
    //头指针，未指针置空
    head = NULL;
    top = NULL;
}

template<typename E>
bool LinkStack<E>::isEmpty() {
    return size == 0;
}

template<typename E>
E LinkStack<E>::peek() {
    return top->value;
}

template<typename E>
LinkStack<E>::LinkStack() {

}

#endif //ALGORITHMNDK_LINKSTACK_HPP
