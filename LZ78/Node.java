//Written by Tony Shu (ID:1356141) and Jiwei Wang (ID:1360206)

//basic component for a trie
//each node consists of a phrase number, the byte value of a character, and a binary search tree
public class
Node
{
    //declare variables
    private byte content;
    private int phrase;
    private BST bst;

    //constructor for a node
    public Node(byte c,int p)
    {
        content=c;
        phrase=p;
        bst=new BST();
    }

    //return the byte value of a character
    public byte getContent()
    {
        return content;
    }

    //return the phrase number
    public int getPhrase()
    {
        return phrase;
    }

    //return the binary search tree
    public BST getBST()
    {
        return bst;
    }

}
