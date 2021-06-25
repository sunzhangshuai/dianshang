package com.data.structure.tree.impl.binarytree;

import com.data.structure.tree.enums.BalanceFactor;
import com.data.structure.tree.model.binarytreenode.BalanceTreeNode;
import com.data.structure.tree.model.binarytreenode.BinaryTreeNode;

/**
 * BalanceBinarySortTreeImpl:
 * 平衡二叉树
 *
 * @author sunchen
 * @date 2020/6/28 10:59 上午
 */
public class BalanceBinarySortTreeImpl extends BinarySortTreeImpl {

    /**
     * 标识树是否长高
     */
    private boolean tallier;

    public BalanceTreeNode node;

    public int direction;

    /**
     * 标识树是否
     */
    public boolean low;

    public void setRoot(BalanceTreeNode node) {
        this.node = node;
        node.bf = BalanceFactor.EH;
        tallier = true;
    }

    public void insertChild(BalanceTreeNode binaryTreeNode, int childType, BalanceTreeNode newNode) {
        if (childType == 0) {
            binaryTreeNode.lChild = newNode;
        } else {
            binaryTreeNode.rChild = newNode;
        }
        newNode.bf = BalanceFactor.EH;
        //判断是否需要平衡
        insertBalance(newNode);
    }

    public void deleteRoot(BalanceTreeNode root) {
        // 如果孩子是null，不做任何处理
        if (root == null) {
            return;
        }
        if (root.lChild == null && root.rChild == null) {
            node = null;
            return;
        } else {
           deleteNode(root);
        }
        deleteBalance();
    }

    public void deleteChild(BalanceTreeNode binaryTreeNode, int childType) {
        //1.获取要删除结点
        BalanceTreeNode node = childType == 0 ? binaryTreeNode.lChild : binaryTreeNode.rChild;
        if (node == null) {
            return;
        }
        //2.如果要删除结点是原子
        if (binaryTreeNode.lChild == null && binaryTreeNode.rChild == null) {
            if (childType == 0) {
                binaryTreeNode.lChild = null;
            } else {
                binaryTreeNode.rChild = null;
            }
        } else {
            deleteNode(node);
        }
        deleteBalance();
    }

    public void insertBalance(BalanceTreeNode newNode) {
        tallier = true;
        BalanceTreeNode childNode = newNode;
        while (!stack.stackEmpty() && tallier) {
            BalanceTreeNode parent = (BalanceTreeNode) stack.pop();
            if (parent.lChild == childNode) {
                switch (parent.bf) {
                    case EH:
                        parent.bf = BalanceFactor.LH;
                        tallier = true;
                        break;
                    case LH:
                        if (stack.stackEmpty()) {
                            this.node = leftBalance(parent);
                        } else {
                            BalanceTreeNode grandParent = (BalanceTreeNode) stack.getTop();
                            if (grandParent.lChild == parent) {
                                grandParent.lChild = leftBalance(parent);
                            } else {
                                grandParent.rChild = leftBalance(parent);
                            }
                        }
                        tallier = false;
                        break;
                    case RH:
                        parent.bf = BalanceFactor.EH;
                        tallier = false;
                        break;
                    default:
                }
            } else {
                switch (parent.bf) {
                    case EH:
                        parent.bf = BalanceFactor.RH;
                        tallier = true;
                        break;
                    case LH:
                        parent.bf = BalanceFactor.EH;
                        tallier = false;
                        break;
                    case RH:
                        if (stack.stackEmpty()) {
                            this.node = rightBalance(parent);
                        } else {
                            BalanceTreeNode grandParent = (BalanceTreeNode) stack.getTop();
                            if (grandParent.lChild == parent) {
                                grandParent.lChild = rightBalance(parent);
                            } else {
                                grandParent.rChild = rightBalance(parent);
                            }
                        }
                        tallier = false;
                        break;
                    default:
                }
            }
            childNode = parent;
        }
    }

    private void deleteBalance() {
        low = true;
        while (!stack.stackEmpty() && low) {
            BalanceTreeNode node = (BalanceTreeNode) stack.pop();
            switch (this.direction) {
                //删除node节点的左子树的节点
                case 0:
                    switch (node.bf) {
                        case LH:
                            node.bf = BalanceFactor.EH;
                            low = true;
                            break;
                        case EH:
                            node.bf = BalanceFactor.RH;
                            low = false;
                            break;
                        case RH:
                            if (stack.stackEmpty()) {
                                this.node = rightBalance(node);
                            } else {
                                BalanceTreeNode parent = (BalanceTreeNode) stack.getTop();
                                if (parent.lChild == node) {
                                    parent.lChild = rightBalance(node);
                                } else {
                                    parent.rChild = rightBalance(node);
                                }
                            }
                            low = true;
                            break;
                        default:
                    }
                    //删除node节点的右子树的节点
                case 1:
                    switch (node.bf) {
                        case LH:
                            if (stack.stackEmpty()) {
                                this.node = leftBalance(node);
                            } else {
                                BalanceTreeNode parent = (BalanceTreeNode) stack.getTop();
                                if (parent.lChild == node) {
                                    parent.lChild = leftBalance(node);
                                } else {
                                    parent.rChild = leftBalance(node);
                                }
                            }
                            low = true;
                            break;
                        case EH:
                            node.bf = BalanceFactor.LH;
                            low = false;
                            break;
                        case RH:
                            node.bf = BalanceFactor.EH;
                            low = true;
                            break;
                        default:
                    }
                default:
            }
            if (!stack.stackEmpty()) {
                BalanceTreeNode parent = (BalanceTreeNode) stack.getTop();
                if (parent.lChild == node) {
                    direction = 0;
                }else {
                    direction = 1;
                }
            }
        }
    }

