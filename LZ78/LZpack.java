//Written by Tony Shu (ID:1356141) and Jiwei Wang (ID:1360206)

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

//given the output from the LZencode
//this program will compress the phrase number as less number of bits as needed (log2 (k) many bits)
public class LZpack
{

    public static void main(String []args)
    {
        //create a temporary file for data storage (stores data in binary representation)
        File file=new File("temp");
        //convert data from byte values to its compressed binary representation
        byteToBinary(file);
        //convert the compressed binary representation to byte values
        binaryToByte();
        //delete the temporary file as it's no longer needed no more
        file.delete();
    }

    //this method read standard input, which is the output generated by LZencode
    //convert it to compressed binary representation and stores the result in a temporary file
    //takes one parameter:
    //File file: the temporary file that stores the output of this method
    public static void byteToBinary(File file)
    {
        try
        {
            //declare variables
            Scanner sc = new Scanner(System.in);
            int phraseNum=0;
            int bitNum;
            FileWriter myWriter = new FileWriter(file);

            //as long as there is more input coming
            while(sc.hasNextLine())
            {
                String s=sc.nextLine();
                String[] arr=s.split(" ");

                //calculate the minimum number of bits needed for phrase number (log2 (k) many bits)
                int phrase=Integer.valueOf(arr[0]);
                double i=Math.log(phraseNum) / Math.log(2)+ 1e-10;
                bitNum=(int)Math.ceil(i);
                phraseNum++;

                //convert phrase number to its binary representation
                if(i>0)
                {
                    String phraseBinary = String.format("%"+bitNum+"s", Integer.toBinaryString(phrase)).replace(' ', '0');
                    myWriter.write(phraseBinary);
                }

                //convert mismatched characters to its binary representation
                int character=Integer.valueOf(arr[1]);
                String charBinary = String.format("%8s", Integer.toBinaryString(character)).replace(' ', '0');
                myWriter.write(charBinary);


            }
            //close input reader and output writer
            sc.close();
            myWriter.close();


        }
        catch (Exception e)
        {
            System.err.println(e);
        }

    }

    //this method read 8 bits of output stored in the temporary file at one time
    //convert the 8 bits to the corresponding byte value
    public static void binaryToByte()
    {
        try
        {
            //declare variables
            BufferedOutputStream output = new BufferedOutputStream(System.out);
            Scanner sc=new Scanner(new File("temp"));
            String binary;
            sc.useDelimiter("");
            int lastDigit;

            //as long as we have not reached the end of the temporary file
            while (sc.hasNext())
            {
                //read 8 bits from the temporary file
                //convert it into byte representation
                binary=grabBinary(sc);
                int i=Integer.parseInt(binary,2);
                byte b=(byte)i;

                //special case handling:
                //we need to explicitly tell the unpacker the number of bits of the last byte value
                //otherwise when unpacker is rebuilding the binary representation given the output of packer
                //it would not be able to rebuild the binary representation in the exact same way as packer does
                //e.g. the last byte value is 3, and its binary representation is actually 00011
                //if we don't tell the number of bits needed, unpacker will rebuild the binary representation as 11
                //which is not exactly the same as 00011
                if(!sc.hasNext())
                {
                    lastDigit=binary.length();
                    output.write(lastDigit);
                    output.flush();
                }
                output.write(b);
                output.flush();

            }
            sc.close();

        }
        catch (Exception e)
        {
            System.err.println(e);
        }


    }

    //this method read the next 8 bits at a time from the temporary file
    //return the 8 bits as a string
    //takes one parameter:
    //Scanner sc: used for reading input from the temporary file
    public static String grabBinary(Scanner sc)
    {
        //declare variables
        int counter=0;
        String result="";

        //read exact 8 bits at a time
        //return the result
        while (sc.hasNext())
        {
            if(counter>7)
            {break;}
            else
            {
                String s=Character.toString(sc.next().charAt(0));
                result+=s;
            }
            counter++;
        }
        return result;
    }
}
