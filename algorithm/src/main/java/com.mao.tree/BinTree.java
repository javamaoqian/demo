package com.mao.tree;

import java.util.Stack;

/**
 * @Author: mq
 * @Date: 2021/5/11 13:42
 */
public class BinTree {
    /** 非递归前序遍历数 */
    public void pre(Node root) {
        if (root == null) {
            return;
        }
        Stack<Node> toBeUsed = new Stack<>();
        Node nowIndex = root;
        while (true) {
            System.out.println(nowIndex);
            while (nowIndex.left != null) {
                System.out.println(nowIndex.left);
                if (nowIndex.right != null) {
                    toBeUsed.push(nowIndex.right);
                }
                nowIndex = nowIndex.left;
            }
            if (nowIndex.right != null) {
                toBeUsed.push(nowIndex.right);
            }
            if (toBeUsed.empty()) {
                break;
            }
            nowIndex = toBeUsed.pop();
        }
    }

    /** 递归前序遍历树 */
    public void preRecursion(Node root) {
        System.out.println(root);
        if (root.left != null) {
            preRecursion(root.left);
        }
        if (root.right != null) {
            pre(root.right);
        }
    }



    public static void main(String[] args) {
        new BinTree().pre(createTree());
        System.out.println();
        new BinTree().preRecursion(createTree());
    }


    public static Node createTree() {
        Node root = new Node("1");
        root.left = new Node("4");
        root.right = new Node("5");
        root.right.left = new Node("7");
        root.right.right = new Node("9");
        root.left.right = new Node("120");
        root.left.right.right = new Node("88");
        return root;
    }

    static class Node {
        private String value;
        private Node left;
        private Node right;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value='" + value + '\'' +
                    '}';
        }
    }
}
