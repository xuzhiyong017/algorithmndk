//
// Created by sky on 20-12-31.
//

#ifndef ALGORITHMNDK_MAP_HPP
#define ALGORITHMNDK_MAP_HPP



/**
 * 红黑树的特性:
（1）每个节点或者是黑色，或者是红色。
（2）根节点是黑色。
（3）每个叶子节点（NIL）是黑色。 [注意：这里叶子节点，是指为空(NIL或NULL)的叶子节点！]
（4）如果一个节点是红色的，则它的子节点必须是黑色的。
（5）从一个节点到该节点的子孙节点的所有路径上包含相同数目的黑节点。
 */

#include <queue>
using namespace std;

typedef bool rb_color;
#define red true //红色
#define black false //黑色

template <class K,class V>
class map{
private:
    struct TreeNodeMap;
    int count;
    TreeNodeMap * root = NULL;
public:
    map(){
        count = 0;
    }

public:
    struct iterator;

    TreeNodeMap *parent(TreeNodeMap* pNode){
        return pNode ? pNode->parent:NULL;
    }

    TreeNodeMap *right(TreeNodeMap* pNode){
        return pNode ? pNode->right:NULL;
    }
    TreeNodeMap *left(TreeNodeMap* pNode){
        return pNode ? pNode->left:NULL;
    }

    bool getColor(TreeNodeMap *pNode){
        return pNode ? pNode->color:black;
    }

    TreeNodeMap *brother(TreeNodeMap* pNode){
        return left(parent(pNode)) == pNode ? right(parent(pNode)) : left(parent(pNode));
    }

    void setColor(TreeNodeMap * pNode, rb_color color){
        if(pNode){
            pNode->color = color;
        }
    }

    TreeNodeMap * L_Rotation(TreeNodeMap *pNode){
        TreeNodeMap * right = pNode->right;
        TreeNodeMap * left = right->left;
        right->left = pNode;
        pNode->right = left;

        //调整pNode 父亲的儿子指向
        if(pNode->parent == NULL){
            root = right;
        }else if(pNode->parent->left == pNode){
            pNode->parent->left = right;
        }else{
            pNode->parent->right = right;
        }

        //调整各大节点的父亲
        right->parent = pNode->parent;
        if(pNode->right){
            pNode->right->parent = pNode;
        }
        pNode->parent = right;
        return right;
    }

    TreeNodeMap * R_Rotation(TreeNodeMap *pNode){
        TreeNodeMap * left = pNode->left;
        TreeNodeMap * right = left->right;
        left->right = pNode;
        pNode->left = right;

        //调整pNode 父亲的儿子指向
        if(pNode->parent == NULL){
            root = left;
        }else if(pNode->parent->left == pNode){
            pNode->parent->left = left;
        }else{
            pNode->parent->right = left;
        }

        //调整各大节点的父亲
        left->parent = pNode->parent;
        if(pNode->left){
            pNode->left->parent = pNode;
        }
        pNode->parent = left;
        return left;
    }

    void solveDoubleRed(TreeNodeMap * pNode){

        while (pNode->parent && pNode->parent->color == red){ //情况1  父亲是红色
            if(getColor(brother(parent(pNode))) == red){ // 情况2  兄弟也红色
                //父亲染成黑色，父亲的兄弟也染成黑色，爷爷染成红色
                setColor(parent(pNode),black);
                setColor(brother(parent(pNode)),black);
                setColor(parent(parent(pNode)),red);

                //指针回溯到爷爷
                pNode = parent(parent(pNode));
            }else{ //情况3

                //两种情况 321，还是354

                if(left(parent(parent(pNode))) == parent(pNode)){
                    // 3-》1-》2
                    if(right(parent(pNode)) == pNode){ //情况3.1
                        pNode = parent(pNode);
                        L_Rotation(pNode);
                    }

                    //情况3.2   3 >> 2 >> 1
                    setColor(parent(pNode),black);
                    setColor(parent(parent(pNode)),red);
                    R_Rotation(parent(parent(pNode)));
                }else{
                    // 3-》5-》4
                    if(left(parent(pNode)) == pNode){ //情况3.1
                        pNode = parent(pNode);
                        R_Rotation(pNode);
                    }

                    //情况3.2   3 >> 4 >> 5
                    setColor(parent(pNode),black);
                    setColor(parent(parent(pNode)),red);
                    L_Rotation(parent(parent(pNode)));
                }



            }
        }
        root->color = black;
    }

