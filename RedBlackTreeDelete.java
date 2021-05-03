// --== CS400 File Header Information ==--
// Name: Zahaan Motiwala
// Email: zmotiwala@wisc.edu
// Team: LD
// TA: Divyanashu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>


public class RedBlackTreeDelete<T extends Comparable<T>> extends RedBlackTree<T> {

    /**
     * Performs a deletion in a binary search tree: removing the node associated
     * with the input data. After this deletion, no attempt is made to restructure
     * or balance the tree. This tree will not hold null references, nor duplicate
     * data values.
     *
     * @param data to be removed from this binary search tree
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when the tree does not contains data
     *
     * @author Zahaan Motiwala
     */

    public void remove(T data) throws IllegalArgumentException, NullPointerException {
        Node<T> z = search(data);
        Node<T> x, y;

        if (z == null) {
            throw new IllegalArgumentException("Couldn't find key in the tree");

        }

        y = z;
        boolean yOriginalColor = y.isBlack;
        if (z.leftChild == null) {
            x = z.rightChild;
            removeNodeFromTree(z, z.rightChild);
        } else if (z.rightChild == null) {
            x = z.leftChild;
            removeNodeFromTree(z, z.leftChild);
        } else {
            Node<T> min = z.rightChild;
            while (min.leftChild != null) {
                min = min.leftChild;
            }
            y = min;
            yOriginalColor = y.isBlack;
            x = y.rightChild;
            if (y.parent == z) {
                x.parent = y;
            } else {
                removeNodeFromTree(y, y.rightChild);
                y.rightChild = z.rightChild;
                y.rightChild.parent = y;
            }

            removeNodeFromTree(z, y);
            y.leftChild = z.leftChild;
            y.leftChild.parent = y;
            y.isBlack = z.isBlack;
        }
        if (yOriginalColor == true) {
            if (x != null) {
                enforceRBTreePropertiesAfterDelete(x);
            }
        }
    }

    /**
     * Enforces red black tree properties on a binary search tree on a red node that
     * has been removed and balances it to ensure a time complexity of O(logN)
     *
     * @param node removed from this binary search tree
     * @author Zahaan Motiwala
     */

    private void enforceRBTreePropertiesAfterDelete(Node<T> x) {
        Node<T> s;
        while (x != root && x.isBlack) {
            if (x == x.parent.leftChild) {
                s = x.parent.rightChild;
                if (!s.isBlack) {
                    s.isBlack = true;
                    x.parent.isBlack = false;
                    rotate(x, x.parent);
                    s = x.parent.rightChild;
                }

                if (s.leftChild.isBlack && s.rightChild.isBlack) {
                    s.isBlack = false;
                    x = x.parent;
                } else {
                    if (s.rightChild.isBlack) {
                        s.leftChild.isBlack = true;
                        s.isBlack = false;
                        rotate(s.rightChild, s);
                        s = x.parent.rightChild;
                    }

                    s.isBlack = x.parent.isBlack;
                    x.parent.isBlack = true;
                    s.rightChild.isBlack = true;
                    rotate(x, x.parent);
                    x = root;
                }
            } else {
                s = x.parent.leftChild;
                if (!s.isBlack) {
                    s.isBlack = true;
                    x.parent.isBlack = false;
                    rotate(x, x.parent);
                    s = x.parent.leftChild;
                }

                if (s.rightChild.isBlack && s.rightChild.isBlack) {
                    s.isBlack = false;
                    x = x.parent;
                } else {
                    if (s.leftChild.isBlack) {
                        s.rightChild.isBlack = true;
                        s.isBlack = false;
                        rotate(s.rightChild, s);
                        s = x.parent.leftChild;
                    }

                    s.isBlack = x.parent.isBlack;
                    x.parent.isBlack = true;
                    s.leftChild.isBlack = true;
                    rotate(x, x.parent);
                    x = root;
                }
            }
        }
        x.isBlack = true;
    }

    /**
     * Removes a node from the tree and replaces its position with the data stored
     * in the child
     *
     * @param node to be removed from tree and node that will replace its position
     *             on tree
     * @author Zahaan Motiwala
     */

    private void removeNodeFromTree(Node<T> u, Node<T> v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.leftChild) {
            u.parent.leftChild = v;
        } else {
            u.parent.rightChild = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    /**
     * Searches for the node stored in the tree
     *
     * @param Value of node needed from tree
     * @author Zahaan Motiwala
     */

    private Node<T> search(T data) {
        Node<T> temp = root;
        while (temp != null) {
            if (temp.data == data) {
                return temp;
            }

            if (temp.data.compareTo(data) < 0) {
                temp = temp.rightChild;
            } else {
                temp = temp.leftChild;
            }
        }
        return null;
    }

     /**
     * Returns the largest node in the subtree
     *
     * @author Zahaan Motiwala
     */

    public T getRightMostNode() {
        Node<T> nowNode = root;
        while (nowNode.rightChild != null) {
            nowNode = nowNode.rightChild;
        }
        return nowNode.data;
    }

}