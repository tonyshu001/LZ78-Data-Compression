//Written by Tony Shu (ID:1356141) and Jiwei Wang (ID:1360206)

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

//given the compressed output from LZencode
//the output of LZdecode is exactly the same as the input to LZencode
public class LZdecode
{

    public static void main(String[] args)
    {
        try
        {
            //declare variables
            Scanner sc = new Scanner(System.in);
            ArrayList<Node> list=new ArrayList<Node>();
            String s;
            String[] pairs;
            Stack<String> stack;

            //the trie in LZencode starts with an empty node
            //therefore the dictionary in LZdecode starts with an empty node as well
            list.add(new Node((byte) '\0',0));
            int byteAsInt;

            //as long as we have not reached the end of input
            while(sc.hasNextLine())
            {
                //store the phrase number and the character as a byte value into an array
                s=sc.nextLine();
                pairs=s.split(" ");
                stack=new Stack<String>();

                //convert the character from string to integer
                byteAsInt=Integer.parseInt(pairs[1]);
                //convert the character from integer to byte
                byte[] bytes={(byte)byteAsInt};

                //get the actual character given its byte value using ASCII table
                String text=new String(bytes, StandardCharsets.US_ASCII);

                //fill up the stack, which is used to generate the final output
                addToStack(stack,Integer.parseInt(pairs[0]),text,list,new Node((byte)byteAsInt,Integer.parseInt(pairs[0])));

                //as long as the stack is not empty
                while(!stack.empty())
                {
                    //print out the string value stored at the top of the stack
                    System.out.print(stack.pop());
                }
            }

            //close the scanner
            sc.close();
        }
        catch (Exception e)
        {
            System.err.println(e);
        }
    }

    //this method handles generating output, and store it to a stack
    //takes five parameters:
    //Stack<String> stack: a stack object reference, output is stored in here
    //int phrase: the phrase number of a specific tuple in our dictionary
    //String s: the item that needs to be pushed on to the stack
    //ArrayList<Node> list: an object reference to our dictionary
    //Node n: the new item that need to be added to our dictionary once we finish generating output
    public static void addToStack(Stack<String> stack,int phrase,String s,ArrayList<Node> list,Node n)
    {
        //if the tuple's phrase number is zero, it means there is nothing more followed by it
        if(phrase==0)
        {
            //push the current output to the stack
            stack.push(s);
            //add a new node to our dictionary
            list.add(n);
            //we are done
            return;
        }

        //otherwise push the current output to the stack
        //and keep generating output until we reached the end point of this recursive method
        stack.push(s);
        int i=(int)list.get(phrase).getContent();
        byte[] bytes={(byte)i};
        String text=new String(bytes, StandardCharsets.US_ASCII);
        addToStack(stack,list.get(phrase).getPhrase(),text,list,n);
    }
}
