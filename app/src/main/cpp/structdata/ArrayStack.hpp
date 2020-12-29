//
// Created by sky on 20-12-29.
//

#ifndef ALGORITHMNDK_ARRAYSTACK_HPP
#define ALGORITHMNDK_ARRAYSTACK_HPP

template <typename E>
class ArrayStack{
private:
    int top = -1;//栈顶元素
    E *array = NULL;
    int size = 10;
public:
    ArrayStack();
    ~ArrayStack();

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
    void growArray();
};

template<typename E>
ArrayStack<E>::ArrayStack() {
    array = new E[size];
}

template<typename E>
ArrayStack<E>::~ArrayStack() {
    delete []array;
}

template<typename E>
E ArrayStack<E>::pop() {
    assert(top >= 0);
    return array[top--];
}

template<typename E>
bool ArrayStack<E>::isEmpty() {
    return top == -1;
}

template<typename E>
E ArrayStack<E>::peek() {
    return array[top];
}

template<typename E>
void ArrayStack<E>::push(E value) {
    if(top + 1 == size){
        growArray();
    }
    array[++top] = value ;
}

template<typename E>
void ArrayStack<E>::growArray() {
    size += size >> 1;
    E *temp = new E[size];
    for (int i = 0; i < size; ++i) {
        temp[i] = array[i];
    }
    delete[] array;
    array = temp;
}


#endif //ALGORITHMNDK_ARRAYSTACK_HPP
