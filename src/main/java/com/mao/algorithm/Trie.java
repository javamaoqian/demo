package com.mao.algorithm;

import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mq
 * @Date: 2020/12/4 11:16
 */
public class Trie {

    private TreeNode root;

    public Trie() {
        this.root = new TreeNode(null, 0);
    }


    public void insert(String s) {
        TreeNode parent = root;
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(i, i + 1);
            if (i == s.length() - 1) {
                parent = addChild(parent, tmp, true);
            } else {
                parent = addChild(parent, tmp, false);
            }
        }
    }

    public boolean search(String s) {
        TreeNode parent = root;
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(i, i + 1);
            if ((parent = findChild(parent, tmp)) == null) {
                return false;
            }
            if (i == s.length() - 1) {
                if (parent.getCount() < 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean startWith(String s) {
        TreeNode parent = root;
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(i, i + 1);
            if ((parent = findChild(parent, tmp)) == null) {
                return false;
            }
        }
        return true;
    }

    public void printTree(TreeNode root) {
        if (root.childs != null) {
            System.out.println();
            for (TreeNode node : root.childs.values()) {
                printTree(node);
            }
        }
        System.out.print(root.getValue() + "_" + root.getCount());
    }

    public void printTree2(TreeNode root, boolean isRoot) {
        if (isRoot) {
            System.out.println(root.getValue() + "_" + root.getCount());
        }
        if (root.childs != null) {
            for (TreeNode child : root.childs.values()) {
                System.out.print(child.getValue() + "_" + child.getCount() + "    ");
                printTree2(child, false);
            }
            System.out.println();
        }
    }

    private TreeNode findChild(TreeNode parent, String s) {
        Map<String, TreeNode> childs = parent.getChilds();
        if (childs == null) {
            return null;
        }
        return childs.get(s);
    }

    private TreeNode addChild(TreeNode node, String c, boolean last) {
        Map<String, TreeNode> childs = node.getChilds();
        if (childs == null) {
            childs = new HashMap<>(16);
            node.setChilds(childs);
        }
        TreeNode res;
        if ((res = childs.get(c)) != null) {
            if (last) {
                res.setCount(res.getCount() + 1);
            }
            return res;
        }
        if (last) {
            res = new TreeNode(c, 1);
        } else {
            res = new TreeNode(c, 0);
        }

        childs.put(c, res);
        return res;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    class TreeNode {
        private String value;
        private Map<String, TreeNode> childs;

        private Integer count;

        public TreeNode(String value, Integer count) {
            this.value = value;
            this.count = count;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Map<String, TreeNode> getChilds() {
            return childs;
        }

        public void setChilds(Map<String, TreeNode> childs) {
            this.childs = childs;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}
