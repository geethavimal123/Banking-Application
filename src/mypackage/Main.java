package mypackage;
import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.lang.Math;

class  bank
{
    int accno=0;
    String name;
    float balance;

   // static File fp = new File("accountdetails.txt");
    //static RandomAccessFile raf;
    Random random = new Random();
    static BufferedWriter bw;
    static BufferedReader br;
    static FileWriter filewriter;
    public static void initial_setup () throws IOException
    {

    //raf= new RandomAccessFile(fp, "rw");



    }
    public bank()
    {

    }

    public bank(String n, float b)
    {
        accno=Math.abs(random.nextInt());
        name=n;
        balance=b;
    }

    public void create_account() throws IOException {
        File fp = new File("accountdetails.txt");
        filewriter = new FileWriter(fp.getName(),true);
        bw = new BufferedWriter(filewriter);

        String ss = String.valueOf(this.accno)+","+this.name+","+String.valueOf(this.balance);
        bw.write(ss);
        bw.write("\n");

        System.out.println("Account has been created successfully");

        System.out.println("Please make a note of the account number generated to use in future transactions");
        System.out.println("Account Number: "+this.accno);
        System.out.println("Name: "+this.name);
        System.out.println("Balance: "+this.balance);
        bw.close();
        filewriter.close();
            }

            public void check_details(String input) throws IOException
            {
                File fp = new File("accountdetails.txt");
                FileReader fr= new FileReader(fp);
                BufferedReader br = new BufferedReader(fr);
                String i;
                int found=0;
                while((i=br.readLine())!= null)
                {
 //i - current line in the file
                    String[] split = i.split(",");

                    if (split[0].equals(input))
                    {
                        found=1;
                        System.out.println("Account Number: " + split[0]);
                        System.out.println("Name: " + split[1]);
                        System.out.println("Balance: " + split[2]);
                        break;
                    }
                                 }
                if (found==0)
                {
                    System.out.println("Invalid Account number");
                }
                br.close();
                fr.close();
            }
            public void clearfile() throws IOException
            {
                FileWriter fw = new FileWriter("accountdetails.txt",false);
                PrintWriter pw = new PrintWriter(fw,false);
                pw.flush();
                pw.close();
                fw.close();
            }
            public void refreshfile() throws IOException
            {
                File fpp1 = new File("tempfile.txt");
                File fpp2 = new File("accountdetails.txt");

                FileReader fr1 = new FileReader(fpp1);
                FileWriter fw1 = new FileWriter(fpp2);
                BufferedReader br1 =new BufferedReader(fr1);
                BufferedWriter bw1 = new BufferedWriter(fw1);
                String i1;
                while(=br1.readLin(i1e())!=null)
                {
                    bw1.write(i1);
                    bw1.write("\n");
                }
                bw1.close();
                fw1.close();
                br1.close();
                fr1.close();
            }
            public void credit_Cash(String input,float amount) throws IOException
            {
                File fp = new File("accountdetails.txt");
                FileReader fr= new FileReader(fp);
                BufferedReader br = new BufferedReader(fr);
                String i;
                int found=0;
                String newrec="";

                File fp1 = new File("tempfile.txt");
                FileWriter fw  = new FileWriter(fp1);
                BufferedWriter bw = new BufferedWriter(fw);

                while((i=br.readLine())!= null)
                {

                    String[] split = i.split(",");
                    if (split[0].equals(input))
                    {
                        found=1;
                        String oldrec = split[0]+","+split[1]+","+split[2];
                        float newbal =Float.parseFloat(split[2])+amount;
                        newrec =split[0]+","+split[1]+","+Float.toString(newbal);
                        continue;
                    }
                    else
                    {
                        fw.write(i);
                        fw.write("\n");
                    }
                                 }
                fw.write(newrec); //tempfile got updated
                clearfile();
                System.out.println("Record updated successfully");
                bw.close();
                fw.close();
                refreshfile();
            }
}

public class Main {

    public static void main(String[] args) throws IOException{
        while(true)
        {
            System.out.println("Welcome to ABC Online Banking");
            System.out.println("Press 1 to create new account, 2 to view balance,3 to deposit cash");
            Scanner sc = new Scanner(System.in);
            // bank.initial_setup();
            bank b ;


            int choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    System.out.println("Please enter your name");
                    String name=sc.next();
                    System.out.println("Please enter the amount you would like to deposit");
                    float balance = sc.nextFloat();
                    b = new bank(name,balance);//calling constructor
                    b.create_account();
                    break;

                case 2:
                    System.out.println("Please enter your account number to view the details");
                    String input = sc.next();
                    b= new bank();
                    b.check_details(input); //passing a/c no as the input
                    break;

                case 3:
                    b=new bank();
                    System.out.println("Enter your account number");
                    String acno = sc.next();
                    float credit_amount;
                    System.out.println("Enter the deposit amount");
                    credit_amount=sc.nextFloat();
                    b.credit_Cash(acno,credit_amount);
                    break;

                default:
                    System.out.println("Invalid Input");

            }
            System.out.println("press 0 to quit");
            int ch=sc.nextInt();
            if (ch==0)
                break;
            else
                continue;


        }

	// write your code here
    }
}