    iterator insert(K key,V value){
        //插入算法
        if(root == NULL){
            root = new TreeNodeMap(NULL,NULL,NULL,key,value, black);
            count = 1;
            return iterator(root);
        }

        //最好我们插入红色节点，它不会违反性质5.但可能会违反性质4
        TreeNodeMap * node = root;
        TreeNodeMap*parent = NULL;

        do{
            parent = node;
            if(key == node->key){
                node->value = value;
            }else if(key > node->key){
                node = node->right;
            }else{
                node = node->left;
            }
        }while(node);

        TreeNodeMap * new_node = new TreeNodeMap(NULL,NULL,parent,key,value,red);

        if(key > parent->key){
            parent->right = new_node;
        }else{
            parent->left = new_node;
        }

        //双红修正
        solveDoubleRed(new_node);

        count++;
        return iterator(new_node);
    }

    TreeNodeMap * findTree(K key){
        TreeNodeMap * node = root;
        while(node){
            if(node->key > key){
                node = node->left;
            }else if(node->key < key){
                node = node->right;
            }else {
                return node;
            }
        }
        return NULL;
    }


    /**
     * 分情况讨论下：

        1.如果兄弟节点是红色的，把兄弟节点染黑，父节点染红，左/右旋父节点；
        2.如果兄弟节点是黑色的，并且两个侄子节点都是黑色的，将兄弟节点染红，指针回溯至父亲节点；
        3.如果兄弟节点是黑色，的并且远侄子是黑色的，近侄子是红色的，将进侄子染黑，兄弟染红，左/右旋兄弟节点，进入下面情况 4 ；
        4.如果兄弟节点是黑色的，并且远侄子是红色的，近侄子随意，将兄弟节点染成父亲节点的颜色，父亲节点染黑，远侄子染黑，左/右旋父亲节点。

     * @param pNode
     */

    //修正颜色
    void sloveLostBlack(TreeNodeMap * pNode){
        while (pNode != root && pNode->color == black){
            if(left(parent(pNode)) == pNode){ //当前节点是父亲节点的左节点
                TreeNodeMap * sib = brother(pNode);
                if(getColor(sib) == red){ //情况1 想办法把兄弟节点变成黑色
                    setColor(sib,black);
                    setColor(parent(pNode),red);
                    L_Rotation(parent(pNode));
                    sib = brother(pNode);
                }

                if(getColor(left(sib)) == black && getColor(right(sib)) == black){ //情况2
                    setColor(sib,red);
                    pNode = parent(pNode);
                }else{

                    //情况3
                    if(getColor(right(sib)) == black){
                        setColor(sib,red);
                        setColor(left(sib),black);
                        R_Rotation(sib);
                        //还有问题

                        sib = brother(sib);
                    }

                    //情况4
                    setColor(sib,getColor(parent(pNode)));
                    setColor(parent(pNode),black);
                    setColor(right(sib),black);

                    L_Rotation(parent(pNode));

                    //相当于两行代码，break，将根节点染黑
                    pNode = root;
                }
            }else{ //当前节点是父亲的右节点

                TreeNodeMap * sib = brother(pNode);
                if(getColor(sib) == red){ //想办法把兄弟节点变成黑色
                    setColor(sib,black);
                    setColor(parent(pNode),red);
                    R_Rotation(parent(pNode));
                    sib = brother(pNode);
                }

                if(getColor(left(sib)) == black && getColor(right(sib)) == black){
                    setColor(sib,red);
                    pNode = parent(pNode);
                }else{

                    //情况3 远侄子是黑
                    if(getColor(left(sib)) == black){
                        setColor(sib,red);
                        setColor(right(sib),black);
                        L_Rotation(sib);
                        //还有问题

                        sib = brother(sib);
                    }

                    //情况4
                    setColor(sib,getColor(parent(pNode)));
                    setColor(parent(pNode),black);
                    setColor(left(sib),black);

                    R_Rotation(parent(pNode));

                    //相当于两行代码，break，将根节点染黑
                    pNode = root;
                }
            }
        }
        //当遇到一个红色节点，把红色节点染黑即可
        pNode->color = black;
    }

