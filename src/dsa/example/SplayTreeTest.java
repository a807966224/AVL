package dsa.example;

import dsa.impl.SplayTree;

public class SplayTreeTest {
    public static void main(String[] args) {
        SplayTree<String> tree = new SplayTree<String>();
        tree.insert("33");
        tree.insert("44");
        tree.insert("67");
        tree.insert("5");
        tree.insert("89");
        tree.insert("41");
        tree.insert("98");
        tree.insert("1");
        boolean a = tree.contains("33");
        boolean b = tree.contains("44");
        System.out.println("33 is true ? " + a);
        System.out.println("44 is true ? " + b);

        tree.remove("89");
        tree.remove("67");
        tree.remove("41");
        tree.remove("5");
        boolean c = tree.contains("5");
        System.out.println("5 is already remove ? " + !c);

        tree.remove("98");
        tree.remove("1");
        tree.remove("44");
        tree.remove("33");

        boolean d = tree.contains("33");
        System.out.println("33 is already remove ? " + !d);

    }
}
