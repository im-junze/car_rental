package com.coder.auto_rental;



import java.io.FileNotFoundException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {
    static class Tree {
        int data;
        Tree left;
        Tree right;

        public Tree() {
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
       Scanner sc = new Scanner(System.in);
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;
            int m = sc.nextInt();
            int[] t = new int[n];
            for (int i = 0; i < n; i++) {
                t[i] = sc.nextInt();
            }
            Tree target = bulit(t);
            int[] t2 = new int[n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    t2[j] = sc.nextInt();
                }
                Tree bulit = bulit(t2);
                boolean b = cpm(target, bulit);
                if (b) {
                    System.out.println("Yes");
                } else {
                    System.out.println("No");
                }
            }
            ;
        }
    }

    public static boolean cpm(Tree n1, Tree n2) {
        if (n1 == null || n2 == null) return false;
        if (n1.data != n2.data) return false;
        Deque<Tree> deque1 = new LinkedList<>();
        Deque<Tree> deque2 = new LinkedList<>();
        deque1.addLast(n1);
        deque2.addLast(n2);
        while (!deque1.isEmpty()) {
            Tree t1 = deque1.pollFirst();
            Tree t2 = deque2.pollFirst();
            if (t1.data != t2.data) return false;
            if (t1.left != null) {
                deque1.addLast(t1.left);
            }
            if (t1.right != null) {
                deque1.addLast(t1.right);
            }
            if (t2.left != null) {
                deque2.addLast(t2.left);
            }
            if (t2.right != null) {
                deque2.addLast(t2.right);
            }
        }
        return true;
    }

    public static Tree bulit(int... arr) {
        Tree tree = new Tree();
        if (arr == null) {
            return tree;
        } else if (arr.length == 1) {
            return tree;
        }
        tree.data = arr[0];
        for (int i = 1; i < arr.length; i++) {
            bulitTree(tree, tree, arr[i]);
        }
        return tree;

    }

    public static void bulitTree(Tree tree, Tree pre, int num) {
        if (tree == null) {
            Tree temp = new Tree();
            temp.data = num;
            if (num > pre.data) {
                pre.right = temp;
            } else {
                pre.left = temp;
            }
            return;
        }
        if (num == tree.data) {
            return;
        }

        if (num > tree.data) {
            bulitTree(tree.right, tree, num);
        } else {
            bulitTree(tree.left, tree, num);
        }
    }
}