    bool remove(K key){
        TreeNodeMap * current = findTree(key);
        if(current == NULL){
            return false;
        }

        //左右子树都不为空
        if(current->left != NULL && current->right != NULL){
            TreeNodeMap * successor = current->successor();
            current->key = successor->key;
            current->value = successor->value;
            current = successor;
        }


        TreeNodeMap * replace = current->left ? current->left : current->right;

        //左右一个子树为空的情况
        if(replace != NULL){

            //父亲，current-parent 会不会有空的情况
            if(current->parent == NULL) {
                root = replace;
            }else if(current->parent->left == current){
                current->parent->left = replace;
            }else{
                current->parent->right = replace;
            }

            replace->parent = current->parent;

            if(current->color == black){
                sloveLostBlack(replace);
            }
            delete current;
        }else if(current->parent == NULL){//删除根节点
            delete root;
            root = NULL;
        }else{

            //为什么把不先移除，是因为再修正的时候需要获取叔叔和侄子节点来分情况判断
            //左右子树都是空并且当前节点是黑色，需要修正
            if(current->color == black){
                sloveLostBlack(current);
            }

            //再来移除
            if(current->parent){
                if(current->parent->left == current){
                    current->parent->left = NULL;
                }else{
                    current->parent->right = NULL;
                }
            }
            delete current;
        }

        count--;
        return true;
    }

    int size(){
        return count;
    }

    bool isEmpty(){
        return count == 0;
    }

    void levelOrderTraverse(void(*fun)(int,int,bool)){
        if(root == NULL){
            return;
        }
        queue<TreeNodeMap *> qQueue;
        TreeNodeMap * treeNodeMap = root;
        qQueue.push(treeNodeMap);
        while (!qQueue.empty()){
            TreeNodeMap * node = qQueue.front();
            qQueue.pop();
            fun(node->key,node->value,node->color);

            if(node->left){
                qQueue.push(node->left);
            }
            if(node->right){
                qQueue.push(node->right);
            }
        }
    }
};

template <class K,class V>
struct map<K,V>::TreeNodeMap{
    TreeNodeMap * left = NULL;
    TreeNodeMap * right = NULL;
    TreeNodeMap * parent = NULL;
    K key;
    V value;
    //颜色
    rb_color color;

    TreeNodeMap(TreeNodeMap *left, TreeNodeMap *right, TreeNodeMap *parent, K key, V value, rb_color color)
            : left(left), right(right), parent(parent), key(key), value(value), color(color) {}


    TreeNodeMap *precursor(){
        if(left){
            TreeNodeMap * node = left;
            while (node->right){
                node = node->right;
            }
            return node;
        }else{
            TreeNodeMap * node = this;
            while (node->parent->left == node){
                node = node->parent;
            }
            return node->parent;
        }
        return NULL;
    }

    //后继节点查找
    TreeNodeMap *successor(){
        if(right){
            TreeNodeMap * node = right;
            while (node->left){
                node = node->left;
            }
            return node;
        }else{
            TreeNodeMap * node = this;
            while (node->parent->right == node){
                node = node->parent;
            }
            return node->parent;
        }
        return NULL;
    }
};


template <class K,class V>
struct map<K,V>::iterator{
private:
    TreeNodeMap *node;
public:
    iterator(TreeNodeMap * node):node(node){

    }

    iterator& operator--(){
        node = node->precursor();
        return this;
    }

    iterator& operator++(){
        node = node->successor();
        return this;
    }

    V operator*(){
        return node->value;
    }
};
#endif //ALGORITHMNDK_MAP_HPP
