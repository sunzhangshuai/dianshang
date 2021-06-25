package com.data.structure.tree.impl.binarytree;

import com.data.structure.queue.impl.SequenceQueueImpl;
import com.data.structure.stack.impl.SequenceStackImpl;
import com.data.structure.tree.interfaces.BinaryTree;
import com.data.structure.tree.model.binarytreenode.AbstractTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * AbstractBinaryTree:
 *
 * @author sunchen
 * @date 2020/6/8 12:26 上午
 */
public abstract class AbstractBinaryTree<T extends AbstractTreeNode> implements BinaryTree<T> {

    public T node;

    /**
     * 栈
     */
    public SequenceStackImpl stack = new SequenceStackImpl();

    /**
     * 队列
     */
    public SequenceQueueImpl queue = new SequenceQueueImpl();

    public List<Object> datas = new ArrayList<Object>();

    @Override
    abstract public void clearBinaryTree();

    @Override
    public boolean isEmpty() {
        return getRoot() == null;
    }

    @Override
    abstract public int binaryTreeDepth();

    @Override
    abstract public T getRoot();

    @Override
    public Object value(T t) {
        return t.data;
    }

    @Override
    public void assign(T t, Object value) {
        t.data = value;
    }

    @Override
    abstract public T parent(T e);

    @Override
    abstract public T leftChild(T e);

    @Override
    abstract public T rightChild(T e);

    @Override
    abstract public T leftSibling(T e);

    @Override
    abstract public T rightSibling(T e);

    @Override
    abstract public void insertChild(T t, int childType, T e);

    @Override
    abstract public T deleteChild(T t, int childType);

    @Override
    abstract public void preOrderTraverse();

    @Override
    abstract public void inOrderTraverse();

    @Override
    abstract public void postOrderTraverse();

    @Override
    abstract public void levelOrderTraverse();

    public void clear() {
        datas = new ArrayList<Object>();
    }

    public <E> void visit(E data) {
        datas.add(data);
    }
}
