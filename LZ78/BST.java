//Written by Tony Shu (ID:1356141) and Jiwei Wang (ID:1360206)

//dynamic data structure
//all nodes in a bst are the 'siblings'(i.e. nodes that are in the same level in a trie)
//however, this is a limited bst as it does not support all commonly used methods, like remove, traverse, etc
//but it does not need these methods if we only use it to build a trie
//would not make any sense if we write methods that we would never use
public class BST
{
    //declare variables
    private Node root;
    private BST left,right;

    //constructor for a binary search tree
    public BST()
    {
        root=null;
        left=null;
        right=null;
    }

    //add a new node to the bst
    //take one parameter: the new node we want to insert
    public void insert(Node newNode)
    {
        //if we reached a BST whose root is null
        if(root==null)
        {
            //insert a new BST, we are done
            root=newNode;
            left=new BST();
            right=new BST();
        }
        //otherwise keep finding the correct position to insert the new node
        else
        {
            //if the new node whose byte value is less than the byte value of the current BST
            if(Byte.compare(newNode.getContent(),root.getContent())<0)
            {
                //keep finding the correct position to insert the new node from its left subtrees
                left.insert(newNode);
            }
            else
            {
                //keep finding the correct position to insert the new node from its right subtrees
                right.insert(newNode);
            }
        }
    }

    //find a node that contains a specific key value in a binary search tree
    //the parameter is used to know what byte value are we looking for
    public Node find(byte target)
    {
        //if the current root is empty, we are done
        if(root==null)
        {
            return null;
        }
        //if we found what we are looking for, return the node object,and we are done
        else if(Byte.compare(target,root.getContent())==0)
        {
            return root;
        }
        //if the byte value we are looking for is less than the byte value in the current root
        else if(Byte.compare(target,root.getContent())<0)
        {
            //search from its left subtrees
            return left.find(target);
        }
        else
        {
            //otherwise search from its right subtrees
            return right.find(target);
        }
    }
}
