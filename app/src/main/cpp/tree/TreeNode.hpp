//
// Created by sky on 20-12-29.
//

#ifndef ALGORITHMNDK_TREENODE_HPP
#define ALGORITHMNDK_TREENODE_HPP
template <typename T>
class TreeNode{
public:
    TreeNode(T data,TreeNode<T> *left,TreeNode<T> *right):data(data),left(left),right(right){

    }

    T data;
    TreeNode<T> *left = NULL;
    TreeNode<T> *right = NULL;
};
#endif //ALGORITHMNDK_TREENODE_HPP
