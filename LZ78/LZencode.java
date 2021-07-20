//Written by Tony Shu (ID:1356141) and Jiwei Wang (ID:1360206)

//generate compressed output using lz78 algorithm
public class LZencode
{
    public static void main(String[] args)
    {
        try
        {
            //declare variables
            Trie trie=new Trie();
            int input;

            //as long as we have not reached the end of input
            while ((input=System.in.read())!=-1)
            {
                //let the trie handle generating the output
                trie.output((byte)input,trie.getHead());
            }
        }
        catch (Exception e)
        {
            System.err.println(e);
        }

    }
}
