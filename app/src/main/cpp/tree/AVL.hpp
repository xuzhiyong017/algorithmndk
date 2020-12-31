//
// Created by sky on 20-12-30.
//

#ifndef ALGORITHMNDK_AVL_HPP
#define ALGORITHMNDK_AVL_HPP

//平衡二叉搜索树
//比当前小的放左边，比他大的放右边
//中序遍历就是从小到大的排序
//删除操作
//当左右两个节点都不为空的情况下，左和右放上去都不行
//从左边找最大值替代，或者从右边找最小值替代就可以


//插入过程中不断的进行左旋和右旋操作来保证二叉树的平衡
//平衡二叉树，左右高度差不超过1，且左右子树也是一颗平衡二叉树

/**
 *  1.AVL树的定义及意义
 *  定义：左右子树的高度差不超过1
 *  平衡二叉搜索树的意义：普通二叉树会出现极度不平衡的情况，复杂度会退化成O（N）
 *  平衡二叉搜索树可以确保复杂度在O（lgN）基本
 *
 *  2.旋转操作
 *  [3,2,1,4,5,6,7,10,9,8]
 *  1.左旋
 *  2.右旋
 *  3.先右旋再左旋
 *  4.先左旋再右旋
 *
 *  3.插入调整
 *  在新增的过程中不断的进行调整
 */

#include <iostream>
#include <queue>
using namespace std;

template <typename K,typename V>
struct AVLTreeNode{
public:
    AVLTreeNode<K,V>* left = NULL;
    AVLTreeNode<K,V>* right = NULL;
    K key;
    V value;
    int height;//当前树的高度
    AVLTreeNode(K key,V value):height(1){
        this->key = key;
        this->value = value;
    }

    AVLTreeNode(const AVLTreeNode<K,V> * pNode):height(pNode->height){
        this->key = pNode->key;
        this->value = pNode->value;
        this->right = pNode->right;
        this->left = pNode->left;
    }
};

template <typename K,typename V>
class AVL {
    AVLTreeNode<K,V>* root;//根节点
    int count;//个数
public:
    AVL(){
        root = NULL;
        count = 0;
    }

private:

    int getHeight(AVLTreeNode<K,V> * pNode){
        if(pNode == NULL){
            LOGE("树的高度测量");
        }
        return pNode ? pNode->height:0;
    }
    AVLTreeNode<K,V>* addNode(AVLTreeNode<K,V>* pNode,K key,V value){
        if(pNode == NULL){
            count++;
            return new AVLTreeNode<K,V>(key,value);
        }

        if(pNode->key > key){
            pNode->left = addNode(pNode->left,key,value);

            if(getHeight(pNode->left) - getHeight(pNode->right) == 2){
                //调整 有些情况需要先左旋再右旋
                if(getHeight(pNode->left->right) > getHeight(pNode->left->left)){
                    pNode = L_R_Rotation(pNode);
                }else{
                    pNode = R_Rotation(pNode);
                }
            }

        }else if(pNode->key < key){
            pNode->right = addNode(pNode->right,key,value);

            if(getHeight(pNode->right) - getHeight(pNode->left) == 2){
                //调整
                if(getHeight(pNode->right->left) > getHeight(pNode->right->right)){
                    pNode = R_L_Rotation(pNode);
                }else{
                    pNode = L_Rotation(pNode);
                }
            }
        } else{
            pNode->value = value;
        }

        pNode->height = max(getHeight(pNode->left),getHeight(pNode->right)) + 1;
        return pNode;
    }

    //四个旋转操作

    /*
     * 右旋 1.左子结点变成根节点
     *      2.根节点变为左子结点的右节点
     *      3.如果原左子结点的右子结点存在，那么就要变成原根节点的右子结点
     */
    AVLTreeNode<K,V>* R_Rotation(AVLTreeNode<K,V>* pNode){
        AVLTreeNode<K,V>* left = pNode->left;
        AVLTreeNode<K,V>* right = left->right;
        left->right = pNode;
        pNode->left = right;

        //更新树的高度
        pNode->height = std::max(getHeight(pNode->left),getHeight(pNode->right)) + 1;
        left->height = std::max(getHeight(left->left),getHeight(left->right)) + 1;
        return left;
    }

    /*
     * 左旋 1.右子结点变成根节点
     *      2.根节点变为右子结点的左节点
     *      3.如果原右子结点的再左子结点存在，那么就要变成原根节点的左子结点
     */
    AVLTreeNode<K,V>* L_Rotation(AVLTreeNode<K,V>* pNode){
        AVLTreeNode<K,V>* right = pNode->right;
        AVLTreeNode<K,V>* left = right->left;
        right->left = pNode;
        pNode->right = left;

        //更新树的高度
        pNode->height = std::max(getHeight(pNode->left),getHeight(pNode->right)) + 1;
        right->height = std::max(getHeight(right->left),getHeight(right->right)) + 1;
        return right;
    }

    AVLTreeNode<K,V>* L_R_Rotation(AVLTreeNode<K,V>* pNode){
        pNode->left = L_Rotation(pNode->left);
        return R_Rotation(pNode);
    }

    AVLTreeNode<K,V>* R_L_Rotation(AVLTreeNode<K,V>* pNode){
        pNode->right = R_Rotation(pNode->right);
        return L_Rotation(pNode);
    }

