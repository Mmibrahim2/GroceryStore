import java.util.LinkedList;

public class RedBlackTreeADT<T extends Comparable<T>> implements SortedCollectionInterface<T>{

    /**
     * This class represents a node holding a single value within a binary tree
     * the parent, left, and right child references are always maintained.
     */
    protected static class Node<T> {
        public T data;
        public Node<T> parent; // null for root node
        public Node<T> leftChild;
        public Node<T> rightChild;
        public int blackHeight;
        public Node(T data) {
            this.data = data;
            this.blackHeight = 0;
        }
        /**
         * @return true when this node has a parent and is the left child of
         * that parent, otherwise return false
         */
        public boolean isLeftChild() {
            return parent != null && parent.leftChild == this;
        }

    }

    protected Node<T> root; // reference to root node of tree, null when empty
    protected int size = 0; // the number of values in the tree

    /**
     * Performs a naive insertion into a binary search tree: adding the input
     * data value to a new node in a leaf position within the tree. After
     * this insertion, no attempt is made to restructure or balance the tree.
     * This tree will not hold null references, nor duplicate data values.
     * @param data to be added into this binary search tree
     * @return true if the value was inserted, false if not
     * @throws NullPointerException when the provided data argument is null
     * @throws IllegalArgumentException when the newNode and subtree contain
     *      equal data references
     */
    public boolean insert(T data) throws NullPointerException, IllegalArgumentException {
        // null references cannot be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");

        Node<T> newNode = new Node<>(data);
        if(root == null) {
            root = newNode; size++;
            this.root.blackHeight = 1;
            return true;
        } // add first node to an empty tree
        else{
            boolean returnValue = insertHelper(newNode,root); // recursively insert into subtree
            if (returnValue) size++;
            else throw new IllegalArgumentException(
                    "This RedBlackTree already contains that value.");
            this.root.blackHeight = 1;
            return returnValue;
        }
    }

    /**
     * Recursive helper method to find the subtree with a null reference in the
     * position that the newNode should be inserted, and then extend this tree
     * by the newNode in that position.
     * @param newNode is the new node that is being added to this tree
     * @param subtree is the reference to a node within this tree which the
     *      newNode should be inserted as a descenedent beneath
     * @return true is the value was inserted in subtree, false if not
     */
    private boolean insertHelper(Node<T> newNode, Node<T> subtree) {
        int compare = newNode.data.compareTo(subtree.data);
        // do not allow duplicate values to be stored within this tree
        if(compare == 0) return false;

            // store newNode within left subtree of subtree
        else if(compare < 0) {
            if(subtree.leftChild == null) { // left subtree empty, add here
                subtree.leftChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.leftChild);
        }

        // store newNode within the right subtree of subtree
        else {
            if(subtree.rightChild == null) { // right subtree empty, add here
                subtree.rightChild = newNode;
                newNode.parent = subtree;
                enforceRBTreePropertiesAfterInsert(newNode);
                return true;
                // otherwise continue recursive search for location to insert
            } else return insertHelper(newNode, subtree.rightChild);
        }
    }

    /**
     * Performs the rotation operation on the provided nodes within this tree.
     * When the provided child is a leftChild of the provided parent, this
     * method will perform a right rotation. When the provided child is a
     * rightChild of the provided parent, this method will perform a left rotation.
     * When the provided nodes are not related in one of these ways, this method
     * will throw an IllegalArgumentException.
     * @param child is the node being rotated from child to parent position
     *      (between these two node arguments)
     * @param parent is the node being rotated from parent to child position
     *      (between these two node arguments)
     * @throws IllegalArgumentException when the provided child and parent
     *      node references are not initially (pre-rotation) related that way
     */
    public void rotate(Node<T> child, Node<T> parent) throws IllegalArgumentException {
        if (parent.leftChild != child && parent.rightChild != child) {
            throw new IllegalArgumentException();
        }
        if (parent == this.root) {
            child.parent = null;
            this.root = child;
        }
        else {
            child.parent = parent.parent;
            if (parent.parent.data.compareTo(child.data) > 0) {
                parent.parent.leftChild = child;
            }
            else {
                parent.parent.rightChild = child;
            }
        }

        Node<T> temp = null;
        if (child == parent.leftChild) {
            temp = child.rightChild;
            child.rightChild = parent;
            parent.parent = child;
            parent.leftChild = temp;

            if (temp != null) {
                temp.parent = parent;
            }

        }
        else if (child == parent.rightChild) {
            temp = child.leftChild;
            child.leftChild = parent;
            parent.parent = child;
            parent.rightChild = temp;
            if (temp != null) {
                temp.parent = parent;
            }
        }
    }

    /**
     * Get the size of the tree (its number of nodes).
     * @return the number of nodes in the tree
     */
    public int size() {
        return size;
    }

    /**
     * Method to check if the tree is empty (does not contain any node).
     * @return true of this.size() return 0, false if this.size() > 0
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Checks whether the tree contains the value *data*.
     * @param data the data value to test for
     * @return true if *data* is in the tree, false if it is not in the tree
     */
    public boolean contains(T data) {
        // null references will not be stored within this tree
        if(data == null) throw new NullPointerException(
                "This RedBlackTree cannot store null references.");
        return this.containsHelper(data, root);
    }

