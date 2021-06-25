package com.data.structure.tree.impl.op;

import com.data.structure.stack.impl.SequenceStackImpl;
import com.data.structure.tree.impl.binarytree.StackTraverseBinaryTreeImpl;
import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

/**
 * StackTraverseBinaryTreeExtends:
 *
 * @author sunchen
 * @date 2020/8/29 8:28 下午
 */
public class StackTraverseBinaryTreeExtends extends StackTraverseBinaryTreeImpl {

    public StackTraverseBinaryTreeExtends(int[] data) {
        super(data);
    }
    public SequenceStackImpl stack = new SequenceStackImpl();
    public SequenceStackImpl countStack = new SequenceStackImpl();

    /**
     * 获取带权路径长度
     * @return
     */
    public int getWpl(){
        int sum = 0;
        int count = 0;
        while (node != null) {
            if (node.rChild != null) {
                stack.push(node.rChild);
                countStack.push(count+1);
            }
            if (node.lChild != null) {
                node = node.lChild;
                count++;
            } else {
                if (node.rChild == null) {
                    //说明是叶子结点，需要计算WPL
                    sum += (int)node.data*count;
                }
                //出栈
                if (stack.stackEmpty()) {
                   break;
                }
                node = (BinaryTreeNode) stack.pop();
                count = (int) countStack.pop();
            }
        }
        return sum;
    }
}