    AVLTreeNode<K,V>* findNode(AVLTreeNode<K,V>* pNode,K key){
        if(pNode == NULL){
            return NULL;
        }
        if(pNode->key < key){
            pNode = findNode(pNode->right,key);
        }else if(pNode->key > key){
            pNode = findNode(pNode->left,key);
        }

        return pNode;
    }

public:

    void put(K key,V value){
        root = addNode(root,key,value);
    }

    V get(K key){
        AVLTreeNode<K,V> * pNode = findNode(root,key);
        if(pNode != NULL){
            return pNode->value;
        }else{
            return NULL;
        }
    }



    int size(){
        return count;
    }

    bool contains(K key){
        return false;
    }

    void remove(K key){
        root = removeNode(root,key);
    }


    /**
     * 移除操作比较复杂，需要考虑左旋右旋，不平衡的时候就要判断是不是旋转然后重新计算树的高度
     * @param pNode
     * @param key
     * @return
     */
    AVLTreeNode<K,V> * removeNode(AVLTreeNode<K,V> * pNode,K key){
        if(pNode == NULL){
            return NULL;
        }
        if(pNode->key > key){
            pNode->left = removeNode(pNode->left,key);

            if(getHeight(pNode->right) - getHeight(pNode->left) == 2){
                //调整
                if(getHeight(pNode->right->left) > getHeight(pNode->right->right)){
                    pNode = R_L_Rotation(pNode);
                }else{
                    pNode = L_Rotation(pNode);
                }
            }
        }else if(pNode->key < key){
            pNode->right = removeNode(pNode->right,key);

            if(getHeight(pNode->left) - getHeight(pNode->right) == 2){
                //调整 有些情况需要先左旋再右旋
                if(getHeight(pNode->left->right) > getHeight(pNode->left->left)){
                    pNode = L_R_Rotation(pNode);
                }else{
                    pNode = R_Rotation(pNode);
                }
            }
        } else{
            count--;
            if(pNode->left == NULL && pNode->right == NULL){
                delete pNode;
                return NULL;
            }else if(pNode->left == NULL){
                AVLTreeNode<K,V> * treeNode = pNode->right;
                delete pNode;
                return treeNode;
            }else if(pNode->right == NULL){
                AVLTreeNode<K,V> * treeNode = pNode->left;
                delete pNode;
                return treeNode;
            }else{
                //左右子树都不为空

                if(getHeight(pNode->left) > getHeight(pNode->right)){
                    //去左边找
                    AVLTreeNode<K,V> * max = maximum(pNode->left);
                    AVLTreeNode<K,V> *successor = new AVLTreeNode<K,V>(max);
                    //保证移除的子结点高度都有更新
//                successor->left = deleteMax(pNode->left);
                    successor->left = removeNode(pNode->left,max->key);
                    count++;
                    successor->right = pNode->right;
                    delete pNode;

                    //更新当前后继的高度
                    successor->height = std::max(getHeight(successor->left),getHeight(successor->right))  + 1;
                    return successor;
                }else{
                    //去右边找
                    AVLTreeNode<K,V> * min = minimum(pNode->right);
                    AVLTreeNode<K,V> *successor = new AVLTreeNode<K,V>(min);
                    //保证移除的子结点高度都有更新
//                successor->left = deleteMax(pNode->left);
                    successor->right = removeNode(pNode->right,min->key);
                    count++;
                    successor->left = pNode->left;
                    delete pNode;
                    pNode = successor;
                }


            }
        }
        pNode->height = max(getHeight(pNode->left),getHeight(pNode->right)) + 1 ;
        return pNode;
    }

    AVLTreeNode<K,V> * deleteMax(AVLTreeNode<K,V> * pNode){
        if(pNode->right == NULL){
            AVLTreeNode<K,V> * left = pNode->left;
            delete pNode;
            count--;
            return left;
        }
        pNode->right = deleteMax(pNode->right);
        return pNode;
    }

    //查找当前树的最大值
    AVLTreeNode<K,V> * maximum(AVLTreeNode<K,V> * pNode){
        if(pNode->right == NULL){
            return pNode;
        }
        return maximum(pNode->right);
    }

    //查找当前树的最小值
    AVLTreeNode<K,V> * minimum(AVLTreeNode<K,V> * pNode){
        if(pNode->left == NULL){
            return pNode;
        }
        return minimum(pNode->left);
    }

    void inOrderTraverse(void(*fun)(K,V)){
        inOrderTraverse_(root,fun);
    }

    void inOrderTraverse_(AVLTreeNode<K,V> * pNode,void(*fun)(K,V)){
        if(pNode == NULL)
            return;

        inOrderTraverse_(pNode->left,fun);
        fun(pNode->key,pNode->value);
        inOrderTraverse_(pNode->right,fun);
    }

    void levelOrderTraverse(void(*fun)(K,V)){
        if(root == NULL){
            return;
        }
       queue<AVLTreeNode<K,V>*> queue1;
        AVLTreeNode<K,V>* node = root;
        queue1.push(node);

       while (!queue1.empty()){
           AVLTreeNode<K,V>* tempNode = queue1.front();
           fun(tempNode->key,tempNode->value);

           queue1.pop();

           if(tempNode->left){
               queue1.push(tempNode->left);
           }

           if(tempNode->right){
               queue1.push(tempNode->right);
           }
       }
    }

};


#endif //ALGORITHMNDK_BST_HPP