    /**
     * Recursive helper method that recurses through the tree and looks
     * for the value *data*.
     * @param data the data value to look for
     * @param subtree the subtree to search through
     * @return true of the value is in the subtree, false if not
     */
    private boolean containsHelper(T data, Node<T> subtree) {
        if (subtree == null) {
            // we are at a null child, value is not in tree
            return false;
        } else {
            int compare = data.compareTo(subtree.data);
            if (compare < 0) {
                // go left in the tree
                return containsHelper(data, subtree.leftChild);
            } else if (compare > 0) {
                // go right in the tree
                return containsHelper(data, subtree.rightChild);
            } else {
                // we found it :)
                return true;
            }
        }
    }

    /**
     * This method performs an inorder traversal of the tree. The string
     * representations of each data value within this tree are assembled into a
     * comma separated string within brackets (similar to many implementations
     * of java.util.Collection, like java.util.ArrayList, LinkedList, etc).
     * Note that this RedBlackTree class implementation of toString generates an
     * inorder traversal. The toString of the Node class class above
     * produces a level order traversal of the nodes / values of the tree.
     * @return string containing the ordered values of this tree (in-order traversal)
     */
    public String toInOrderString() {
        // generate a string of all values of the tree in (ordered) in-order
        // traversal sequence
        StringBuffer sb = new StringBuffer();
        sb.append("[ ");
        sb.append(toInOrderStringHelper("", this.root));
        if (this.root != null) {
            sb.setLength(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }

    private String toInOrderStringHelper(String str, Node<T> node){
        if (node == null) {
            return str;
        }
        str = toInOrderStringHelper(str, node.leftChild);
        str += (node.data.toString() + ", ");
        str = toInOrderStringHelper(str, node.rightChild);
        return str;
    }

    /**
     * This method performs a level order traversal of the tree rooted
     * at the current node. The string representations of each data value
     * within this tree are assembled into a comma separated string within
     * brackets (similar to many implementations of java.util.Collection).
     * Note that the Node's implementation of toString generates a level
     * order traversal. The toString of the RedBlackTree class below
     * produces an inorder traversal of the nodes / values of the tree.
     * This method will be helpful as a helper for the debugging and testing
     * of your rotation implementation.
     * @return string containing the values of this tree in level order
     */
    public String toLevelOrderString() {
        String output = "[ ";
        if (this.root != null) {
            LinkedList<Node<T>> q = new LinkedList<>();
            q.add(this.root);
            while(!q.isEmpty()) {
                Node<T> next = q.removeFirst();
                if(next.leftChild != null) q.add(next.leftChild);
                if(next.rightChild != null) q.add(next.rightChild);
                output += next.data.toString();
                if(!q.isEmpty()) output += ", ";
            }
        }
        return output + " ]";
    }

    public String toString() {
        return "level order: " + this.toLevelOrderString() +
                "\nin order: " + this.toInOrderString();
    }

    /**
     * This method recognizes and handles each of the possible red-black tree property violation cases.
     *
     * @param  node the node that was inserted
     */
    protected void enforceRBTreePropertiesAfterInsert(Node<T> node){
        Node<T> parent = node.parent;
        //Checks if the node is the root
        if (parent == null) {
            return;
        }
        //Checks if the parent of the node is the root
        if (parent.parent == null) {
            parent.blackHeight = 1;
            return;
        }
        //Checks if the parent node is black
        if (parent.blackHeight == 1) {
            return;
        }
        //Checks if the parent is in the left subtree
        if (parent.data.compareTo(parent.parent.data) < 0) {
            //Checks if the uncle exists and is red
            if (parent.parent.rightChild != null
                    && parent.parent.rightChild.blackHeight == 0) {
                parent.blackHeight = 1;
                parent.parent.blackHeight = 0;
                parent.parent.rightChild.blackHeight = 1;
                enforceRBTreePropertiesAfterInsert(parent.parent);
            }
            else {
                //Checks if node is in the left subtree
                if(node.isLeftChild()) {
                    Node<T> temp = parent.parent;
                    rotate(parent, temp);
                    parent.blackHeight = 1;
                    temp.blackHeight = 0;
                }
                //Checks if node is in the right subtree
                else {
                    Node<T> tempParent = parent;
                    rotate(node, tempParent);
                    enforceRBTreePropertiesAfterInsert(tempParent);
                }
            }
        }
        //checks if parent is in the right subtree
        else if (parent.data.compareTo(parent.parent.data) > 0) {
            //checks if uncle exists and is red
            if (parent.parent.leftChild != null
                    && parent.parent.leftChild.blackHeight == 0) {
                parent.blackHeight = 1;
                parent.parent.blackHeight = 0;
                parent.parent.leftChild.blackHeight = 1;
                enforceRBTreePropertiesAfterInsert(parent.parent);
            }
            else {
                //If the node is the right child
                if (!node.isLeftChild()) {
                    Node<T> oldGrand = parent.parent;
                    rotate(parent, oldGrand);
                    parent.blackHeight = 1;
                    oldGrand.blackHeight = 0;
                }
                //If the node is the left child
                else {
                    Node<T> tempParent = parent;
                    rotate(node, tempParent);
                    enforceRBTreePropertiesAfterInsert(tempParent);
                }
            }
        }
        return;
    }
}
