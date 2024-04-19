package dsa.impl;

import dsa.iface.IPosition;

import java.util.Comparator;
import java.util.Objects;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

   BTPosition BSTInsert(AVLPosition root, T element) {
      // Performs normal BST insertion
      if (root == null || root.element == null) {
         // System.out.println("BSTInsert write this.element " + element + "; root empty : "+(root == null));
         root = (AVLPosition) (newPosition(element, root));
      } else if (element.hashCode() < root.element.hashCode()) {
         
         root.left = BSTInsert((AVLPosition) (root.left), element);

      } else {
         root.right = BSTInsert((AVLPosition) (root.right), element);

   
      }

      // Balances the tree after BST Insertion
      return balanceTree(root);
   }

   // returns the height of the node
   int Height(AVLPosition key) {
      if (key == null)
         return 0;

      else
         return key.height;
   }

   // Balance computes the balance factor of the node
   int Balance(AVLPosition key) {
      if (key == null)
         return 0;

      else
         return (Height((AVLPosition) (key.right)) - Height((AVLPosition) (key.left)));
   }

   // updateHeight updates the height of the node
   void updateHeight(AVLPosition key) {
      int l = Height((AVLPosition) key.left);
      int r = Height((AVLPosition) key.right);

      key.height = Math.max(l, r) + 1;
   }

   AVLPosition rotateLeft(AVLPosition x) {
      AVLPosition y = (AVLPosition) (x.right);
      AVLPosition T2 = (AVLPosition) (y.left);

      y.left = x;
      x.right = T2;

      updateHeight(x);
      updateHeight(y);

      return y;
   }

   AVLPosition rotateRight(AVLPosition y) {
      AVLPosition x = (AVLPosition) (y.left);
      AVLPosition T2 = (AVLPosition) (x.right);

      x.right = y;
      y.left = T2;

      updateHeight(y);
      updateHeight(x);

      return x;
   }

   // balanceTree balances the tree using rotations after an insertion or deletion
   AVLPosition balanceTree(AVLPosition root) {
      updateHeight(root);

      int balance = Balance(root);

      if (balance > 1) // R
      {
         if (Balance((AVLPosition) (root.right)) < 0)// RL
         {
            root.right = rotateRight((AVLPosition) (root.right));
            return rotateLeft(root);
         }

         else // RR
            return rotateLeft(root);
      }

      if (balance < -1)// L
      {
         if (Balance((AVLPosition) (root.left)) > 0)// LR
         {
            root.left = rotateLeft((AVLPosition) (root.left));
            return rotateRight(root);
         } else// LL
            return rotateRight(root);
      }

      return root;
   }

   // findNode is used to search for a particular value given the root
   AVLPosition findNode(AVLPosition root, T element) {
      if (root == null || root.element == null) {
         return null;
      }

      if (element.hashCode() == root.element.hashCode())
         return root;

      if (element.hashCode() < root.element.hashCode())
         return findNode((AVLPosition) (root.left), element);

      else
         return findNode((AVLPosition) (root.right), element);
   }

   // Successor returns the next largest node
   AVLPosition Successor(AVLPosition root) {
      if (root.left != null)
         return Successor((AVLPosition) (root.left));

      else
         return root;
   }

   AVLPosition Remove(BTPosition root, T element) {
      // Performs standard BST Deletion
      if (root == null || root.element == null || element == null) 
         return (AVLPosition) root;

      else if (element.hashCode() < root.element.hashCode())
         root.left = Remove(root.left, element);

      else if (element.hashCode() > root.element.hashCode())
         root.right = Remove(root.right, element);

      else {
         if (root.right == null)
            root = root.left;

         else if (root.left == null)
            root = root.right;

         else {
            AVLPosition temp = Successor((AVLPosition) (root.right));
            root.element = temp.element;
            root.right = Remove(root.right, root.element);
         }
      }

      if (root == null)
         return (AVLPosition) root;

      else
         // Balances the tree after deletion
         return balanceTree((AVLPosition) root);
   }

   @Override
   public void insert(T element) {
      if (findNode((AVLPosition) (this.root), element) == null) {
         this.root = BSTInsert((AVLPosition) (this.root), element);
         //System.out.println("Insertion successful root : " + this.root.element);
      }

      else
         System.out.println("\nKey with the entered value already exists in the tree");

   }

   @Override
   public boolean contains(T element) {
      return findNode((AVLPosition) (this.root), element) != null;

      // return false; // this line is here just so that the class will compile. You
      // should replace it.
   }

   @Override
   public void remove(T element) {
      this.Remove(root, element);
   }

   private void restructure(IPosition<T> x) {
      // Implement the restructure(...) method.
      this.balanceTree((AVLPosition) x);
   }

   @Override
   protected BTPosition newPosition(T element, BTPosition parent) {
      return new AVLPosition(element, parent);
   }



   public class AVLPosition extends BTPosition {
      int height = 0;

      private AVLPosition(){
         super(null,null,null,null);
      }

      public AVLPosition(T element, BTPosition parent) {
         super(element, parent);
         super.parent = parent;
         super.element = element;
         super.left = new AVLPosition();
         super.right = new AVLPosition();
      }
   }
}
