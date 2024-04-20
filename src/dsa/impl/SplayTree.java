package dsa.impl;

import dsa.iface.IPosition;
import dsa.inner.InnerSplayTree;

public class SplayTree<T extends Comparable<T>> extends BinarySearchTree<T> {

   private InnerSplayTree<T> ist;

   public SplayTree() {
      ist = new InnerSplayTree<T>();
   }

   @Override
   public void insert(T value) {
      ist.insert(value);
   }

   @Override
   public boolean contains(T value) {
      return ist.searchTree(value) != null;
   }

   @Override
   public void remove(T value) {
      ist.deleteNode(value);
   }

   private void splay(IPosition<T> n) {
      // do nothing because auto splay
      
   }
}
