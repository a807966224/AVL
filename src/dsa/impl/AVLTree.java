package dsa.impl;

import dsa.iface.IPosition;

import java.util.Comparator;
import java.util.Objects;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

   @Override
   public void insert( T element ) {
      // TODO: Implement the insert(...) method.
   }

   @Override
   public boolean contains( T element ) {
      // TODO: Implement the contains(...) method.
      return false; // this line is here just so that the class will compile. You should replace it.
   }

   @Override
   public void remove( T element ) {
      // TODO: Implement the remove(...) method.
   }

   private void restructure( IPosition<T> x ) {
      // Implement the restructure(...) method.
   }

   @Override
   protected BTPosition newPosition( T element, BTPosition parent ) {
      return new AVLPosition( element, parent );
   }

   public class AVLPosition extends BTPosition {
      int height = 0;

      public AVLPosition( T element, BTPosition parent ) {
         super( element, parent );
      }
   }
}
