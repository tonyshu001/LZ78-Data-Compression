//Written by Tony Shu (ID:1356141) and Jiwei Wang (ID:1360206)

import java.util.Scanner;

//a dynamic data structure that stores all patterns we have seen
public class Trie
{
    //declare variables
    private int phrase;
    private Node head;

    //constructor for a trie
    //initialised with a head whose phrase number is 0
    public Trie()
    {
        phrase=0;
        head=new Node((byte)'\0',phrase);
    }

    //given a target node, insert a new node to its bst
    //takes two parameters:
    //Byte c: the new node's mismatch character as a byte value
    //Node target: specify insert the new node to which node's bst
    private void extendBST(Byte c,Node target)
    {
        //create a new node, give it the correct phrase number
        Node newNode=new Node(c,++phrase);
        //given a target node, insert the new node to its bst
        target.getBST().insert(newNode);
    }

    //returns the head of the trie
    public Node getHead()
    {
        return head;
    }

    //this method handles the output the trie will generate
    //as well as updating (inserting a new node) the trie
    //takes two parameters:
    //byte target: the next byte value from standard input
    //Node curr: specify which node in our trie we are dealing with
    public void output(byte target,Node curr)
    {
        //see if the node that contains the byte value we are looking for exists in the current node's bst
        Node targetNode=curr.getBST().find(target);

        //if it does not exist, it means we have never seen this pattern before
        if(targetNode==null)
        {
            //generate the output
            System.out.println(curr.getPhrase()+" "+target);
            //add it to our trie
            extendBST(target,curr);
            //we are done
            return;
        }
        //otherwise it means we have seen this pattern before
        else
        {
            //special case handling: we have reached the end of input, and we have also seen this pattern
            //there is no mismatch character
            try
            {
                int input;
                if((input=System.in.read())==-1)
                {
                    //print our the current node's phrase number and the last character
                    System.out.println(curr.getPhrase()+" "+target);
                    //we are done
                    return;
                }

                //keep finding in our trie until we found a pattern that we have never seen before
                output((byte)input,targetNode);
            }
            catch (Exception e)
            {
                System.err.println(e);
            }

        }
    }

}
