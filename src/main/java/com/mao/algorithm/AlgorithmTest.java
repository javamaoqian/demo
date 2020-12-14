package com.mao.algorithm;

import java.util.LinkedList;

/**
 * @Author: mq
 * @Date: 2020/12/3 16:37
 */
public class AlgorithmTest {
    public static void main(String[] args) {
        predictPartyVictory("RDD");

    }

    public static void testTrie() {
        Trie t = new Trie();
        t.insert("ssstresesmnglif");
        t.insert("lkfslskflhdslhf");
        t.insert("application");
        t.insert("app");
        System.out.println(t.search("ap"));
        System.out.println(t.search("ssstresesmnglif"));
        System.out.println(t.startWith("ap"));
        System.out.println(t.search("app"));
        t.printTree2(t.getRoot(), true);
    }

    public void revNode1() {
        Node node = new Node(1);
        node.next(new Node(2)).next(new Node(3)).next(new Node(4)).next(new Node(5));
        print(node);
        Node node1 = revNode(node);
        print(node1);
    }

    public static Node revNode(Node node) {
        if (node == null) {
            return node;
        }
        Node tail = node;
        Node next;
        while ((next = tail.next) != null) {
            tail.next = next.next;
            next.next = node;
            node = next;
        }
        return node;
    }

    static class Node {
        private Integer value;
        private Node next;

        public Node(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node next(Node node) {
            this.next = node;
            return node;
        }
    }

    public static String print(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append(node.value + "->");
        while (node.next != null) {
            node = node.next;
            sb.append(node.value + "->");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    public boolean lemonadeChange(int[] bills) {
        if (bills.length < 1) {
            return true;
        }
        int fiveCount = 1;
        int tenCount = 0;
        if (bills[0] != 5) {
            return false;
        }
        for (int i = 1; i < bills.length; i++) {
            int bill = bills[i];
            if (bill - 5 > i * 5) {
                return false;
            }
            if (bill == 5) {
                fiveCount++;
            } else if (bill == 10) {
                fiveCount--;
                tenCount++;
                if (fiveCount < 0) {
                    return false;
                }
            } else {
                if (tenCount > 0) {
                    tenCount--;
                    fiveCount--;
                } else {
                    fiveCount = fiveCount - 3;
                }
                if (fiveCount < 0) {
                    return false;
                }
            }
        }
        return true;
    }

   /* public String predictPartyVictory(String senate) {
        int length = senate.length();
        String[] rd = senate.split("");
        int[] outIndex = new int[length - 1];


        return "";
    }*/

    public static String predictPartyVictory(String senate) {
        int rCount = 0;
        int dCount = 0;
        SimpleCircleLinkList<String> rd = new SimpleCircleLinkList<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'D') {
                dCount++;
                rd.addLast("D");
            } else {
                rCount++;
                rd.addLast("R");
            }
        }
        SimpleCircleLinkList<String>.Node<String> pre = rd.getHead();
        if (dCount == 0) {
            return "Radiant";
        } else if (rCount == 0) {
            return "Dire";
        } else {
            while (true) {
                SimpleCircleLinkList<String>.Node<String> next = pre.getNext();
                if (!pre.getData().equals(next.getData())) {
                    if (next.getData().equals("D")) {
                        dCount--;
                        if (dCount == 0) {
                            return "Radiant";
                        }
                    } else {
                        rCount--;
                        if (rCount == 0) {
                            return "Dire";
                        }
                    }
                    pre.next = next.next;
                    pre = next.next;
                } else {
                    pre = pre.next;
                }
            }
        }
    }


    public static String predictPartyVictor2(String senate) {
        int rCount = 0;
        int dCount = 0;
        SimpleCircleLinkList<String> rd = new SimpleCircleLinkList<>();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'D') {
                dCount++;
                rd.addLast("D");
            } else {
                rCount++;
                rd.addLast("R");
            }
        }
        if (dCount == 0) {
            return "Radiant";
        } else if (rCount == 0) {
            return "Dire";
        } else {
            SimpleCircleLinkList<String>.Node<String> pre = rd.getHead();
            while (true) {
                SimpleCircleLinkList<String>.Node<String> p = pre;
                SimpleCircleLinkList<String>.Node<String> n = pre.next;
                while (n != null) {
                    if (!n.getData().equals(pre.getData())) {
                        if (n.getData().equals("D")) {
                            dCount--;
                            if (dCount == 0) {
                                return "Radiant";
                            }
                        } else {
                            rCount--;
                            if (rCount == 0) {
                                return "Dire";
                            }
                        }
                        p.next = n.next;
                        break;
                    }
                    p = n;
                    n = n.next;
                }
                pre = pre.next;
            }
        }
    }

    static class SimpleCircleLinkList<T> {
        private Node<T> head;
        private Node<T> tail;

        public void addLast(T t) {
            Node<T> n = new Node<>(t);
            if (tail == null) {
                head = tail = n;
            } else {
                tail.next = n;
                tail = n;
                n.next = head;
            }
        }

        public Node<T> getHead() {
            return head;
        }

        public void setHead(Node head) {
            this.head = head;
        }

        public Node<T> getTail() {
            return tail;
        }

        public void setTail(Node tail) {
            this.tail = tail;
        }

        class Node<T> {
            private T data;
            private Node<T> next;

            public Node(T data) {
                this.data = data;
            }

            public T getData() {
                return data;
            }

            public void setData(T data) {
                this.data = data;
            }

            public Node<T> getNext() {
                return next;
            }

            public void setNext(Node<T> next) {
                this.next = next;
            }
        }
    }


    public static String predictPartyVictor3(String senate) {
        LinkedList<Integer> r = new LinkedList();
        LinkedList<Integer> d = new LinkedList();
        for (int i = 0; i < senate.length(); i++) {
            if (senate.charAt(i) == 'D') {
                d.addLast(i);
            } else {
                r.addLast(i);
            }
        }
        if (r.size() == 0) {
            return "Dire";
        } else if (d.size() == 0) {
            return "Radiant";
        } else {
            while (true) {
                if (r.getFirst() > d.getFirst()) {
                    r.removeFirst();
                    d.addLast(d.getFirst() + senate.length());
                    d.removeFirst();
                } else {
                    d.removeFirst();
                    r.addLast(r.getFirst() + senate.length());
                    r.removeFirst();
                }

                if (r.size() == 0) {
                    return "Dire";
                } else if (d.size() == 0) {
                    return "Radiant";
                }
            }
        }
    }
}
