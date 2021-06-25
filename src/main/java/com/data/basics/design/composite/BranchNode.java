package com.data.basics.design.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * BranchNode:
 *
 * @author sunchen
 * @date 2020/7/16 9:17 下午
 */
public class BranchNode extends Node{
    public List<Node> child = new ArrayList<Node>();
    public BranchNode(String name){
        super(name);
    }
    public void add(Node node){
        child.add(node);
    }
}
