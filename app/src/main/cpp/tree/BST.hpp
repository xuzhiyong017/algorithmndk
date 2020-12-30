//
// Created by sky on 20-12-30.
//

#ifndef ALGORITHMNDK_BST_HPP
#define ALGORITHMNDK_BST_HPP

//二叉搜索树
//比当前小的放左边，比他大的放右边
//中序遍历就是从小到大的排序
//删除操作
//当左右两个节点都不为空的情况下，左和右放上去都不行
//从左边找最大值替代，或者从右边找最小值替代就可以

template <typename K,typename V>
struct BSTreeNode{
public:
    BSTreeNode<K,V>* left = NULL;
    BSTreeNode<K,V>* right = NULL;
    K key;
    V value;
    BSTreeNode(K key,V value){
        this->key = key;
        this->value = value;
    }

    BSTreeNode(const BSTreeNode<K,V> * pNode){
        this->key = pNode->key;
        this->value = pNode->value;
        this->right = pNode->right;
        this->left = pNode->left;
    }
};

template <typename K,typename V>
class BST {
    BSTreeNode<K,V>* root;//根节点
    int count;//个数
public:
    BST(){
        root = NULL;
        count = 0;
    }

private:
    BSTreeNode<K,V>* addNode(BSTreeNode<K,V>* pNode,K key,V value){
        if(pNode == NULL){
            count++;
            return new BSTreeNode<K,V>(key,value);
        }

        if(pNode->key > key){
            pNode->left = addNode(pNode->left,key,value);
        }else if(pNode->key < key){
            pNode->right = addNode(pNode->right,key,value);
        } else{
            pNode->value = value;
        }

        return pNode;
    }
    BSTreeNode<K,V>* findNode(BSTreeNode<K,V>* pNode,K key){
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
        BSTreeNode<K,V> * pNode = findNode(root,key);
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
    BSTreeNode<K,V> * removeNode(BSTreeNode<K,V> * pNode,K key){
        if(pNode == NULL){
            return NULL;
        }
        if(pNode->key > key){
            pNode->left = removeNode(pNode->left,key);
        }else if(pNode->key < key){
            pNode->right = removeNode(pNode->right,key);
        } else{
            count--;
            if(pNode->left == NULL && pNode->right == NULL){
                delete pNode;
                return NULL;
            }else if(pNode->left == NULL){
                BSTreeNode<K,V> * treeNode = pNode->right;
                delete pNode;
                return treeNode;
            }else if(pNode->right == NULL){
                BSTreeNode<K,V> * treeNode = pNode->left;
                delete pNode;
                return treeNode;
            }else{
                //左右子树都不为空
                BSTreeNode<K,V> *successor = new BSTreeNode<K,V>(maximum(pNode->left));
                successor->left = deleteMax(pNode->left);
                count++;
                successor->right = pNode->right;
                delete pNode;
                return successor;
            }
        }
        return pNode;
    }

    BSTreeNode<K,V> * deleteMax(BSTreeNode<K,V> * pNode){
        if(pNode->right == NULL){
            BSTreeNode<K,V> * left = pNode->left;
            delete pNode;
            count--;
            return left;
        }
        pNode->right = deleteMax(pNode->right);
        return pNode;
    }

    //查找当前树的最大值
    BSTreeNode<K,V> * maximum(BSTreeNode<K,V> * pNode){
        if(pNode->right == NULL){
            return pNode;
        }
        return maximum(pNode->right);
    }

    void inOrderTraverse(void(*fun)(K,V)){
        inOrderTraverse_(root,fun);
    }

    void inOrderTraverse_(BSTreeNode<K,V> * pNode,void(*fun)(K,V)){
        if(pNode == NULL)
            return;

        inOrderTraverse_(pNode->left,fun);
        fun(pNode->key,pNode->value);
        inOrderTraverse_(pNode->right,fun);
    }

};


#endif //ALGORITHMNDK_BST_HPP
