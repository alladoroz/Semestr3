package Alla.BinTree;

import java.util.Iterator;

/**
 * Binary search tree
 * @param <T> type of tree elements
 */
public class Tree<T extends Comparable<T>> implements Iterable<T> {
    private class Node {
        private T value;
        private Node leftChild;
        private Node rightChild;

        public Node(T value) {
            this.value = value;
            leftChild = null;
            rightChild = null;
        }

        /**
         * Adds value to the tree with this root
         * @param value value to be added
         */
        private void add(T value) {
            if(value.compareTo(this.value) == 0)
                return;
            else if(value.compareTo(this.value) == -1)
                if (leftChild == null)
                    leftChild = new Node(value);
                else
                    leftChild.add(value);
            else
            if (rightChild == null)
                rightChild = new Node(value);
            else
                rightChild.add(value);
        }

        /**
         * Removes value from the tree with this root
         * @param value value to be removed
         * @return root of the resulting tree
         */
        private Node remove(T value) {
            if (value.compareTo(this.value) == 0) {
                if (leftChild == null && rightChild == null) {
                    return null;
                }
                if (leftChild != null && rightChild == null) {
                    return leftChild;
                }
                if (leftChild == null && rightChild != null) {
                    return rightChild;
                } else {
                    Node substitute = leftChild;
                    while (substitute.rightChild != null) {
                        substitute = substitute.rightChild;
                    }
                    this.value = substitute.value;
                    if (substitute.leftChild != null) {
                        substitute = substitute.leftChild;
                    } else {
                        substitute = null;
                    }

                    return this;
                }
            } else if (value.compareTo(this.value) == -1 && leftChild != null) {
                return leftChild.remove(value);
            } else if (value.compareTo(this.value) == 1 && rightChild != null) {
                return rightChild.remove(value);
            } else
                return this;
        }

        /**
         * Checks if the tree with this root contains the value
         * @param value value to be checked
         * @return true if the value is in the tree
         */
        private boolean contains(T value) {
            if (this != null) {
                if (value.compareTo(this.value) == 0)
                    return true;
                if (value.compareTo(this.value) == -1)
                    return leftChild != null && leftChild.contains(value);
                else
                    return rightChild != null && rightChild.contains(value);
            } else
                return false;
        }
    }

    private Node root;

    public class TreeIterator implements Iterator<T> {

        private TreeIterator() {
            stack = new Stack<Node>();
            if (root != null)
                stack.push(root);
        }

        @Override
        public T next() {
            try {
                position = stack.pop();
                if (position.rightChild != null)
                    stack.push(position.rightChild);
                if (position.leftChild != null)
                    stack.push(position.leftChild);
                return position.value;
            } catch (Exception e) {return null;}
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("cannot remove from a binary tree");
        }

        private Stack<Node> stack;
        private Node position;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    public Tree() {
        root = null;
    }

    /**
     * Adds value to the set
     * @param value value to be added
     */
    public void add(T value) {
        if (root == null)
            root = new Node(value);
        else
            root.add(value);
    }

    /**
     * Removes value from the set
     * @param value value to be removed
     */
    public void remove(T value) {
        if (root != null)
            root = root.remove(value);
    }

    /**
     * Checks if the set contains the value given
     * @param value value to be checked
     * @return true if the value is in the set
     */
    public boolean contains(T value) {
        if (root == null)
            return false;
        else
            return root.contains(value);
    }
}