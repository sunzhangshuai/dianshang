package com.data.basics.design.composite;

import java.util.List;

/**
 * Test:
 *
 * @author sunchen
 * @date 2020/7/16 9:20 下午
 */
public class Test {
    public static void main(String[] args) {
        BranchNode root = new BranchNode("root");
        BranchNode chapter1 = new BranchNode("第一章");
        BranchNode chapter2 = new BranchNode("第二章");
        LeafNode sector11 = new LeafNode("第一节");
        LeafNode sector12 = new LeafNode("第二节");
        LeafNode sector21 = new LeafNode("第一节");
        LeafNode sector22 = new LeafNode("第二节");
        root.add(chapter1);
        root.add(chapter2);
        chapter1.add(sector11);
        chapter1.add(sector12);
        chapter2.add(sector21);
        chapter2.add(sector22);
        tree(root);
    }

    public static void tree(Node root) {
        visit(root);
        if (root instanceof BranchNode) {
            BranchNode node = (BranchNode) root;
            List<Node> childs = node.child;
            for (Node child : childs) {
                tree(child);
            }
        }
    }

    public static void visit(Node node) {
        System.out.println(node.name);
    }
}