    /**
     * 删除结点（非原子结点）
     *
     * @param node
     */
    private void deleteNode(BalanceTreeNode node) {
        if (node.rChild == null) {
            direction = 0;
            node.data = node.lChild.data;
            node.rChild = node.lChild.rChild;
            node.lChild = node.lChild.rChild;
        } else if (node.lChild == null) {
            direction = 0;
            node.data = node.rChild.data;
            node.rChild = node.rChild.rChild;
            node.lChild = node.rChild.rChild;
        } else {
            stack.push(node);
            switch (node.bf) {
                case LH:
                case EH:
                    BalanceTreeNode lc = node.lChild;
                    BalanceTreeNode pre = node;
                    while (lc.rChild != null) {
                        pre = lc;
                        stack.push(pre);
                        lc = lc.rChild;
                    }
                    if (pre == node) {
                        direction = 0;
                        node.lChild = lc.lChild;
                    } else {
                        direction = 1;
                        pre.rChild = lc.lChild;
                    }
                    node.data = lc.data;
                    break;
                case RH:
                    BalanceTreeNode rc = node.rChild;
                    BalanceTreeNode rPre = node;
                    while (rc.lChild != null) {
                        rPre = rc;
                        stack.push(rPre);
                        rc = rc.lChild;
                    }
                    if (rPre == node) {
                        node.rChild = rc.rChild;
                        direction = 1;
                    } else {
                        rPre.lChild = rc.rChild;
                        direction = 0;
                    }
                    node.data = rc.data;
                    break;
                default:
            }
        }
    }

    /**
     * 左平衡
     *
     * @param node
     */
    public BalanceTreeNode leftBalance(BalanceTreeNode node) {
        BalanceTreeNode lChild = node.lChild;
        switch (lChild.bf) {
            //LL型
            case LH:
                node.bf = lChild.bf = BalanceFactor.EH;
                node = rightRotate(node);
                break;
            //LR型
            case RH:
                BalanceTreeNode rd = lChild.rChild;
                switch (rd.bf) {
                    //插在了lChild的右子树的左孩子
                    case LH:
                        node.bf = BalanceFactor.RH;
                        lChild.bf = BalanceFactor.EH;
                        break;
                    //插在了lChild的右子树的根部
                    case EH:
                        node.bf = lChild.bf = BalanceFactor.EH;
                        break;
                    //插在了lChild的右子树的右孩子
                    case RH:
                        node.bf = BalanceFactor.EH;
                        lChild.bf = BalanceFactor.LH;
                        break;
                    default:
                }
                rd.bf = BalanceFactor.EH;
                node.lChild = leftRotate(lChild);
                node = rightRotate(node);
                break;
            default:
        }
        return node;
    }

    /**
     * 右平衡
     *
     * @param node
     */
    public BalanceTreeNode rightBalance(BalanceTreeNode node) {
        BalanceTreeNode rChild = node.rChild;
        switch (rChild.bf) {
            //RL型
            case LH:
                BalanceTreeNode ld = rChild.lChild;
                switch (ld.bf) {
                    //插在了rChild左子树的根
                    case EH:
                        node.bf = rChild.bf = BalanceFactor.EH;
                        break;
                    //插在了rChild左子树的左边
                    case LH:
                        node.bf = BalanceFactor.EH;
                        rChild.bf = BalanceFactor.RH;
                        break;
                    //插在了rChild左子树的右边
                    case RH:
                        node.bf = BalanceFactor.LH;
                        rChild.bf = BalanceFactor.EH;
                        break;
                    default:
                }
                ld.bf = BalanceFactor.EH;
                node.rChild = rightRotate(rChild);
                node = leftRotate(node);
                break;
            // RR型
            case RH:
                node.bf = rChild.bf = BalanceFactor.EH;
                node = leftRotate(node);
                break;
            default:
        }
        return node;
    }

    /**
     * 左旋
     *
     * @param node
     */
    public BalanceTreeNode leftRotate(BalanceTreeNode node) {
        BalanceTreeNode rChild = node.rChild;
        node.rChild = rChild.lChild;
        rChild.lChild = node;
        return rChild;
    }

    /**
     * 右旋
     *
     * @param node
     */
    public BalanceTreeNode rightRotate(BalanceTreeNode node) {
        BalanceTreeNode lChild = node.lChild;
        node.lChild = lChild.rChild;
        lChild.rChild = node;
        return lChild;
    }

    @Override
    public void inOrderTraverse() {
        in(node);
    }

    public void in(BalanceTreeNode node) {
        if (node == null) {
            return;
        }
        in(node.lChild);
        visit(node.data);
        in(node.rChild);
    }
}
