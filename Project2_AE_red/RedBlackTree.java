// --== CS400 Project One File Header ==--
// Name: Nikita Agarwal
// CSL Username: nikitaa
// Email: nagarwal29@wisc.edu
// Lecture #: 001 @11:00am

/**
 * Red-Black Tree implementation of removing a node. It rearranges, recolors and swaps the entire tree by traversing
 * through it so that it does not violate any Red-Black Tree rules.
 */
public class RedBlackTree<T extends Comparable<T>> extends  RedBlackTreeADT{
    /**
     * Removes the node with the given data from the tree
     * @param data data of the node that has to be removed
     * @return the removed node, or null if the node is node not in the tree
     */
    public T remove(T data) {
        Node<T> removeNode = findNodeToRemove(data, root);
        if(removeNode == null){
            return null;
        }
        return removeHelper(removeNode);
    }

    /**
     * Finds the node that has to be removed by traversing through the tree
     * @param removeNode node that has to be removed
     * @param node currently being checked (starts at root)
     * @return the node that matched the data
     */
    protected Node<T> findNodeToRemove(T removeNode, Node<T> node){
        int compare = node.data.compareTo(removeNode);
        if(node == null){
            return null;
        }
        //if the node to be removed is smaller than the node checked, go to left subtree
        if(compare > 0) {
            //node to be removed is not found
            if(node.leftChild == null){
                return null;
            }
            return findNodeToRemove(removeNode, node.leftChild);
        }
        //if the node to be removed is larger than the node checked, go to right subtree
        if(compare < 0){
            //node to be removed is not found
            if(node.rightChild == null){
                return null;
            }
            return findNodeToRemove(removeNode, node.rightChild);
        }
        //the value of the node to be removed is equal to the node
        return node;
    }

    /**
     * Helper method for remove method which removes the node
     * @param removeNode the node that has to be removed
     * @return the data of the removed node
     */
    protected T removeHelper(Node<T> removeNode){
        //when the node that has to be removed has no children
        if(removeNode.leftChild == null && removeNode.rightChild == null){
            //if the node that has to be removed is the root
            if(removeNode == root) {
                root = null;
                this.size = this.size - 1;
                return removeNode.data;
            }
            //if the node that has to be removed is red
            if(removeNode.blackHeight == 0){
                if(removeNode.isLeftChild()){
                    removeNode.parent.leftChild = null;
                }
                else {
                    removeNode.parent.rightChild = null;
                }
                this.size--;
                return removeNode.data;
            }
            //if the node that has to be removed is black
            removeNode.blackHeight = 2;
            resolveDoubleBlack(removeNode);
            if(removeNode.isLeftChild()){
                removeNode.parent.leftChild = null;
            }
            else {
                removeNode.parent.rightChild = null;
            }
            this.size--;
            return removeNode.data;
        }

        T data = removeNode.data;
        //when the node that has to be removed has one child
        if(removeNode.leftChild == null){
            removeNode.data = removeNode.rightChild.data;
            removeNode.rightChild = null;
            this.size--;
            return data;
        }
        else if(removeNode.rightChild == null){
            removeNode.data = removeNode.leftChild.data;
            removeNode.leftChild = null;
            this.size--;
            return data;
        }

        //when the node to be removed has two children
        Node<T> toReplace = removeNode.leftChild;
        while(toReplace.rightChild != null){
            toReplace = toReplace.rightChild;
        }
        //replace the node that has to be removed data with the rightmost child of its left subtree
        removeNode.data = toReplace.data;
        removeHelper(toReplace);
        return data;
    }

    /**
     * Resolves the double black node by arranging, recoloring, swapping the tree through recursion
     * @param doubleBlackNode the double black node
     */
    protected void resolveDoubleBlack(Node<T> doubleBlackNode){
        Node<T> parent = doubleBlackNode.parent;
        Node<T> sibling;
        if(doubleBlackNode.isLeftChild()){
            sibling = parent.rightChild;
        }
        else{
            sibling = parent.leftChild;
        }

        //if the double black node has a red sibling
        if(sibling.blackHeight == 0){
            //rotate sibling node and parent node
            rotate(sibling, parent);
            int tempColor = parent.blackHeight;
            //color swap
            parent.blackHeight = sibling.blackHeight;
            sibling.blackHeight = tempColor;
            resolveDoubleBlack(doubleBlackNode);
            return;
        }

        //if the double black node's sibling has only black children (no red children)
        if((sibling.leftChild == null || sibling.leftChild.blackHeight == 1)
                && (sibling.rightChild == null || sibling.rightChild.blackHeight == 1)){
            //recolor the double black node, sibling node and the parent node
            parent.blackHeight++;
            doubleBlackNode.blackHeight--;
            sibling.blackHeight--;
            if(parent.blackHeight == 1){
                return;
            }
            //if the parent node has double black but parent is the root
            if(parent == root) {
                root.blackHeight = 1;
                return;
            }
            //if the parent node becomes double black
            resolveDoubleBlack(parent);
            return;
        }
        //if the double black node's sibling is black and has one or more red child/children
        if(!sibling.isLeftChild() && (sibling.rightChild == null || sibling.rightChild.blackHeight == 1)){
            rotate(sibling.leftChild, sibling);
            sibling = sibling.parent;
            int tmp = sibling.blackHeight;
            sibling.blackHeight = sibling.rightChild.blackHeight;
            sibling.rightChild.blackHeight = tmp;
        }
        else if(sibling.isLeftChild() && (sibling.leftChild == null || sibling.leftChild.blackHeight == 1)){
            int tempColor = sibling.blackHeight;
            sibling = sibling.parent;
            sibling.blackHeight = sibling.leftChild.blackHeight;
            sibling.leftChild.blackHeight = tempColor;
            rotate(sibling.rightChild, sibling);
        }
        //fix the colors of the nodes
        doubleBlackNode.blackHeight--;
        if(sibling.isLeftChild()){
            sibling.leftChild.blackHeight++;
        }
        else{
            sibling.rightChild.blackHeight++;
        }
        //rotate parent and sibling
        rotate(sibling, parent);
        //swap colors
        int tempColor = sibling.blackHeight;
        sibling.blackHeight = parent.blackHeight;
        parent.blackHeight = tempColor;
    }
}
