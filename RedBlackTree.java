// --== CS400 File Header Information ==--
// Name: Taylor Mehmen
// Email: tmehmen@wisc.edu
// Team: LD
// TA: Divyanashu
// Lecturer: Gary Dahl
// Notes to Grader: <optional extra notes>
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//
// Pair Partner:     (name of your pair programming partner)
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)


import java.util.LinkedList;

/**
 * Red Black Tree implementation with a Node inner class for representing
 * the nodes within a Red Black tree.  You can use this class' insert
 * method to build a binary search tree, and its toString method to display
 * the level order (breadth first) traversal of values in that tree.
 */
public class RedBlackTree<T extends Comparable<T>> {

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always to be maintained.
     * It also hold the color of the node for balancing effors.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public boolean isBlack;

        public Node(T data) {
            this.data = data;
            isBlack = false;
        }

        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

        /**
         * This method performs a level order traversal of the tree rooted
         * at the current node.  The string representations of each data value
         * within this tree are assembled into a comma separated string within
         * brackets (similar to many implementations of java.util.Collection).
         *
         * @return string containing the values of this tree in level order
         */
        @Override
        public String toString() { // display subtree in order traversal
            String output = "[";
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this);
            while (!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if (next.leftChild != null) q.add(next.leftChild);
                if (next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if (!q.isEmpty()) output += ", ";
            }
            return output + "]";
        }
    }

    protected Node<T> root; // reference to root node of tree, null when empty

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree.  After
     * this insertion, the tree is restructured and balanced. The root node is
     * always set to black.
     * This tree will not hold null references, nor duplicate data values.
     *
     * @param data to be added into this binary search tree
     * @throws NullPointerException     when the provided data argument is null
     * @throws IllegalArgumentException when the tree already contains data
     */
    public void insert(T data) throws NullPointerException,
            IllegalArgumentException {
        // null references cannot be stored within this tree
        if (data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if (root == null) {
            root = newNode;
        } // add first node to an empty tree
        else insertHelper(newNode, root); // recursively insert into subtree
        root.isBlack = true;
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position. After insertion is will call
     * a method to enforce RBT properties.
     *
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the
     *                newNode should be inserted as a descenedent beneath
     * @throws IllegalArgumentException when the newNode and subtree contain
     *                                  equal data references (as defined by Comparable.compareTo())
     */
    private void insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if (compare == 0) throw new IllegalArgumentException(
                "This RedBlackTree already contains that value.");

            // store newNode within left subtree of subtree
        else if (compare < 0) {
            if (subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if (subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                // otherwise continue recursive search for location to insert
            } else insertHelper(newNode, subtree.rightChild);
        }
        enforceRBTreePropertiesAfterInsert(newNode);
    }

    /**
     * This method performs a level order traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     *
     * @return string containing the values of this tree in level order
     */
    @Override
    public String toString() {
        return root.toString();
    }

    /**
     * Performs the rotation operation on the provided nodes within this RBT.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation (sometimes called a left-right
     * rotation).  When the provided child is a rightChild of the provided
     * parent, this method will perform a left rotation (sometimes called a
     * right-left rotation).  When the provided nodes are not related in one
     * of these ways, this method will throw an IllegalArgumentException.
     *
     * @param child  is the node being rotated from child to parent position
     *               (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *               (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *                                  node references are not initially (pre-rotation) related that way
     */
    public void rotate(Node<T> child, Node<T> parent)
            throws IllegalArgumentException {
        //check for relation
        if (child.parent != parent) {
            throw new IllegalArgumentException();
        }
        //left child -> left-right rotation
        if (child.isLeftChild()) {
            if (child.rightChild != null) {
                (child.rightChild).parent = parent;
                parent.leftChild = child.rightChild;
            } else {
                parent.leftChild = null;
            }
            child.rightChild = parent;
            child.parent = parent.parent;
            if (parent == root) {
                root = child;
            } else {
                parent.parent.rightChild = child;
            }
            parent.parent = child;

        }
        //right child -> right-left rotation
        else {
            if (child.leftChild != null) {
                (child.leftChild).parent = parent;
                parent.rightChild = child.leftChild;
            } else {
                parent.rightChild = null;
            }
            child.leftChild = parent;
            child.parent = parent.parent;
            if (parent == root) {
                root = child;
            } else {
                parent.parent.leftChild = child;
            }
            parent.parent = child;
        }
    }

    /**
     * Performs RBT property enforcement based on 4 cases of inserting a red
     * node:
     * Case0: Parent is black
     * Case1: Parent's Sibling is Red
     * Case2: Parent Sibling is black and opposite side of new node
     * Case3: Parent Sibling is black and same side of new node
     * This method can be run recursively up the tree to make sure the tree
     * is always balanced.
     *
     * @param newRedNode is the node being that was just inserted
     */
    private void enforceRBTreePropertiesAfterInsert(Node<T> newRedNode) {
//        if (newRedNode != root) {
//            System.out.println(newRedNode.data);
//            System.out.println(newRedNode.parent.isBlack);
//        }
        //Case0: Parent is Red
        Node<T> parentSibling;
        if ((newRedNode != root) && (!newRedNode.parent.isBlack)) {
            if (newRedNode.parent.isLeftChild()) {
                parentSibling = newRedNode.parent.parent.rightChild;
            } else {
                parentSibling = newRedNode.parent.parent.leftChild;
            }
            //System.out.println(parentSibling);
            //Case1: Parent's Sibling is Red\
            if (parentSibling != null) {
                //parent sibling is not null
                if (!parentSibling.isBlack) {
                    newRedNode.parent.isBlack = true;
                    parentSibling.isBlack = true;
                    newRedNode.parent.parent.isBlack = false;
                    enforceRBTreePropertiesAfterInsert(newRedNode.parent.parent);
                } else {
                    //Case2: Parent Sibling is black and opposite side of new node
                    if (newRedNode.isLeftChild() ^ parentSibling.isLeftChild()) {
                        newRedNode.parent.isBlack = true;
                        newRedNode.parent.parent.isBlack = false;
                        rotate(newRedNode.parent, newRedNode.parent.parent);
                    }
                    //Case3: Parent Sibling is black and same side of new node
                    else if (newRedNode.isLeftChild() && parentSibling.isLeftChild()) {
                        rotate(newRedNode, newRedNode.parent);
                        newRedNode.isBlack = true;
                        newRedNode.parent.isBlack = false;
                        rotate(newRedNode, newRedNode.parent);
                    }
                }
            } else {
                //parent sibling is null - can't be red
                //Case2: Parent Sibling is black and opposite side of new node
                if (newRedNode.isLeftChild() ^ !newRedNode.parent.isLeftChild()) {
                    newRedNode.parent.isBlack = true;
                    newRedNode.parent.parent.isBlack = false;
                    rotate(newRedNode.parent, newRedNode.parent.parent);
                }
                //Case3: Parent Sibling is black and same side of new node
                else if (newRedNode.isLeftChild() && !newRedNode.parent.isLeftChild()) {
                    rotate(newRedNode, newRedNode.parent);
                    newRedNode.isBlack = true;
                    newRedNode.parent.isBlack = false;
                    rotate(newRedNode, newRedNode.parent);


                }
            }
        }

    }
}
