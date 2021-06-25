package com.data.structure.searchtable.dynamicsearchtable.impl;

import com.data.structure.searchtable.model.Element;
import com.data.structure.tree.enums.BalanceFactor;
import com.data.structure.tree.impl.binarytree.BalanceBinarySortTreeImpl;
import com.data.structure.tree.model.binarytreenode.BalanceTreeNode;
import java.util.List;

/**
 * BalanceBinarySortTreeSearchTableImpl:
 * 平衡二叉排序树(递归插入)
 *
 * @author sunchen
 * @date 2020/6/28 10:42 上午
 */
public class BalanceBinarySortTreeSearchTableImpl extends BinarySortTreeSearchTableImpl{
    /**
     * 平衡树
     */
    public BalanceBinarySortTreeImpl tree = new BalanceBinarySortTreeImpl();

    /**
     * 标识树是否长高
     */
    private boolean tallier;

    /**
     * 插入
     * @param e
     * @return
     */
    @Override
    public boolean insert(Element e) {
        BalanceTreeNode node = insertAvl(tree.node, e);
        if (node != null) {
            tree.node = node;
        }
        return true;
    }

    /**
     * 递归插入
     * @return
     */
    public BalanceTreeNode insertAvl (BalanceTreeNode node, Element e) {
        //1. node为null
        if (node == null) {
            //插入新结点
            node = new BalanceTreeNode(e);
            tallier = true;
            return node;
        }
        //2. 如果要插入的key和node的key相等，则不插入
        if (((Element)(node.data)).equalKey(e.getKey()) == 0) {
            return null;
        }
        //3. 如果要插入的比node的key小
        else if (((Element)node.data).equalKey(e.getKey()) > 0) {
            BalanceTreeNode lChild = insertAvl(node.lChild, e);
            if (lChild == null){
                return null;
            }
            node.lChild = lChild;
            //3.2 如果插成功了，则需要判断子树的长高对自己是否有影响
            if (tallier) {
                switch (node.bf){

                    //如果原来是左右一样高，则现在为左高
                    case EH:
                        node.bf = BalanceFactor.LH;
                        tallier = true;
                        break;

                    //如果原来是左高，则现在需要左平衡
                    case LH:
                        node = tree.leftBalance(node);
                        tallier = false;
                        break;

                    //如果原来是右高
                    case RH:
                        node.bf = BalanceFactor.EH;
                        tallier = false;
                        break;
                    default:
                }
            }
        } else {
            //4. 如果要插入的比node的key大
            BalanceTreeNode rChild = insertAvl(node.rChild, e);
            if (rChild == null) {
                return null;
            }
            node.rChild = rChild;
            //判断右子树长高，对自己的高度是否有影响
            if (tallier) {
                switch (node.bf) {

                    //原来是左右一样高
                    case EH:
                        node.bf = BalanceFactor.RH;
                        tallier = true;
                        break;

                    //原来是左高
                    case LH:
                        node.bf = BalanceFactor.EH;
                        tallier = false;
                        break;

                    //原来是右高，则触发右平衡
                    case RH:
                        node = tree.rightBalance(node);
                        tallier = false;
                        break;
                    default:
                }
            }

        }
        return node;
    }

    @Override
    public void traverse() {
        values.clear();
        tree.inOrderTraverse();
        List<Object> datas = tree.datas;
        for (Object data : datas) {
            visit((Element) data);
        }
    }
}
